package com.example.tab_fragmentos;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class fragment3 extends Fragment {

    View v;
    private RecyclerView myRecyclerView;
    private ArrayList<Pedido> listStatus;
    Info in = new Info();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_fragment3, container, false);
        myRecyclerView = (RecyclerView) v.findViewById(R.id.f3_recyclerview);
        RecyclerViewAdapter recyclerAdapter = new RecyclerViewAdapter(getContext(), listStatus);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(recyclerAdapter);
        return v;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listStatus = new ArrayList<>();

        for (int i = 0; i < in.listPedido.size(); i++)
            if (in.listPedido.get(i).getStatus().equals("FINALIZADO"))
                listStatus.add(in.listPedido.get(i));
    }
}