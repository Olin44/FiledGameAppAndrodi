package com.example.filedgameapptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.filedgameapptest.maps.ScannedBarcodeActivity;
import com.example.filedgameapptest.users.login.LoginActivity;
import com.example.filedgameapptest.users.register.RegisterActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnScanBarcode;
    private Button btnLogIn;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

    }

    private void initViews() {
        btnScanBarcode = findViewById(R.id.btnScanBarcode);
        btnLogIn = findViewById(R.id.btnLogIn);
        btnSignUp = findViewById(R.id.btnSignUpActivity);

        btnScanBarcode.setOnClickListener(this);
        btnLogIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnScanBarcode:
                startActivity(new Intent(MainActivity.this, ScannedBarcodeActivity.class));
                break;
            case R.id.btnLogIn:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                break;
            case R.id.btnSignUpActivity:
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                break;
        }

    }
}
