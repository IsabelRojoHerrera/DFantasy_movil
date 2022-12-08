package com.example.tab_fragmentos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MobiliarioAdapter extends RecyclerView.Adapter<MobiliarioAdapter.ViewHolder> {

    ArrayList<Mobiliario> mData;

    public MobiliarioAdapter(ArrayList<Mobiliario> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_mobiliario, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_descripcion.setText(String.valueOf(mData.get(position).toStringMobiliario()));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_descripcion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_descripcion = itemView.findViewById(R.id.tv_detalle);
        }
    }
}