package com.granjaavicola.ui;

import com.granjaavicola.model.Galpon;
import com.granjaavicola.model.Granja;
import com.granjaavicola.service.GranjaService;
import com.granjaavicola.repository.GalponRepository;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;

import jakarta.annotation.security.PermitAll;

import java.util.List;

@Route(value = "galpon", layout = MainLayout.class)
@PageTitle("Galpones")
@PermitAll
public class GalponView extends VerticalLayout {

    private final GranjaService granjaService;
    private final GalponRepository galponRepository;

    private Grid<Galpon> grid;
    private Select<Integer> selectGranja;

    public GalponView(GranjaService granjaService, GalponRepository galponRepository) {
        this.granjaService = granjaService;
        this.galponRepository = galponRepository;

        setSpacing(true);

        // 🔽 Selector de granja
        selectGranja = new Select<>();
        selectGranja.setLabel("Filtrar por Granja");

        List<Granja> granjas = granjaService.listarGranjas();

        selectGranja.setItems(granjas.stream().map(Granja::getId).toList());

        selectGranja.setItemLabelGenerator(id ->
                granjas.stream()
                        .filter(g -> g.getId() == id)
                        .findFirst()
                        .map(Granja::getNombre)
                        .orElse("")
        );

        selectGranja.addValueChangeListener(e -> {
            if (e.getValue() != null) {
                grid.setItems(granjaService.listarGalpones(e.getValue()));
            }
        });

        // 🔽 Botón agregar
        Button btnAgregar = new Button("Nuevo Galpón", e -> abrirDialogCrear());

        // 🔽 Grid
        grid = new Grid<>(Galpon.class);
        grid.setColumns("id", "identificador", "capacidad", "granjaId");

        if (!granjas.isEmpty()) {
            grid.setItems(granjaService.listarGalpones(granjas.get(0).getId()));
        }

        add(selectGranja, btnAgregar, grid);
    }

    // 🔽 Dialog para crear galpón
    private void abrirDialogCrear() {
        Dialog dialog = new Dialog();
        FormLayout form = new FormLayout();

        TextField txtIdentificador = new TextField("Identificador");

        NumberField txtCapacidad = new NumberField("Capacidad");
        txtCapacidad.setMin(1);

        Select<Integer> selectGranjaDialog = new Select<>();
        selectGranjaDialog.setLabel("Granja");

        List<Granja> granjas = granjaService.listarGranjas();

        selectGranjaDialog.setItems(granjas.stream().map(Granja::getId).toList());

        selectGranjaDialog.setItemLabelGenerator(id ->
                granjas.stream()
                        .filter(g -> g.getId() == id)
                        .findFirst()
                        .map(Granja::getNombre)
                        .orElse("")
        );

        // 🔽 Botón guardar
        Button btnGuardar = new Button("Guardar", e -> {
            if (!txtIdentificador.isEmpty()
                    && txtCapacidad.getValue() != null
                    && selectGranjaDialog.getValue() != null) {

                granjaService.crearGalpon(
                        txtIdentificador.getValue(),
                        txtCapacidad.getValue().intValue(),
                        selectGranjaDialog.getValue()
                );

                grid.setItems(granjaService.listarGalpones(selectGranjaDialog.getValue()));

                Notification.show("Galpón creado correctamente");
                dialog.close();
            } else {
                Notification.show("Completa todos los campos");
            }
        });

        Button btnCancelar = new Button("Cancelar", e -> dialog.close());

        form.add(txtIdentificador, txtCapacidad, selectGranjaDialog, btnGuardar, btnCancelar);

        dialog.add(form);
        dialog.open();
    }
}