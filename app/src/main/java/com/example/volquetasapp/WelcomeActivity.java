package com.miapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class WelcomeActivity extends AppCompatActivity {

    private MaterialButton btnVerVolquetas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btnVerVolquetas = findViewById(R.id.btnVerVolquetas);
        btnVerVolquetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Al pulsar, abre la lista de volquetas
                Intent intent = new Intent(WelcomeActivity.this, VolquetasActivity.class);
                startActivity(intent);
            }
        });
    }
}
