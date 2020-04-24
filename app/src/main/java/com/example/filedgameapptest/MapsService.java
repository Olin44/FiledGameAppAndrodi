package com.example.filedgameapptest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MapsService {
    @GET("controllers/allMaps")
    Call<List<Map>> getAllMaps();
}

