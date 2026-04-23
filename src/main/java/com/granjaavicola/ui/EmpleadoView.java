package com.granjaavicola.ui;

import com.granjaavicola.model.Empleado;
import com.granjaavicola.service.EmpleadoService;
import com.granjaavicola.service.GranjaService;
import com.granjaavicola.model.Granja;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import jakarta.annotation.security.PermitAll;
import java.util.List;

@Route(value = "empleado", layout = MainLayout.class)
@PageTitle("Empleados")
@PermitAll
public class EmpleadoView extends VerticalLayout {

    private final EmpleadoService empleadoService;
    private final GranjaService granjaService;
    private Grid<Empleado> grid;

    public EmpleadoView(EmpleadoService empleadoService, GranjaService granjaService) {
        this.empleadoService = empleadoService;
        this.granjaService = granjaService;
        setSpacing(true);

        Button btnContratar = new Button("Contratar Empleado", e -> abrirDialogContratar());
        Button btnSupervisar = new Button("Asignar Supervision", e -> asignarSupervision());

        grid = new Grid<>(Empleado.class);
        grid.setColumns("id", "nombre", "cargo", "turno", "granjaId");
        grid.setItems(empleadoService.listarEmpleados());

        add(new HorizontalLayout(btnContratar, btnSupervisar), grid);
    }

    private void abrirDialogContratar() {
        Dialog dialog = new Dialog();
        FormLayout form = new FormLayout();

        TextField txtNombre = new TextField("Nombre");
        TextField txtCargo = new TextField("Cargo");
        Select<String> selectTurno = new Select<>();
        selectTurno.setLabel("Turno");
        selectTurno.setItems("Mañana", "Tarde", "Noche");

        Select<Integer> selectGranja = new Select<>();
        selectGranja.setLabel("Granja");
        List<Granja> granjas = granjaService.listarGranjas();
        selectGranja.setItems(granjas.stream().map(Granja::getId).toList());
        selectGranja.setItemLabelGenerator(id -> granjas.stream()
            .filter(g -> g.getId() == id).findFirst().map(Granja::getNombre).orElse(""));

        Button btnGuardar = new Button("Guardar", e -> {
            if (!txtNombre.isEmpty() && !txtCargo.isEmpty()
                && selectTurno.getValue() != null && selectGranja.getValue() != null) {
                empleadoService.contratarEmpleado(txtNombre.getValue(),
                    txtCargo.getValue(), selectTurno.getValue(), selectGranja.getValue());
                grid.setItems(empleadoService.listarEmpleados());
                Notification.show("Empleado contratado");
                dialog.close();
            }
        });
        Button btnCancelar = new Button("Cancelar", ev -> dialog.close());

        form.add(txtNombre, txtCargo, selectTurno, selectGranja, btnGuardar, btnCancelar);
        dialog.add(form);
        dialog.open();
    }

    private void asignarSupervision() {
        Empleado seleccionado = grid.asSingleSelect().getValue();
        if (seleccionado == null) {
            Notification.show("Seleccione un empleado");
            return;
        }
        Notification.show("Empleado " + seleccionado.getNombre() + " puede supervisar galpones");
        empleadoService.supervisarGalpon(seleccionado.getId(), 1);
    }
}