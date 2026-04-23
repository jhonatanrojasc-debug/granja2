package com.granjaavicola.ui;

import com.granjaavicola.model.Ave;
import com.granjaavicola.model.Galpon;
import com.granjaavicola.repository.GalponRepository;
import com.granjaavicola.service.AveService;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;

import jakarta.annotation.security.PermitAll;

import java.util.List;

@Route(value = "ave", layout = MainLayout.class)
@PageTitle("Aves")
@PermitAll
public class AveView extends VerticalLayout {

    private final AveService aveService;
    private final GalponRepository galponRepository;
    private Grid<Ave> grid;
    private Select<Integer> selectGalpon;

    public AveView(AveService aveService, GalponRepository galponRepository) {
        this.aveService = aveService;
        this.galponRepository = galponRepository;
        setSpacing(true);

        selectGalpon = new Select<>();
        selectGalpon.setLabel("Filtrar por Galpon");

        List<Galpon> galpones = galponRepository.findAll();

        selectGalpon.setItems(galpones.stream().map(Galpon::getId).toList());
        selectGalpon.setItemLabelGenerator(id -> galpones.stream()
                .filter(g -> g.getId() == id)
                .findFirst()
                .map(Galpon::getIdentificador)
                .orElse(""));

        selectGalpon.addValueChangeListener(e -> {
            if (e.getValue() != null) {
                grid.setItems(aveService.listarAvesPorGalpon(e.getValue()));
            }
        });

        Button btnAgregar = new Button("Registrar Ave", e -> abrirDialogCrear());

        Button btnAlimentar = new Button("Alimentar Seleccionada", e -> {
            Ave ave = grid.asSingleSelect().getValue();
            if (ave != null) {
                aveService.alimentar(ave.getId());
                Notification.show("Ave " + ave.getIdentificador() + " alimentada");
            }
        });

        grid = new Grid<>(Ave.class);
        grid.setColumns("id", "identificador", "raza", "edad", "peso", "estadoSalud");

        if (!galpones.isEmpty()) {
            grid.setItems(aveService.listarAvesPorGalpon(galpones.get(0).getId()));
        }

        add(selectGalpon, new HorizontalLayout(btnAgregar, btnAlimentar), grid);
    }

    private void abrirDialogCrear() {
        Dialog dialog = new Dialog();
        FormLayout form = new FormLayout();

        TextField txtIdentificador = new TextField("Identificador");
        TextField txtRaza = new TextField("Raza");
        NumberField txtEdad = new NumberField("Edad (semanas)");
        NumberField txtPeso = new NumberField("Peso (kg)");

        Select<Integer> selectGalpon = new Select<>();
        selectGalpon.setLabel("Galpon");

        List<Galpon> galpones = galponRepository.findAll();

        selectGalpon.setItems(galpones.stream().map(Galpon::getId).toList());
        selectGalpon.setItemLabelGenerator(id -> galpones.stream()
                .filter(g -> g.getId() == id)
                .findFirst()
                .map(Galpon::getIdentificador)
                .orElse(""));

        Button btnGuardar = new Button("Guardar", e -> {
            if (!txtIdentificador.isEmpty() && !txtRaza.isEmpty()
                    && txtEdad.getValue() != null && txtPeso.getValue() != null
                    && selectGalpon.getValue() != null) {

                aveService.registrarAve(
                        txtIdentificador.getValue(),
                        txtRaza.getValue(),
                        txtEdad.getValue().intValue(),
                        txtPeso.getValue(),
                        selectGalpon.getValue()
                );

                grid.setItems(aveService.listarAvesPorGalpon(selectGalpon.getValue()));
                Notification.show("Ave registrada");
                dialog.close();
            }
        });

        Button btnCancelar = new Button("Cancelar", ev -> dialog.close());

        form.add(txtIdentificador, txtRaza, txtEdad, txtPeso, selectGalpon, btnGuardar, btnCancelar);
        dialog.add(form);
        dialog.open();
    }
}