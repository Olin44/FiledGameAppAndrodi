package com.example.filedgameapptest.users.stats;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.filedgameapptest.R;
import com.example.filedgameapptest.apiconnections.RetrofitClientInstance;
import com.example.filedgameapptest.apiconnections.models.GameService;
import com.example.filedgameapptest.users.data.UserDataRepository;

import org.w3c.dom.Text;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

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
        Call<List<UsersStatsDTO>> call = gameService.getUserResult(userDataRepository.getId());
        System.out.println(userDataRepository.getId());
        call.enqueue(new Callback<List<UsersStatsDTO>>() {
            @Override
            public void onResponse(Call<List<UsersStatsDTO>> call, Response<List<UsersStatsDTO>> response) {
                if (response.isSuccessful()) {
                    int gamesCounter = 1;
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", java.util.Locale.getDefault());
                    for(UsersStatsDTO stats : response.body()){
                        String statString = gamesCounter + ". " +
                                stats.mapName + ", "+
                                simpleDateFormat.format(stats.endGame) + ", "+
                                "Points: " + stats.points + "." + System.getProperty("line.separator");
                        txtStats.append(statString);
                        gamesCounter++;
                    };

                } else {
                    try {
                        txtStats.append(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<UsersStatsDTO>> call, Throwable t) {
                //TODO:implementacja
            }
        });
    }
}