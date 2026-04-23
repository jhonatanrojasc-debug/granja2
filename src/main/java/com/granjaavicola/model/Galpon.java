package com.granjaavicola.model;

public class Galpon {
    private int id;
    private String identificador;
    private int capacidad;
    private int granjaId;

    public Galpon() {}

    public Galpon(String identificador, int capacidad) {
        this.identificador = identificador;
        this.capacidad = capacidad;
    }

    public Galpon(int id, String identificador, int capacidad, int granjaId) {
        this.id = id;
        this.identificador = identificador;
        this.capacidad = capacidad;
        this.granjaId = granjaId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getIdentificador() { return identificador; }
    public void setIdentificador(String identificador) { this.identificador = identificador; }
    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }
    public int getGranjaId() { return granjaId; }
    public void setGranjaId(int granjaId) { this.granjaId = granjaId; }
}