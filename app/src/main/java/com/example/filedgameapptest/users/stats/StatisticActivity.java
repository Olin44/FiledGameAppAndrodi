package com.example.filedgameapptest.users.stats;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.filedgameapptest.R;
import com.example.filedgameapptest.apiconnections.RetrofitClientInstance;
import com.example.filedgameapptest.apiconnections.models.GameService;
import com.example.filedgameapptest.users.data.UserDataRepository;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class StatisticActivity extends AppCompatActivity {

    UserDataRepository userDataRepository = UserDataRepository.getInstance();
    private TextView txtStats;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        initViews();
        getUserStats();
    }

    private void initViews() {
        txtStats = findViewById(R.id.txtStats);
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
                    System.out.println(response.body().toString());

                } else {
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