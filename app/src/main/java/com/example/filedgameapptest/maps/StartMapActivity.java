package com.example.filedgameapptest.maps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.filedgameapptest.MainActivity;
import com.example.filedgameapptest.R;
import com.example.filedgameapptest.users.login.ui.login.LoginActivity;
import com.example.filedgameapptest.users.register.RegisterActivity;

public class StartMapActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnRequest;
    private Button btnStartNewMapActivity;
    private TextView txtResponse;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_map);
        Intent incomingIntent = getIntent();
        url = incomingIntent.getStringExtra("url");

        initViews();
        txtResponse.setText(url);


    }

    private void RequestMap() {
        //
        //tu sobie piszesz co chcesz zrobić na klikniecie button
        //

        //tym możesz sobie wyświetlić wynik
        String response = "";
        txtResponse.setText(response);

        //Chciałabym aby na request (który się będzie dział automatycznie a nie na kliknięcie) pobierało mape i tworzylo mi liste obiektow na mapie
        // pozniej po kliknieciu 'start' bedzie przechodzilo do aktywnosci juz z mapa google (MapsActivity) i lokalizacja obiektu

    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnRequest:
                RequestMap();
                break;
            case R.id.btnStartNewMapActivity:
                //startActivity(new Intent(StartMapActivity.this, MapsActivity.class));
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
