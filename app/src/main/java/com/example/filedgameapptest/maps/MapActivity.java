package com.example.filedgameapptest.maps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.filedgameapptest.MainActivity;
import com.example.filedgameapptest.R;
import com.example.filedgameapptest.apiconnections.RetrofitClientInstance;
import com.example.filedgameapptest.apiconnections.models.GameService;
import com.example.filedgameapptest.apiconnections.models.GameUserDTO;
import com.example.filedgameapptest.maps.data.GameDataRepository;
import com.example.filedgameapptest.maps.data.MapDataRepository;
import com.example.filedgameapptest.users.account.UserAccountActivity;
import com.example.filedgameapptest.users.data.UserDataRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MapActivity extends AppCompatActivity implements View.OnClickListener {

    private UserDataRepository userDataRepository = UserDataRepository.getInstance();
    private GameUserDTO gameUserDTO;
    private MapDataRepository mapDataRepository = MapDataRepository.getInstance();
    private GameDataRepository gameDataRepository = GameDataRepository.getInstance();

    private FloatingActionButton btnCamera;
    private FloatingActionButton btnEndGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        initViews();
        addNewUserGameToUser();
    }

    private void initViews() {

        btnCamera = findViewById(R.id.btnCamera);
        btnEndGame = findViewById(R.id.btnEndGame);

        btnEndGame.setOnClickListener(this);
        btnCamera.setOnClickListener(this);

    }

    private void addNewUserGameToUser() {
        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        GameService gameService = retrofit.create(GameService.class);
        Call<GameUserDTO> call = gameService.registerUser(userDataRepository.getId(), mapDataRepository.getId());
        call.enqueue(new Callback<GameUserDTO>() {
            @Override
            public void onResponse(Call<GameUserDTO> call, Response<GameUserDTO> response) {
                if(response.isSuccessful()) {
                    gameUserDTO = response.body();
                    setNewGame();
                }
            }
            @Override
            public void onFailure(Call<GameUserDTO> call, Throwable t) {
                System.out.println(t.toString());
            }
        });

    }

    private void setNewGame() {
        gameDataRepository.setAllData(gameUserDTO.getMapId(),gameUserDTO.getUserId(),gameUserDTO.getPoints(), gameUserDTO.isActive());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCamera:
                runCamera();
                break;
            case R.id.btnEndGame:
                startActivity(new Intent(this, EndGameActivity.class));
                break;
        }
    }

    private void runCamera() {
        //TODO: Monika to implement launching camera
        Toast.makeText(getApplicationContext(), "Not implemented", Toast.LENGTH_SHORT).show();
    }
}
