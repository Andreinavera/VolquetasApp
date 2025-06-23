package com.example.volquetasapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import missing.namespace.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvVolquetas;
    private VolquetaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configurar Spinner
        Spinner spnCategory = findViewById(R.id.spnCategory);
        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this,
                R.array.categories, android.R.layout.simple_spinner_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCategory.setAdapter(adapterSpinner);

        // Configurar RecyclerView
        rvVolquetas = findViewById(R.id.rvVolquetas);
        rvVolquetas.setLayoutManager(new LinearLayoutManager(this));
        adapter = new VolquetaAdapter(null);
        rvVolquetas.setAdapter(adapter);

        // Configurar botón
        Button btnShowVolquetas = findViewById(R.id.btnShowVolquetas);
        btnShowVolquetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadVolquetas();
            }
        });
    }

    private void loadVolquetas() {
        VolquetaApi api = RetrofitClient.getClient().create(VolquetaApi.class);
        Call<List<Volqueta>> call = api.getVolquetas();
        call.enqueue(new Callback<List<Volqueta>>() {
            @Override
            public void onResponse(Call<List<Volqueta>> call, Response<List<Volqueta>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Volqueta> volquetas = response.body();
                    adapter = new VolquetaAdapter(volquetas);
                    rvVolquetas.setAdapter(adapter);
                    rvVolquetas.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(MainActivity.this, "Error al cargar volquetas", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Volqueta>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Fallo en la conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}