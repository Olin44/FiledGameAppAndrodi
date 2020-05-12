package com.example.filedgameapptest.apiconnections.models;

import com.example.filedgameapptest.users.register.NewUserDataModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GameService {
    @GET("addNewUserGameToUser/{userId}/{mapId}")
    Call<GameUserDTO> registerUser(@Path("userId") String userId, @Path("mapId") String mapId);
}
