package com.example.tab_fragmentos;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fragment3 extends Fragment {

    MyReceiver r;
    View v;
    ImageView ivSinPendientes;
    RecyclerView myRecyclerView;
    private ArrayList<Pedido> listStatus = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_fragment3, container, false);
        ivSinPendientes = v.findViewById(R.id.iv_sin_pendientes3);
        myRecyclerView = v.findViewById(R.id.f3_recyclerview);
        apiCargarListado();

        return v;
    }

    public void apiCargarListado(){
        listStatus.clear();

        Call<ArrayList<Pedido>> call = App.getInstance().getApi().getPedidos();
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<ArrayList<Pedido>> call, Response<ArrayList<Pedido>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "No se pudo conectar al servidor", Toast.LENGTH_LONG).show();
                    updateUI();
                    return;
                }

                ArrayList<Pedido> pedidos = response.body();
                for (Pedido p : pedidos) {
                    if (p.getStatus().equals("FINALIZADO")) {
                        listStatus.add(p);
                    }
                }

                setRecyclerView();
                updateUI();
            }

            @Override
            public void onFailure(Call<ArrayList<Pedido>> call, Throwable t) {
                Toast.makeText(getActivity(), "No se pudo conectar al servidor", Toast.LENGTH_LONG).show();
                updateUI();
            }
        });
    }

    public void updateUI() {
        if (listStatus.size() == 0) {
            Log.i("PROBANDO", "SINDATOS");
            ivSinPendientes.setVisibility(View.VISIBLE);
            myRecyclerView.setVisibility(View.GONE);
        } else {
            Log.i("PROBANDO", "DATOS "+ listStatus.size());
            ivSinPendientes.setVisibility(View.GONE);
            myRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    public void setRecyclerView() {
        RecyclerViewAdapter recyclerAdapter = new RecyclerViewAdapter(listStatus, position -> {});
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(recyclerAdapter);
    }

    public void refresh() {
        apiCargarListado();
    }

    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this.getActivity()).unregisterReceiver(r);
    }

    public void onResume() {
        super.onResume();
        r = new MyReceiver();
        LocalBroadcastManager.getInstance(this.getActivity()).registerReceiver(r,
                new IntentFilter("TAG_FINALIZADOS"));
    }

    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            fragment3.this.refresh();
        }
    }
}