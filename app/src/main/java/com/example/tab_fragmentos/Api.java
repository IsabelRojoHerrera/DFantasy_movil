package com.example.tab_fragmentos;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {
    @GET("pedido/get_all")
    Call<ArrayList<Pedido>> getPedidos();

    @PUT("pedido/entregar/{id}")
    Call<ConfirmResponse> entregarPedido(
            @Path("id") Long groupId
    );

    @PUT("pedido/recoger/{id}")
    Call<ConfirmResponse> recogerPedido(
            @Path("id") Long groupId
    );
}
