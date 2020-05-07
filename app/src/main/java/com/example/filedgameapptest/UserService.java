package com.example.filedgameapptest;

import com.example.filedgameapptest.users.register.NewUserDataModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("/users/registerUser")
    Call<Boolean> registerUser(@Body NewUserDataModel newUserDataModel);
}
