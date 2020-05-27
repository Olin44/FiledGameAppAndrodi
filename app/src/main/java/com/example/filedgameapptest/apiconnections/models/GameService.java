package com.example.filedgameapptest.apiconnections.models;

import com.example.filedgameapptest.users.stats.UsersStatsDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Interfejs służący do łączenia się z serwerem z REST API używając biblioteki Retrofit i pobrania informacji o grach.
 */
public interface GameService {
    /**
     * Metoda pozwalająca na dodanie nowej gry do użytkownika, wykonując request GET do serwerem z REST API
     * @param userId identyfikator użytkownika
     * @param mapId identyfikator mapy
     * @return zwraca obiekt Call<GameUserDTO>, w którym znajdują się dane dodanej do bazy gry
     */
    @GET("/game/addNewUserGameToUser/{userId}/{mapId}")
    Call<GameUserDTO> registerUser(@Path("userId") String userId, @Path("mapId") String mapId);
    /**
     * Metoda pozwalająca na dodanie nowej gry do użytkownika, wykonując request do serwerem z REST API
     * @param gameUserDTO obiekt klasy GameUserDTO wypełniony danymi gry, które zostaną zapisane w bazie
     * @return zwraca obiekt Call<GameUserDTO>, w którym znajdują się dane zapisanej gry
     */
    @POST("/game/saveResults")
    Call<GameUserDTO> saveResults(@Body GameUserDTO gameUserDTO);
    /**
     * Metoda zwracająca wyniki gracza o danym ID
     * @param userId identyfikator użytkownika, którego dane chcemy pobrać
     * @return zwraca obiekt Call<GameUserDTO>, w którym znajdują się dane o grze użytkownika
     */
    @GET("/game/getUserResult/{userId}")
    Call<UsersStatsDTO> getUserResult(@Path("userId") String userId);
}
