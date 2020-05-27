package com.example.filedgameapptest.apiconnections;

import com.example.filedgameapptest.util.BaseURL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
    Klasa, w której zdefiniowany jest obiekt RetrofitClient, umożliwiający łączenie się z serwerem z REST API.
 */
public class RetrofitClientInstance {
    /**
     Pole z instancją klasy Retrofit.
     */
    private static Retrofit retrofit;
    /**
     Pole, w którym znajduje się adres do serwera z REST API.
     */
    private static final String BASE_URL = BaseURL.baseURL;
    /**
     Metoda służaca do pobrania instancji klasy Retrofit.
     @return Retrofit obiekt klasy Retrofit
     */
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}