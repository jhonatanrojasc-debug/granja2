package com.granjaavicola.ui;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Granja Avicola - Inicio")
public class MainView extends VerticalLayout {

    public MainView() {
        setSpacing(true);
        setAlignItems(Alignment.CENTER);

        // Título
        H1 titulo = new H1("Sistema de Gestion de Granja Avicola");
        titulo.getStyle().set("color", "#2E7D32");

        // Descripción
        Paragraph descripcion = new Paragraph(
                "Gestiona de manera eficiente tu granja avicola: galpones, aves, produccion y empleados."
        );

        // "Card" simulada con Div
        Div cardInfo = new Div();
        cardInfo.add(new H1("Bienvenido"));
        cardInfo.add(new Paragraph("Usa el menu lateral para navegar entre modulos."));

        // Estilos tipo tarjeta
        cardInfo.getStyle()
                .set("border", "1px solid #ddd")
                .set("border-radius", "10px")
                .set("padding", "20px")
                .set("box-shadow", "0 2px 5px rgba(0,0,0,0.1)")
                .set("background-color", "#ffffff");

        cardInfo.setWidth("500px");

        // Agregar todo al layout
        add(titulo, descripcion, cardInfo);
    }
    }
