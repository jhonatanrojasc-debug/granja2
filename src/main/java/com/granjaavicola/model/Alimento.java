package com.granjaavicola.model;

import java.time.LocalDate;

public class Alimento {
    private int id;
    private String tipo;
    private int cantidadDisponible;
    private LocalDate fechaCaducidad;

    public Alimento() {}

    public Alimento(String tipo, int cantidadDisponible, LocalDate fechaCaducidad) {
        this.tipo = tipo;
        this.cantidadDisponible = cantidadDisponible;
        this.fechaCaducidad = fechaCaducidad;
    }

    public Alimento(int id, String tipo, int cantidadDisponible, LocalDate fechaCaducidad) {
        this.id = id;
        this.tipo = tipo;
        this.cantidadDisponible = cantidadDisponible;
        this.fechaCaducidad = fechaCaducidad;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public int getCantidadDisponible() { return cantidadDisponible; }
    public void setCantidadDisponible(int cantidadDisponible) { this.cantidadDisponible = cantidadDisponible; }
    public LocalDate getFechaCaducidad() { return fechaCaducidad; }
    public void setFechaCaducidad(LocalDate fechaCaducidad) { this.fechaCaducidad = fechaCaducidad; }
}