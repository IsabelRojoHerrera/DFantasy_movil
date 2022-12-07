package com.example.tab_fragmentos;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Pedido {

    private String numPedido;
    private String status;
    private Consumidor consumidor;
    private ArrayList<Mobiliario> mobiliario;
    private float total;

    public Pedido(String numPedido,Consumidor consumidor, ArrayList<Mobiliario> mobiliario ) {
        this.numPedido = numPedido;
        this.status = "POR ENTREGAR";
        this.consumidor = consumidor;
        this.mobiliario = mobiliario;
    }

    public String toStringPedido(){
        return numPedido +
                "\n"+status+
                "\n"+consumidor.toStringConsumidor();
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

    public String getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(String numPedido) {
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
