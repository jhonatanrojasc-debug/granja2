package com.granjaavicola.ui;

import com.granjaavicola.model.Produccion;
import com.granjaavicola.service.ProduccionService;
import com.granjaavicola.repository.GalponRepository;
import com.granjaavicola.model.Galpon;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import jakarta.annotation.security.PermitAll;
import java.time.LocalDate;
import java.util.List;

@Route(value = "produccion", layout = MainLayout.class)
@PageTitle("Produccion")
@PermitAll
public class ProduccionView extends VerticalLayout {

    private final ProduccionService produccionService;
    private final GalponRepository galponRepository;
    private Grid<Produccion> grid;

    public ProduccionView(ProduccionService produccionService, GalponRepository galponRepository) {
        this.produccionService = produccionService;
        this.galponRepository = galponRepository;
        setSpacing(true);

        Button btnRegistrar = new Button("Registrar Produccion", e -> abrirDialogRegistrar());
        Button btnReporte = new Button("Generar Reporte", e -> generarReporte());

        grid = new Grid<>(Produccion.class);
        grid.setColumns("id", "fecha", "cantidadHuevos", "galponId");
        grid.setItems(produccionService.listarProducciones());

        add(new HorizontalLayout(btnRegistrar, btnReporte), grid);
    }

    private void abrirDialogRegistrar() {
        Dialog dialog = new Dialog();
        FormLayout form = new FormLayout();

        Select<Integer> selectGalpon = new Select<>();
        selectGalpon.setLabel("Galpon");
        List<Galpon> galpones = galponRepository.findAll();
        selectGalpon.setItems(galpones.stream().map(Galpon::getId).toList());
        selectGalpon.setItemLabelGenerator(id -> galpones.stream()
            .filter(g -> g.getId() == id).findFirst().map(Galpon::getIdentificador).orElse(""));

        TextField txtFecha = new TextField("Fecha (YYYY-MM-DD)");
        txtFecha.setValue(LocalDate.now().toString());
        NumberField txtCantidad = new NumberField("Cantidad Huevos");

        Button btnGuardar = new Button("Guardar", e -> {
            if (selectGalpon.getValue() != null && txtFecha.getValue() != null && txtCantidad.getValue() != null) {
                LocalDate fecha = LocalDate.parse(txtFecha.getValue());
                produccionService.registrarProduccion(fecha,
                    txtCantidad.getValue().intValue(), selectGalpon.getValue());
                grid.setItems(produccionService.listarProducciones());
                Notification.show("Produccion registrada");
                dialog.close();
            }
        });
        Button btnCancelar = new Button("Cancelar", ev -> dialog.close());

        form.add(selectGalpon, txtFecha, txtCantidad, btnGuardar, btnCancelar);
        dialog.add(form);
        dialog.open();
    }

    private void generarReporte() {
        Produccion seleccionado = grid.asSingleSelect().getValue();
        if (seleccionado == null) {
            Notification.show("Seleccione un registro para el reporte");
            return;
        }
        String reporte = produccionService.generarReporte(seleccionado.getGalponId());
        Dialog dialog = new Dialog();
        dialog.add(new com.vaadin.flow.component.html.Pre(reporte));
        dialog.add(new Button("Cerrar", e -> dialog.close()));
        dialog.open();
    }
}