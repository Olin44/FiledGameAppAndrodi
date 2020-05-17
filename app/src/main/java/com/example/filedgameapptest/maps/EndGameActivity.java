package com.example.filedgameapptest.maps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.filedgameapptest.MainActivity;
import com.example.filedgameapptest.R;
import com.example.filedgameapptest.maps.data.GameDataRepository;
import com.example.filedgameapptest.users.account.UserAccountActivity;
import com.example.filedgameapptest.users.login.LoginActivity;
import com.example.filedgameapptest.users.register.RegisterActivity;

public class EndGameActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnStartNewMap;
    private Button btnAccount;
    private Button btnLogOut;

    private TextView txtEndMessage;
    private TextView txtResult;

    private GameDataRepository gameDataRepository = GameDataRepository.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
        initViews();
        setTextViews();

        endGame();
    }

    private void setTextViews() {
        //TODO: Establish end messages based on the result?
        txtResult.setText("Congrats! You finished the game!");
        txtEndMessage.setText(String.format("Points: %s", gameDataRepository.getPoints().toString()));
    }

    private void initViews() {
        btnStartNewMap = findViewById(R.id.btnStartNewMap);
        btnAccount = findViewById(R.id.btnAccount);
        btnLogOut = findViewById(R.id.btnLogOut);

        txtEndMessage = findViewById(R.id.txtEndMessage);
        txtResult = findViewById(R.id.txtResult);

        btnStartNewMap.setOnClickListener(this);
        btnAccount.setOnClickListener(this);
        btnLogOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogOut:
                LogOut();
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.btnAccount:
                startActivity(new Intent(this, UserAccountActivity.class));
                break;
            case R.id.btnStartNewMap:
                startActivity(new Intent(this, StartMapActivity.class));
                break;
        }
    }

    private void LogOut() {
        //TODO: Jacek to implement logout and clear data
    }

    private void endGame(){
        //TODO Jacek to implement sending data (gameDataRepository) to server and end game
    }
}
