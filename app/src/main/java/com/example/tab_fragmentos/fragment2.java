package com.example.tab_fragmentos;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fragment2 extends Fragment implements OnConfirmClickListener {

    MyReceiver r;
    View v;
    RecyclerViewAdapter recyclerAdapter;
    ImageView ivSinPendientes;;
    RecyclerView myRecyclerView;
    private ArrayList<Pedido> listStatus = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_fragment2, container, false);
        ivSinPendientes = v.findViewById(R.id.iv_sin_pendientes2);
        myRecyclerView = v.findViewById(R.id.f2_recyclerview);
        apiCargarListado();

        return v;
    }

    @Override
    public void onConfirmClick(Long numPedido, int position) {
        apiRecoger(numPedido, position);
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
                    if (p.getStatus().equals("POR RECOGER")) {
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
            ivSinPendientes.setVisibility(View.VISIBLE);
            myRecyclerView.setVisibility(View.GONE);
        } else {
            ivSinPendientes.setVisibility(View.GONE);
            myRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    public void apiRecoger(Long numPedido, int position){
        Call<ConfirmResponse> call = App.getInstance().getApi().recogerPedido(numPedido);
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<ConfirmResponse> call, Response<ConfirmResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "No se pudo conectar al servidor", Toast.LENGTH_LONG).show();
                    return;
                }

                ConfirmResponse confirmResponse = response.body();
                Long id = confirmResponse.getId();

                removeAt(position);

                Toast.makeText(getActivity(), "Se ha recogido el pedido No. " + id, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ConfirmResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "No se pudo conectar al servidor", Toast.LENGTH_LONG).show();
            }
        });
    }


    public void setRecyclerView(){
        recyclerAdapter = new RecyclerViewAdapter(listStatus,
                this::showDialog
        );
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(recyclerAdapter);
    }

    public void showDialog(int posicion) {
        FragmentManager fragmentManager = getFragmentManager();
        CustomDialogFragment newFragment = new CustomDialogFragment(listStatus.get(posicion), posicion);
        newFragment.setTargetFragment(this,0);
        newFragment.show(fragmentManager, "dialog");
    }

    public void removeAt(int position) {
        listStatus.remove(position);
        recyclerAdapter.notifyItemRemoved(position);
        recyclerAdapter.notifyItemRangeChanged(position, listStatus.size());
        updateUI();
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
                new IntentFilter("TAG_POR_RECOGER"));
    }

    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            fragment2.this.refresh();
        }
    }
}