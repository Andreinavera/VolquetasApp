package com.example.volquetasapp;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface VolquetaApi {
    @GET("volquetas")
    Call<List<Volqueta>> getVolquetas();
}