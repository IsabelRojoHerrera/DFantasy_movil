package com.example.tab_fragmentos;

import com.google.gson.annotations.SerializedName;

public class Mobiliario {

    @SerializedName("nombre_mobiliario")
    private String nombre;

    @SerializedName("precio_unitario")
    private float precio;

    @SerializedName("cantidad")
    private int cantidad;

    public Mobiliario(String nombre, float precio, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String toStringMobiliario(){
        return "Tipo: "+nombre+
                "\nCantidad: "+cantidad+" x $"+precio+
                "\nPrecio = $"+getTotal();
    }

    public float getTotal(){
        return precio * cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
