package com.example.filedgameapptest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ApiConnection {

    private Button buttonGetAllMaps;

    protected void onCreate(Bundle savedInstanceState) {

        //buttonGetAllMaps = findViewById(R.id.button);
        buttonGetAllMaps.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
                MapsService mapsService = retrofit.create(MapsService.class);
                Call<List<Map>> call = mapsService.getAllMaps();
                call.enqueue(new Callback<List<Map>>() {
                    @Override
                    public void onResponse(Call<List<Map>> call, Response<List<Map>> response) {
                        System.out.println(response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<List<Map>> call, Throwable t) {
                        System.out.println(t.toString());
                    }
                });
            }
        });
    }
}
