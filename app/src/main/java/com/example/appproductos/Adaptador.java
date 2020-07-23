package com.example.appproductos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    Activity activity;
    ArrayList<Producto> listaProductos;

    public Adaptador(Activity activity, ArrayList<Producto> listaProductos) {
        this.activity = activity;
        this.listaProductos = listaProductos;
    }

    @Override
    public int getCount() {
        return listaProductos.size();
    }

    @Override
    public Object getItem(int i) {
        return listaProductos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v=view;

        TextView txvNombre, txvMarca,txvPrecio,txvStock;
        Button btnVerProducto;

        if (v==null) {
            LayoutInflater inflater=(LayoutInflater) activity.getLayoutInflater();
            v=inflater.inflate(R.layout.lyt_item_producto,null);
        }

        txvNombre=v.findViewById(R.id.txvNombre);
        txvMarca=v.findViewById(R.id.txvMarca);
        txvPrecio=v.findViewById(R.id.txvPrecio);
        txvStock=v.findViewById(R.id.txvStock);
        btnVerProducto=v.findViewById(R.id.btnVerProducto);

        final Producto p=listaProductos.get(i);
        
        txvNombre.setText(p.getName());
        txvMarca.setText(p.getBrand());
        txvPrecio.setText(p.getPrice()+"");
        txvStock.setText(p.getStock()+"");

        btnVerProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, ActDetalleProducto.class);
                intent.putExtra("data", p);
                activity.startActivity(intent);
            }
        });

        return v;
    }
}
