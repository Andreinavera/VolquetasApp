package com.example.volquetasapp;

import static android.os.Build.VERSION_CODES.R;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import missing.namespace.R;

public class ListaVolquetasActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private final List<Volqueta> volquetasList = new ArrayList<>();
    private VolquetaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_volquetas);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.titulo_pantalla_lista);

        recyclerView = findViewById(R.id.recyclerViewVolquetas);
        progressBar = findViewById(R.id.progressBar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new VolquetaAdapter(volquetasList);
        recyclerView.setAdapter(adapter);

        cargarDatosDesdeAPI();
    }

    private void cargarDatosDesdeAPI() {
        String url = "https://uteqia.com/api/volquetas";
        progressBar.setVisibility(View.VISIBLE);

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        volquetasList.clear();
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject volquetaObject = response.getJSONObject(i);
                            String placa = volquetaObject.getString("placa");
                            String marca = volquetaObject.getString("marca");
                            double capacidad = volquetaObject.getDouble("capacidad_m3");
                            String estado = volquetaObject.getString("estado");

                            volquetasList.add(new Volqueta(placa, marca, capacidad, estado));
                        }
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Error al procesar los datos", Toast.LENGTH_SHORT).show();
                    } finally {
                        progressBar.setVisibility(View.GONE);
                    }
                },
                error -> {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(this, getString(R.string.error_al_cargar_datos), Toast.LENGTH_LONG).show();
                }
        );

        queue.add(jsonArrayRequest);
    }
}