package com.example.jejavi.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jejavi on 13/11/17.
 */

public class Emisora_adapter extends RecyclerView.Adapter<Emisora_adapter.MyViewHolder> {
    private List<Emisora> emisoraList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView emisora, nombre, ciudad;
        public ImageView logoview;

        public MyViewHolder(View view) {
            super(view);
            emisora = view.findViewById(R.id.txtNombre);
           // nombre = view.findViewById(R.id.txtNombre);
            ciudad = view.findViewById(R.id.txtCiudad);
            logoview=view.findViewById(R.id.imgLogo);

        }
    }

    public Emisora_adapter(List<Emisora> emisoraList) {
        this.emisoraList = emisoraList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_emisora_adapter, parent, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Emisora radio = emisoraList.get(position);
        //holder.emisora.setText(radio.getUrl());
        holder.emisora.setText(radio.getNombre());
        holder.ciudad.setText(radio.getCiudad());
        holder.logoview.setImageDrawable(radio.getLogo());

    }

    @Override
    public int getItemCount() {
        return emisoraList.size();
    }
}
