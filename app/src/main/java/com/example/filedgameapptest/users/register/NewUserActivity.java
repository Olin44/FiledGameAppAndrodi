package com.example.filedgameapptest.users.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.filedgameapptest.R;

public class NewUserActivity extends AppCompatActivity {

    private Intent incomingIntent;
    private TextView emailTextView;
    private TextView usernameTextView;
    private TextView passwordTextView;
    private NewUserModel userModel;
    private String username;
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        setIncomingIntent();
        setDataFromIntent();
        setUserData();

        initViews();
        setTextViews();
    }

    private void setTextViews() {
        emailTextView.setText(userModel.getEmail());
        usernameTextView.setText(userModel.getUsername());
        passwordTextView.setText(userModel.getPassword());
    }

    private void setIncomingIntent() {
        incomingIntent = getIntent();
    }

    private void setDataFromIntent() {
        Bundle data = incomingIntent.getExtras();

        if (data != null) {
            username = (String) data.get("username");
            email = (String) data.get("email");
            password = (String) data.get("password");
        }
    }

    private void setUserData() {
        userModel = new NewUserModel.Builder()
                .setUsername(username)
                .setEmail(email)
                .setPassword(password)
                .setIsActive(false)
                .build();
    }

    private void initViews() {
        emailTextView = findViewById(R.id.emailTextView);
        usernameTextView = findViewById(R.id.usernameTextView);
        passwordTextView = findViewById(R.id.passwordTextView);
    }
}
