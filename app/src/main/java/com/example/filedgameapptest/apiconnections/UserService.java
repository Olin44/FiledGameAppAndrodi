package com.example.filedgameapptest.apiconnections;

import com.example.filedgameapptest.users.data.LogoutUserDTO;
import com.example.filedgameapptest.users.data.LoginUserDTO;
import com.example.filedgameapptest.users.data.NewUserDataModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
/**
 * Interfejs służący do łączenia się z serwerem z REST API używając biblioteki Retrofit
 * i pobrania oraz edycji informacji o użytkownikach.
 */
public interface UserService {
    /**
     * Metoda służaca do zarejestrowania użytkownika.
     * @param newUserDataModel obiekt zawierający dane wpisane przez użytkownika.
     * @return Call<NewUserDataModel> obiekt Call zwierający obiekt NewUserDataModel z danymi użytkownika jakie zostały zapisane na bazie
     */
    @POST("/users/registerUser")
    Call<NewUserDataModel> registerUser(@Body NewUserDataModel newUserDataModel);
    /**
     * Metoda służaca do zalogowania użytkownika.
     * @param newUserDataModel obiekt zawierający dane wpisane przez użytkownika.
     * @return Call<NewUserDataModel> obiekt Call zwierający obiekt NewUserDataModel z danymi użytkownika jakie zostały zapisane na bazie
     */
    @POST("/users/login")
    Call<NewUserDataModel> login(@Body LoginUserDTO newUserDataModel);
    /**
     * Metoda służaca do zalogowania użytkownika.
     * @param logoutUserDTO obiekt zawierający dane wpisane przez użytkownika.
     * @return Call<logoutUserDTO> obiekt Call zwierający obiekt logoutUserDTO z danymi użytkownika jakie zostały zapisane na bazie
     */
    @POST("/users/logout")
    Call<LogoutUserDTO> logout(@Body LogoutUserDTO logoutUserDTO);
}
