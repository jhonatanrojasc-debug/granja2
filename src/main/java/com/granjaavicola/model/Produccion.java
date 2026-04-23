package com.granjaavicola.model;

import java.time.LocalDate;

public class Produccion {
    private int id;
    private LocalDate fecha;
    private int cantidadHuevos;
    private int galponId;

    public Produccion() {}

    public Produccion(LocalDate fecha, int cantidadHuevos, int galponId) {
        this.fecha = fecha;
        this.cantidadHuevos = cantidadHuevos;
        this.galponId = galponId;
    }

    public Produccion(int id, LocalDate fecha, int cantidadHuevos, int galponId) {
        this.id = id;
        this.fecha = fecha;
        this.cantidadHuevos = cantidadHuevos;
        this.galponId = galponId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public int getCantidadHuevos() { return cantidadHuevos; }
    public void setCantidadHuevos(int cantidadHuevos) { this.cantidadHuevos = cantidadHuevos; }
    public int getGalponId() { return galponId; }
    public void setGalponId(int galponId) { this.galponId = galponId; }
}