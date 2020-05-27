package com.example.filedgameapptest.apiconnections;

import com.example.filedgameapptest.apiconnections.models.Map;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
/**
 * Interfejs służący do łączenia się z serwerem z REST API używając biblioteki Retrofit i pobrania informacji o mapach.
 */
public interface MapsService {
    /**
     * Metoda pozwalająca na pobranie infromacji o wszystkich mapach.
     * @return zwraca obiekt Call zawierający listę wszystkich map.
     */
    @GET("maps/getAllMaps")
    Call<List<Map>> getAllMaps();
    /**
     * Metoda pozwalająca na pobranie infromacji o mapie o podanym ID.
     * @param  mapID String z ID mapy
     * @return zwraca obiekt Call zawierający mapę.
     */
    @GET("/maps/getMapById/{mapID}")
    Call<Map> getMapById(@Path("mapID") String mapID);
}

