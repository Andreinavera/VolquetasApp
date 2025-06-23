package com.example.volquetasapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvVolquetas;
    private VolquetaAdapter adapter;
    private View progressLoading;
    private View textVacio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvVolquetas = findViewById(R.id.rvVolquetas);
        progressLoading = findViewById(R.id.progressLoading);
        textVacio = findViewById(R.id.textVacio);

        rvVolquetas.setLayoutManager(new LinearLayoutManager(this));
        adapter = new VolquetaAdapter(); // Asume constructor sin lista inicial
        rvVolquetas.setAdapter(adapter);

        cargarVolquetas();
    }

    private void cargarVolquetas() {
        progressLoading.setVisibility(View.VISIBLE);
        rvVolquetas.setVisibility(View.GONE);
        textVacio.setVisibility(View.GONE);

        // Llama a tu API con Retrofit
        Call<List<Volqueta>> call = ApiClient.getVolquetasApi().getVolquetas();
        call.enqueue(new Callback<List<Volqueta>>() {
            @Override
            public void onResponse(Call<List<Volqueta>> call, Response<List<Volqueta>> response) {
                progressLoading.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    List<Volqueta> lista = response.body();
                    if (lista.isEmpty()) {
                        textVacio.setVisibility(View.VISIBLE);
                    } else {
                        adapter.setVolquetas(lista);
                        rvVolquetas.setVisibility(View.VISIBLE);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Error al cargar volquetas", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Volqueta>> call, Throwable t) {
                progressLoading.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Fallo en la conexión: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
