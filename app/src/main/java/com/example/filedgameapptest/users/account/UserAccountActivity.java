package com.example.filedgameapptest.users.account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.filedgameapptest.R;
import com.example.filedgameapptest.users.register.NewUserDataModel;

import java.util.Observable;
import java.util.Observer;

public class UserAccountActivity extends AppCompatActivity implements Observer {

    private Intent incomingIntent;
    private TextView emailTextView;
    private TextView usernameTextView;
    private TextView passwordTextView;
    private NewUserDataModel userModel;
    private Observable mUserDataRepositoryObservable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);

        setIncomingIntent();
        setDataFromIntent();

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
        userModel = (NewUserDataModel) incomingIntent.getSerializableExtra("userModel");
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
