package com.example.appproductos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void agregarProductos(View view) {
        Intent intent = new Intent(this, ActDetalleProducto.class);
        startActivity(intent);
    }

    public void listarProductos(View view) {
        Intent intent = new Intent(this, ActListaProductos.class);
        startActivity(intent);
    }
}