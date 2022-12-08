package com.example.tab_fragmentos;

import com.google.gson.annotations.SerializedName;

public class Direccion {

    @SerializedName("colonia")
    private String colonia;

    @SerializedName("calle1")
    private String calle1;

    @SerializedName("calle2")
    private String calle2;

    @SerializedName("codigo_postal")
    private int codigoPostal;

    @SerializedName("numero_exterior")
    private int numeroExterior;

    @SerializedName("numero_interior")
    private int numeroInterior;

    @SerializedName("descripcion")
    private String descripcion;

    public Direccion(String colonia, String calle1, String calle2, int codigoPostal, int numeroExterior, int numeroInterior, String descripcion) {
        this.colonia = colonia;
        this.calle1 = calle1;
        this.calle2 = calle2;
        this.codigoPostal = codigoPostal;
        this.numeroExterior = numeroExterior;
        this.numeroInterior = numeroInterior;
        this.descripcion = descripcion;
    }

    public String toStringDireccion(){
        return calle1 + " " + numeroExterior +
                "\n" + colonia + " CP " + codigoPostal +
                "\nReferencias: " + descripcion;
    }
}
