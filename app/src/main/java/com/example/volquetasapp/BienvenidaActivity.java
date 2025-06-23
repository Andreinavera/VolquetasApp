package com.example.volquetasapp;

import static android.os.Build.VERSION_CODES.R;
import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;

import missing.namespace.R;

public class BienvenidaActivity extends AppCompatActivity {

    private static final int TIEMPO_SPLASH = 3000; // 3 segundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(BienvenidaActivity.this, ListaVolquetasActivity.class);
            startActivity(intent);
            finish();
        }, TIEMPO_SPLASH);
    }
}