package com.example.filedgameapptest.maps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.filedgameapptest.apiconnections.BaseURL;

import com.example.filedgameapptest.apiconnections.models.GameService;
import com.example.filedgameapptest.apiconnections.models.GameUserDTO;
import com.example.filedgameapptest.apiconnections.models.Map;
import com.example.filedgameapptest.apiconnections.MapsService;
import com.example.filedgameapptest.R;
import com.example.filedgameapptest.apiconnections.RetrofitClientInstance;
import com.example.filedgameapptest.users.account.UserDataRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class StartMapActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnRequest;
    private Button btnStartNewMapActivity;
    private TextView txtResponse;
    private String url;
    private Map map;
    private UserDataRepository userDataRepository = UserDataRepository.getInstance();
    private GameUserDTO gameUserDTO;
    //TODO: tak wygląda ten obiekt {
    //  "mapId": "string",
    //  "userId": "string",
    //  "points": string,
    //  "active": true
    //}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_map);
        Intent incomingIntent = getIntent();
        url = incomingIntent.getStringExtra("url");

        initViews();
        txtResponse.setText(url);
        requestMap();
        addNewUserGameToUser();

    }
    private void addNewUserGameToUser() {
        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        GameService gameService = retrofit.create(GameService.class);
        Call<GameUserDTO> call = gameService.registerUser(userDataRepository.getId(), map.getId());
        call.enqueue(new Callback<GameUserDTO>() {
            @Override
            public void onResponse(Call<GameUserDTO> call, Response<GameUserDTO> response) {
                if(response.isSuccessful()){
                    gameUserDTO = response.body();
                }
                //:TODO dorób tu obsługę tego jak jest git to niech co się dzieje, a jak nie to się pojawia jakiś monit typu tryAgain
            }
            @Override
            public void onFailure(Call<GameUserDTO> call, Throwable t) {
                System.out.println(t.toString());
                //:TODO tutaj też ten monit
            }
        });

    }

    private void requestMap() {
        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        MapsService mapsService = retrofit.create(MapsService.class);
        String mapId = url.replace(BaseURL.baseURL + "maps/getMapById/", "");
        Call<Map> call = mapsService.getMapById(mapId);
        call.enqueue(new Callback<Map>() {
            @Override
            public void onResponse(Call<Map> call, Response<Map> response) {
                map = response.body();
                String responseAsString = map.toString();
                txtResponse.setText(responseAsString);
            }

            @Override
            public void onFailure(Call<Map> call, Throwable t) {
                System.out.println(t.toString());
            }
        });

    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnRequest:
                break;
            case R.id.btnStartNewMapActivity:
                startActivity(new Intent(StartMapActivity.this, MapsActivity.class));
                break;

        }

    }

    private void initViews() {
        btnRequest = findViewById(R.id.btnRequest);
        btnStartNewMapActivity = findViewById(R.id.btnStartNewMapActivity);
        txtResponse = findViewById(R.id.txtResponse);

        btnRequest.setOnClickListener(this);
        btnStartNewMapActivity.setOnClickListener(this);

    }


}
