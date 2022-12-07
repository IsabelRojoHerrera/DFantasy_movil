package com.example.tab_fragmentos;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mcontext;
    ArrayList<Pedido> mData;
    Dialog myDialog;

    public RecyclerViewAdapter(Context mcontext, ArrayList<Pedido> mData) {
        this.mcontext = mcontext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mcontext).inflate(R.layout.item_f1, parent, false);
        final MyViewHolder vHolder = new MyViewHolder(v);




        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_num_pedido.setText(mData.get(position).getNumPedido());
        holder.tv_pedido.setText(mData.get(position).toStringPedido());
        holder.tv_status.setText(mData.get(position).getStatus());
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private Button btn_entregar_item;
        private TextView tv_num_pedido;
        private TextView tv_pedido;
        private TextView tv_status;

        public MyViewHolder(View itemView) {
            super(itemView);

            btn_entregar_item = (Button) itemView.findViewById(R.id.btn_entregar);
            tv_num_pedido = (TextView) itemView.findViewById(R.id.titulo_pedido);
            tv_pedido = (TextView) itemView.findViewById(R.id.info_pedido);
            tv_status = (TextView) itemView.findViewById(R.id.status_pedido);
        }
    }
}
