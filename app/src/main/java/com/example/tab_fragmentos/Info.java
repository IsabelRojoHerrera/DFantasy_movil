package com.example.tab_fragmentos;

import java.util.ArrayList;

public class Info {

     ArrayList<Pedido> listPedido;
    private ArrayList<Mobiliario> m = new ArrayList<>();

    public Info(){
        llenaMobi();
        llenaList();
    }

    public void llenaList(){
        llenaMobi();
        //AQUI SE AGREGAN PEIDIDOS:
        listPedido = new ArrayList<>();
        listPedido.add(new Pedido("001", new Consumidor("Isabel desde info", "Esta es la dirección", "1234567890"),m));
        listPedido.add(new Pedido("002", new Consumidor("Isabel aaa", "Esta es la dirección", "1234567890"),m));
        listPedido.add(new Pedido("003", new Consumidor("Isabel", "Esta es la dirección", "1234567890"),m));
        listPedido.add(new Pedido("001", new Consumidor("Isabel desde info", "Esta es la dirección", "1234567890"),m));
        listPedido.add(new Pedido("002", new Consumidor("Isabel aaa", "Esta es la dirección", "1234567890"),m));
        listPedido.add(new Pedido("003", new Consumidor("Isabel", "Esta es la dirección", "1234567890"),m));
    }

    public void llenaMobi(){
        m.add(new Mobiliario("Mesa redonda", 300, 5));
        m.add(new Mobiliario("Mesa cuadrada", 300, 2));
        m.add(new Mobiliario("Mantel redondo", 150, 5));
        m.add(new Mobiliario("Mantel cuadrado", 150, 2));
    }

}
