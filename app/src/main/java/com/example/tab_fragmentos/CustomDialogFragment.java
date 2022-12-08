package com.example.tab_fragmentos;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CustomDialogFragment extends DialogFragment{

    private Pedido pedido;
    private int position;
    private RecyclerView myRecyclerView;
    private OnConfirmClickListener callback;

    public CustomDialogFragment (Pedido pedido, int position) {
        this.pedido = pedido;
        this.position = position;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            callback = (OnConfirmClickListener) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException("Calling fragment must implement DialogClickListener interface");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mobiliarios_layout, container, false);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myRecyclerView = view.findViewById(R.id.mobiliarios_list);
        MobiliarioAdapter recyclerAdapter = new MobiliarioAdapter(pedido.getMobiliario());
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(recyclerAdapter);

        TextView tvNumPedido = view.findViewById(R.id.select_title2);
        tvNumPedido.setText("Detalle del pedido No. " + pedido.getNumPedido());

        TextView tvTotal = view.findViewById(R.id.tv_total);
        tvTotal.setText("Total: $" + pedido.getTotal());

        Button btnConfirm = view.findViewById(R.id.btn_confirm);
        if (pedido.getStatus().equals("POR ENTREGAR")){
            btnConfirm.setText("ENTREGAR");
        } else if (pedido.getStatus().equals("POR RECOGER")) {
            btnConfirm.setText("RECOGER");
        }

        btnConfirm.setOnClickListener(view1 -> {
            callback.onConfirmClick(pedido.getNumPedido(), position);
            dismiss();
        });

    }
}
