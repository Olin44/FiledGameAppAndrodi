package com.example.filedgameapptest.maps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.filedgameapptest.R;

public class EndGameActivity extends AppCompatActivity {

    private Button btnStartNewMap;
    private Button btnAccount;
    private Button btnLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        initViews();
    }

    private void initViews() {
        btnStartNewMap = findViewById(R.id.btnStartNewMap);
        btnAccount = findViewById(R.id.btnAccount);
        btnLogOut = findViewById(R.id.btnLogOut);
    }
}
