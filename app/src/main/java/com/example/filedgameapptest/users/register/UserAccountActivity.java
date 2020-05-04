package com.example.filedgameapptest.users.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.filedgameapptest.R;

import java.util.Observable;
import java.util.Observer;

public class UserAccountActivity extends AppCompatActivity implements Observer {

    private Intent incomingIntent;
    private TextView emailTextView;
    private TextView usernameTextView;
    private TextView passwordTextView;
    private NewUserModel userModel;
    private String username;
    private String email;
    private String password;
    private Observable mUserDataRepositoryObservable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        setIncomingIntent();
        setDataFromIntent();
        //Builder
        setUserData();
        initViews();

        setTextViews();

        //Observer + Singleton
        mUserDataRepositoryObservable = UserDataRepository.getInstance();
        mUserDataRepositoryObservable.addObserver(this);

    }

    private void setTextViews() {
        emailTextView.setText(userModel.getEmail());
        passwordTextView.setText(userModel.getPassword());
        usernameTextView.setText(userModel.getUsername());
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

    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof UserDataRepository) {
            UserDataRepository userDataRepository = (UserDataRepository) observable;
            emailTextView.setText(userDataRepository.getEmail());
            passwordTextView.setText(userDataRepository.getPassword());
            usernameTextView.setText(userDataRepository.getUsername());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUserDataRepositoryObservable.deleteObserver(this);
    }
}
