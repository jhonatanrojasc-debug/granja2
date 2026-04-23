package com.granjaavicola.ui;

import com.granjaavicola.model.Alimento;
import com.granjaavicola.service.AlimentoService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import jakarta.annotation.security.PermitAll;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Route(value = "alimento", layout = MainLayout.class)
@PageTitle("Alimentos")
@PermitAll
public class AlimentoView extends VerticalLayout {

    private final AlimentoService alimentoService;
    private Grid<Alimento> grid;

    public AlimentoView(AlimentoService alimentoService) {
        this.alimentoService = alimentoService;
        setSpacing(true);

        Button btnAgregar = new Button("Agregar Alimento", e -> abrirDialogAgregar());
        Button btnConsumir = new Button("Registrar Consumo", e -> abrirDialogConsumir());
        Button btnReabastecer = new Button("Reabastecer", e -> abrirDialogReabastecer());

        grid = new Grid<>(Alimento.class);
        grid.setColumns("id", "tipo", "cantidadDisponible", "fechaCaducidad");
        grid.setItems(alimentoService.listarAlimentos());

        add(new HorizontalLayout(btnAgregar, btnConsumir, btnReabastecer), grid);
    }

    private void abrirDialogAgregar() {
        Dialog dialog = new Dialog();
        FormLayout form = new FormLayout();

        TextField txtTipo = new TextField("Tipo");
        NumberField txtCantidad = new NumberField("Cantidad");
        TextField txtCaducidad = new TextField("Fecha Caducidad (YYYY-MM-DD)");

        Button btnGuardar = new Button("Guardar", e -> {
            if (!txtTipo.isEmpty() && txtCantidad.getValue() != null && !txtCaducidad.isEmpty()) {
                alimentoService.crearAlimento(txtTipo.getValue(),
                    txtCantidad.getValue().intValue(), txtCaducidad.getValue());
                grid.setItems(alimentoService.listarAlimentos());
                Notification.show("Alimento agregado");
                dialog.close();
            }
        });
        Button btnCancelar = new Button("Cancelar", ev -> dialog.close());

        form.add(txtTipo, txtCantidad, txtCaducidad, btnGuardar, btnCancelar);
        dialog.add(form);
        dialog.open();
    }

    private void abrirDialogConsumir() {
        Alimento seleccionado = grid.asSingleSelect().getValue();
        if (seleccionado == null) {
            Notification.show("Seleccione un alimento");
            return;
        }
        Dialog dialog = new Dialog();
        FormLayout form = new FormLayout();

        TextField txtInfo = new TextField("Alimento");
        txtInfo.setValue(seleccionado.getTipo() + " - Disponible: " + seleccionado.getCantidadDisponible());
        txtInfo.setReadOnly(true);
        NumberField txtCantidad = new NumberField("Cantidad a consumir");

        Button btnAceptar = new Button("Aceptar", e -> {
            if (txtCantidad.getValue() != null) {
                boolean ok = alimentoService.consumir(seleccionado.getId(), txtCantidad.getValue().intValue());
                if (ok) {
                    grid.setItems(alimentoService.listarAlimentos());
                    Notification.show("Consumo registrado");
                } else {
                    Notification.show("No hay suficiente stock");
                }
                dialog.close();
            }
        });
        Button btnCancelar = new Button("Cancelar", ev -> dialog.close());

        form.add(txtInfo, txtCantidad, btnAceptar, btnCancelar);
        dialog.add(form);
        dialog.open();
    }

    private void abrirDialogReabastecer() {
        Alimento seleccionado = grid.asSingleSelect().getValue();
        if (seleccionado == null) {
            Notification.show("Seleccione un alimento");
            return;
        }
        Dialog dialog = new Dialog();
        FormLayout form = new FormLayout();

        TextField txtInfo = new TextField("Alimento");
        txtInfo.setValue(seleccionado.getTipo() + " - Actual: " + seleccionado.getCantidadDisponible());
        txtInfo.setReadOnly(true);
        NumberField txtCantidad = new NumberField("Cantidad a agregar");

        Button btnAceptar = new Button("Aceptar", e -> {
            if (txtCantidad.getValue() != null) {
                alimentoService.reabastecer(seleccionado.getId(), txtCantidad.getValue().intValue());
                grid.setItems(alimentoService.listarAlimentos());
                Notification.show("Reabastecimiento completado");
                dialog.close();
            }
        });
        Button btnCancelar = new Button("Cancelar", ev -> dialog.close());

        form.add(txtInfo, txtCantidad, btnAceptar, btnCancelar);
        dialog.add(form);
        dialog.open();
    }
}