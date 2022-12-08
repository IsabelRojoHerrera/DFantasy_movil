package com.example.tab_fragmentos;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class Pedido {

    @SerializedName("id")
    private long numPedido;

    @SerializedName("estatus")
    private String status;

    @SerializedName("consumidor")
    private Consumidor consumidor;

    @SerializedName("direccion")
    private Direccion direccion;

    @SerializedName("detalle_pedidos")
    private ArrayList<Mobiliario> mobiliario;

    private float total;

    public Pedido(Long numPedido, Consumidor consumidor, Direccion direccion, ArrayList<Mobiliario> mobiliario ) {
        this.numPedido = numPedido;
        this.status = "POR ENTREGAR";
        this.direccion = direccion;
        this.consumidor = consumidor;
        this.mobiliario = mobiliario;
    }

    public String toStringPedido(){
        return  "08/12/2022"+
                "\n"+consumidor.toStringConsumidor()+
                "\n"+direccion.toStringDireccion();
    }

    public String toStringDetallePedido(){

        String mobiliarios = "";

        for(Mobiliario m : mobiliario){
            mobiliarios += "\n"+m.toStringMobiliario();
        }
        return toStringPedido()+ mobiliarios + getTotal();
    }

    public float getTotal(){

        float total = 0;

        for(Mobiliario m : mobiliario){
            total += m.getTotal();
        }
        return total;
    }

    public long getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(long numPedido) {
        this.numPedido = numPedido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Consumidor getConsumidor() {
        return consumidor;
    }

    public void setConsumidor(Consumidor consumidor) {
        this.consumidor = consumidor;
    }

    public ArrayList<Mobiliario> getMobiliario() {
        return mobiliario;
    }

    public void setMobiliario(ArrayList<Mobiliario> mobiliario) {
        this.mobiliario = mobiliario;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
