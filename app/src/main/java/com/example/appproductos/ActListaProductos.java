package com.example.appproductos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ActListaProductos extends AppCompatActivity {
    ListView lstProductos;
    ArrayList<Producto> listaProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_lista_productos);

        asignarReferencias();
        recuperarDatos();
    }

    private void asignarReferencias() {
        lstProductos=findViewById(R.id.lstProductos);
    }

    private void recuperarDatos() {
        listaProductos=new ArrayList<>();

        /*listaProductos.add(new Producto("polo","prenda",1.0,1));
        listaProductos.add(new Producto("camisa","prenda",1.0,1));
        Adaptador adaptador=new Adaptador(this,listaProductos);
        lstProductos.setAdapter(adaptador);*/

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.1.5:8080/products";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest (Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        APIResponse apiResponse = gson.fromJson(response.toString(), APIResponse.class);

                        if (apiResponse.getCode() == 200) {
                            try {
                                JSONArray jsonArray = response.getJSONArray("data");
                                Producto[] productos = gson.fromJson(jsonArray.toString(), Producto[].class);

                                for (Producto pro: productos) {
                                    listaProductos.add(pro);
                                }
                                
                                Adaptador adaptador=new Adaptador(ActListaProductos.this, listaProductos);
                                lstProductos.setAdapter(adaptador);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(ActListaProductos.this, apiResponse.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
        });
        queue.add(jsonObjectRequest);
    }

    public void regresar(View view) {
        Intent intent = new Intent(ActListaProductos.this, MainActivity.class);
        startActivity(intent);
    }
}