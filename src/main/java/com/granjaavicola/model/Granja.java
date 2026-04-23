package com.granjaavicola.model;

import java.time.LocalDateTime;

public class Granja {
    private int id;
    private String nombre;
    private String ubicacion;
    private LocalDateTime fechaCreacion;

    public Granja() {}

    public Granja(String nombre, String ubicacion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.fechaCreacion = LocalDateTime.now();
    }

    public Granja(int id, String nombre, String ubicacion, LocalDateTime fechaCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.fechaCreacion = fechaCreacion;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
}