package com.example.filedgameapptest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonOne = findViewById(R.id.button);
        buttonOne.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
                MapsService mapsService = retrofit.create(MapsService.class);
                Call<List<Map>> call = mapsService.getAllMaps();
                call.enqueue(new Callback<List<Map>>() {
                    @Override
                    public void onResponse(Call<List<Map>> call, Response<List<Map>> response){
                        System.out.println("sex");
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
