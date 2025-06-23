package com.example.volquetasapp;

import static android.os.Build.VERSION_CODES.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import missing.namespace.R;

public class VolquetaAdapter extends RecyclerView.Adapter<VolquetaAdapter.VolquetaViewHolder> {

    private final List<Volqueta> volquetas;

    public VolquetaAdapter(List<Volqueta> volquetas) {
        this.volquetas = volquetas;
    }

    @NonNull
    @Override
    public VolquetaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_volqueta, parent, false);
        return new VolquetaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VolquetaViewHolder holder, int position) {
        Volqueta volqueta = volquetas.get(position);
        holder.tvPlaca.setText("Placa: " + volqueta.getPlaca());
        holder.tvMarca.setText("Marca: " + volqueta.getMarca());
        holder.tvCapacidad.setText("Capacidad: " + volqueta.getCapacidadM3());
        holder.tvEstado.setText("Estado: " + volqueta.getEstado());
    }

    @Override
    public int getItemCount() {
        return volquetas.size();
    }

    static class VolquetaViewHolder extends RecyclerView.ViewHolder {
        TextView tvPlaca, tvMarca, tvCapacidad, tvEstado;

        public VolquetaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPlaca = itemView.findViewById(R.id.tvPlaca);
            tvMarca = itemView.findViewById(R.id.tvMarca);
            tvCapacidad = itemView.findViewById(R.id.tvCapacidad);
            tvEstado = itemView.findViewById(R.id.tvEstado);
        }
    }
}