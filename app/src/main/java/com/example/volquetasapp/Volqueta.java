package com.example.volquetasapp;

public class Volqueta {
    private int id;
    private String placa;
    private String dispositivoId;
    private String estado;
    private String createdAt;

    public Volqueta(int id, String placa, String dispositivoId, String estado, String createdAt) {
        this.id = id;
        this.placa = placa;
        this.dispositivoId = dispositivoId;
        this.estado = estado;
        this.createdAt = createdAt;
    }

    public int getId() { return id; }
    public String getPlaca() { return placa; }
    public String getDispositivoId() { return dispositivoId; }
    public String getEstado() { return estado; }
    public String getCreatedAt() { return createdAt; }
}