package com.example.filedgameapptest.apiconnections.models;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GameService {
    @GET("addNewUserGameToUser/{userId}/{mapId}")
    Call<GameUserDTO> registerUser(@Path("userId") String userId, @Path("mapId") String mapId);
}
