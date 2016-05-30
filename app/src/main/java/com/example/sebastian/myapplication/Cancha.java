package com.example.sebastian.myapplication;

/**
 * Created by sebastian on 30/05/16.
 */
public class Cancha {
    private int idComplejos;
    private int idCancha;
    private double precio;
    private String descripcion;

    public Cancha(){

    }

    public Cancha(int idComplejos, int idCancha, double precio, String descripcion) {
        this.idComplejos = idComplejos;
        this.idCancha = idCancha;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public int getIdComplejos() {
        return idComplejos;
    }

    public void setIdComplejos(int idComplejos) {
        this.idComplejos = idComplejos;
    }

    public int getIdCancha() {
        return idCancha;
    }

    public void setIdCancha(int idCancha) {
        this.idCancha = idCancha;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
