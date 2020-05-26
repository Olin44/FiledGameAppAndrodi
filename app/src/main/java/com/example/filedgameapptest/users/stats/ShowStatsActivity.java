package com.example.filedgameapptest.users.stats;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.filedgameapptest.apiconnections.RetrofitClientInstance;
import com.example.filedgameapptest.apiconnections.UserService;
import com.example.filedgameapptest.apiconnections.models.GameService;
import com.example.filedgameapptest.users.data.NewUserDataModel;
import com.example.filedgameapptest.users.data.UserDataRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ShowStatsActivity extends AppCompatActivity {

    UserDataRepository userDataRepository = UserDataRepository.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getUserStats();
    }

    private void getUserStats() {
        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        GameService gameService = retrofit.create(GameService.class);
        Call<UsersStatsDTO> call = gameService.getUserResult(userDataRepository.getId());
        System.out.println(userDataRepository.getId());
        call.enqueue(new Callback<UsersStatsDTO>() {
            @Override
            public void onResponse(Call<UsersStatsDTO> call, Response<UsersStatsDTO> response) {
                if (response.isSuccessful()) {
                    System.out.println("dupa0");
                    System.out.println(response.body().toString());
                } else {
                    System.out.println("dupa56454");
                    System.out.println(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<UsersStatsDTO> call, Throwable t) {
                //TODO:implementacja
            }
        });
    }
}
