package com.example.volquetasapp;

public class Volqueta {
    private final String placa;
    private final String marca;
    private final double capacidadM3;
    private final String estado;

    public Volqueta(String placa, String marca, double capacidadM3, String estado) {
        this.placa = placa;
        this.marca = marca;
        this.capacidadM3 = capacidadM3;
        this.estado = estado;
    }

    public String getPlaca() { return placa; }
    public String getMarca() { return marca; }
    public String getCapacidadM3() { return capacidadM3 + " m³"; }
    public String getEstado() { return estado; }
}