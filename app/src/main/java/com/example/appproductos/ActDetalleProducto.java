package com.example.appproductos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class ActDetalleProducto extends AppCompatActivity {
    EditText edtNombre,edtMarca,edtPrecio,edtStock;
    Button btnRegistrar,btnEliminar,btnEditar;
    Producto producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_detalle_producto);

        asignarReferencias();
        recuperarDatos();
    }

    private void asignarReferencias() {
        edtNombre=findViewById(R.id.edtNombre);
        edtMarca=findViewById(R.id.edtMarca);
        edtPrecio=findViewById(R.id.edtPrecio);
        edtStock=findViewById(R.id.edtStock);
        btnRegistrar=findViewById(R.id.btnRegistrar);
        btnEliminar=findViewById(R.id.btnEliminar);
        btnEditar=findViewById(R.id.btnEditar);
    }

    private void recuperarDatos() {
        Bundle bundle=getIntent().getExtras();
        if (bundle!=null) {
            producto=(Producto) bundle.getSerializable("data");
            Toast.makeText(this,producto.getId()+"",Toast.LENGTH_LONG).show();
            btnRegistrar.setVisibility(View.INVISIBLE);

            mostrarDatos();
        } else {
            producto = new Producto();
            btnEliminar.setVisibility(View.INVISIBLE);
            btnEditar.setVisibility(View.INVISIBLE);
        }
    }

    private void mostrarDatos() {
        edtNombre.setText(producto.getName());
        edtMarca.setText(producto.getBrand());
        edtPrecio.setText(producto.getPrice()+"");
        edtStock.setText(producto.getStock()+"");
    }

    public void registrarProducto(View view) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://localhost:8080/products";

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", edtNombre.getText().toString());
            jsonObject.put("brand", edtMarca.getText().toString());
            jsonObject.put("price", edtPrecio.getText().length() > 0 ? Double.parseDouble(edtPrecio.getText().toString()) : null);
            jsonObject.put("stock", edtStock.getText().length() > 0 ? Integer.parseInt(edtStock.getText().toString()) : null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest (Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        APIResponse apiResponse = gson.fromJson(response.toString(), APIResponse.class);
                        if (apiResponse.getCode() == 200) {
                            Toast.makeText(ActDetalleProducto.this, "Se registró correctamente",Toast.LENGTH_LONG).show();
                            limpiar();
                        } else {
                            Toast.makeText(ActDetalleProducto.this, apiResponse.getMessage(),Toast.LENGTH_LONG).show();
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

    private void limpiar() {
        edtNombre.setText("");
        edtMarca.setText("");
        edtPrecio.setText("");
        edtStock.setText("");
        edtNombre.requestFocus();
    }
    public void editarProducto(View view) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.1.5:8080/products/" + producto.getId();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", edtNombre.getText().toString());
            jsonObject.put("brand", edtMarca.getText().toString());
            jsonObject.put("price", Double.parseDouble(edtPrecio.getText().toString()));
            jsonObject.put("stock", Integer.parseInt(edtStock.getText().toString()));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest (Request.Method.PUT, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        APIResponse apiResponse = gson.fromJson(response.toString(), APIResponse.class);
                        if (apiResponse.getCode() == 200) {
                            Toast.makeText(ActDetalleProducto.this, "Se actualizó correctamente",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(ActDetalleProducto.this, ActListaProductos.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(ActDetalleProducto.this, apiResponse.getMessage(),Toast.LENGTH_LONG).show();
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

    public void eliminarProducto(View view) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.1.5:8080/products/" + producto.getId();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest (Request.Method.DELETE, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        APIResponse apiResponse = gson.fromJson(response.toString(), APIResponse.class);
                        if (apiResponse.getCode() == 200) {
                            Toast.makeText(ActDetalleProducto.this, "Se eliminó correctamente",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(ActDetalleProducto.this, ActListaProductos.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(ActDetalleProducto.this, apiResponse.getMessage(),Toast.LENGTH_LONG).show();
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
        Intent intent = new Intent(ActDetalleProducto.this, MainActivity.class);
        startActivity(intent);
    }
}