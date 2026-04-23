package com.granjaavicola.model;

public class Empleado {
    private int id;
    private String nombre;
    private String cargo;
    private String turno;
    private int granjaId;

    public Empleado() {}

    public Empleado(String nombre, String cargo, String turno) {
        this.nombre = nombre;
        this.cargo = cargo;
        this.turno = turno;
    }

    public Empleado(int id, String nombre, String cargo, String turno, int granjaId) {
        this.id = id;
        this.nombre = nombre;
        this.cargo = cargo;
        this.turno = turno;
        this.granjaId = granjaId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    public String getTurno() { return turno; }
    public void setTurno(String turno) { this.turno = turno; }
    public int getGranjaId() { return granjaId; }
    public void setGranjaId(int granjaId) { this.granjaId = granjaId; }
}