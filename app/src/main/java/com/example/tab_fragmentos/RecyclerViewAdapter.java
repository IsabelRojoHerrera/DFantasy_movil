package com.example.tab_fragmentos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    ArrayList<Pedido> mData;
    private OnPedidoClickListener mListener;

    public RecyclerViewAdapter(ArrayList<Pedido> mData, OnPedidoClickListener mListener) {
        this.mData = mData;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_f1, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_num_pedido.setText(String.valueOf(mData.get(position).getNumPedido()));
        holder.tv_pedido.setText(mData.get(position).toStringPedido());
        holder.tv_status.setText(mData.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_num_pedido;
        private TextView tv_pedido;
        private TextView tv_status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_num_pedido = itemView.findViewById(R.id.titulo_pedido);
            tv_pedido = itemView.findViewById(R.id.info_pedido);
            tv_status = itemView.findViewById(R.id.status_pedido);

            itemView.setOnClickListener(view -> mListener.onPedidoClick(getAbsoluteAdapterPosition()));
        }
    }
}