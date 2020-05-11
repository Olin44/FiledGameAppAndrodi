package com.example.filedgameapptest.apiconnections;

import com.example.filedgameapptest.users.register.NewUserDataModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("/users/registerUser")
    Call<NewUserDataModel> registerUser(@Body NewUserDataModel newUserDataModel);
}
