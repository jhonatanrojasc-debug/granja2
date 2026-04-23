package com.granjaavicola.model;

public class Ave {
    private int id;
    private String identificador;
    private String raza;
    private int edad;
    private double peso;
    private String estadoSalud;
    private int huevosProducidos;
    private Integer galponId;

    public Ave() {
        this.huevosProducidos = 0;
        this.estadoSalud = "Saludable";
    }

    public Ave(String identificador, String raza, int edad, double peso) {
        this.identificador = identificador;
        this.raza = raza;
        this.edad = edad;
        this.peso = peso;
        this.estadoSalud = "Saludable";
        this.huevosProducidos = 0;
    }

    public Ave(int id, String identificador, String raza, int edad, double peso, String estadoSalud, Integer galponId) {
        this.id = id;
        this.identificador = identificador;
        this.raza = raza;
        this.edad = edad;
        this.peso = peso;
        this.estadoSalud = estadoSalud;
        this.galponId = galponId;
        this.huevosProducidos = 0;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getIdentificador() { return identificador; }
    public void setIdentificador(String identificador) { this.identificador = identificador; }
    public String getRaza() { return raza; }
    public void setRaza(String raza) { this.raza = raza; }
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }
    public String getEstadoSalud() { return estadoSalud; }
    public void setEstadoSalud(String estadoSalud) { this.estadoSalud = estadoSalud; }
    public int getHuevosProducidos() { return huevosProducidos; }
    public void setHuevosProducidos(int huevosProducidos) { this.huevosProducidos = huevosProducidos; }
    public Integer getGalponId() { return galponId; }
    public void setGalponId(Integer galponId) { this.galponId = galponId; }
}