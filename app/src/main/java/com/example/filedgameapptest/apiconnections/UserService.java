package com.example.filedgameapptest.apiconnections;

import com.example.filedgameapptest.users.account.LogoutUserDTO;
import com.example.filedgameapptest.users.login.LoginUserDTO;
import com.example.filedgameapptest.users.register.NewUserDataModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("/users/registerUser")
    Call<NewUserDataModel> registerUser(@Body NewUserDataModel newUserDataModel);

    @POST("/users/login")
    Call<NewUserDataModel> login(@Body LoginUserDTO newUserDataModel);

    @POST("/users/logout")
    Call<LogoutUserDTO> logout(@Body LogoutUserDTO logoutUserDTO);
}