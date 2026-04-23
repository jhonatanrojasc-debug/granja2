package com.granjaavicola.ui;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {

    public MainLayout() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.getElement().setAttribute("aria-label", "Menu");
        addToDrawer(createTabs());
        addToNavbar(toggle);
        setDrawerOpened(false);
    }

    private Tabs createTabs() {
        Tabs tabs = new Tabs(
            new Tab(new RouterLink("Inicio", MainView.class)),
            new Tab(new RouterLink("Granjas", GranjaView.class)),
            new Tab(new RouterLink("Galpones", GalponView.class)),
            new Tab(new RouterLink("Aves", AveView.class)),
            new Tab(new RouterLink("Alimentos", AlimentoView.class)),
            new Tab(new RouterLink("Producción", ProduccionView.class)),
            new Tab(new RouterLink("Empleados", EmpleadoView.class))
        );
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        return tabs;
    }
}