package com.example.volquetasapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class VolquetaAdapter extends RecyclerView.Adapter<VolquetaAdapter.ViewHolder> {
    private List<Volqueta> volquetas;

    public VolquetaAdapter(List<Volqueta> volquetas) {
        this.volquetas = volquetas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_volqueta, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Volqueta volqueta = volquetas.get(position);
        holder.tvPlaca.setText("Placa: " + volqueta.getPlaca());
        holder.tvDispositivoId.setText("Dispositivo: " + volqueta.getDispositivoId());
        holder.tvEstado.setText("Estado: " + volqueta.getEstado());
    }

    @Override
    public int getItemCount() {
        return volquetas != null ? volquetas.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPlaca, tvDispositivoId, tvEstado;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPlaca = itemView.findViewById(R.id.tvPlaca);
            tvDispositivoId = itemView.findViewById(R.id.tvDispositivoId);
            tvEstado = itemView.findViewById(R.id.tvEstado);
        }
    }
}