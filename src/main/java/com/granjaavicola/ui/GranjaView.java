package com.granjaavicola.ui;

import com.granjaavicola.model.Granja;
import com.granjaavicola.service.GranjaService;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;

import jakarta.annotation.security.PermitAll;

@Route(value = "granja", layout = MainLayout.class)
@PageTitle("Granjas")
@PermitAll
public class GranjaView extends VerticalLayout {

    private final GranjaService granjaService;
    private Grid<Granja> grid;

    public GranjaView(GranjaService granjaService) {
        this.granjaService = granjaService;

        grid = new Grid<>(Granja.class);
        grid.setColumns("id", "nombre", "ubicacion", "fechaCreacion");
        grid.setItems(granjaService.listarGranjas());

        Button btnAgregar = new Button("Agregar Granja", e -> abrirDialog());

        add(new HorizontalLayout(btnAgregar), grid);
    }

    private void abrirDialog() {
        Dialog dialog = new Dialog();
        FormLayout form = new FormLayout();

        TextField txtNombre = new TextField("Nombre");
        TextField txtUbicacion = new TextField("Ubicación");

        Button btnGuardar = new Button("Guardar", e -> {
            if (!txtNombre.isEmpty() && !txtUbicacion.isEmpty()) {

                granjaService.crearGranja(
                        txtNombre.getValue(),
                        txtUbicacion.getValue()
                );

                grid.setItems(granjaService.listarGranjas());
                Notification.show("Granja registrada");
                dialog.close();
            }
        });

        Button btnCancelar = new Button("Cancelar", e -> dialog.close());

        form.add(txtNombre, txtUbicacion, btnGuardar, btnCancelar);
        dialog.add(form);
        dialog.open();
    }
}