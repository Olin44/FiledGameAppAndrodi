package com.example.filedgameapptest.apiconnections;

import com.example.filedgameapptest.apiconnections.models.Map;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MapsService {
    @GET("maps/getAllMaps")
    Call<List<Map>> getAllMaps();

    @GET("/maps/getMapById/{mapID}")
    Call<Map> getMapById(@Path("mapID") String mapID);
}

