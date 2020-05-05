package com.example.filedgameapptest.maps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.filedgameapptest.R;

public class StartMapActivity extends AppCompatActivity {

    private Button btnRequest;
    private TextView txtResponse;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_map);
        Intent incomingIntent = getIntent();
        url = incomingIntent.getStringExtra("url");

        initViews();
    }

    private void initViews() {
        btnRequest = findViewById(R.id.btnRequest);
        txtResponse = findViewById(R.id.txtResponse);

        txtResponse.setText(url);


        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //
                //tu sobie piszesz co chcesz zrobić na klikniecie button
                //

                //tym możesz sobie wyświetlić wynik
                String response = "";
                txtResponse.setText(response);

                //Chciałabym aby na request (który się będzie dział automatycznie a nie na kliknięcie) pobierało mape i tworzylo mi liste obiektow na mapie
                // pozniej po kliknieciu 'start' bedzie przechodzilo do aktywnosci juz z mapa google i lokalizacja obiektu


            }
        });
    }
}
