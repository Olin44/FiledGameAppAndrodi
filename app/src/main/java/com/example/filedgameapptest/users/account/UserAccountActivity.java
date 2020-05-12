package com.example.filedgameapptest.users.account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.filedgameapptest.MainActivity;
import com.example.filedgameapptest.R;
import com.example.filedgameapptest.maps.ScannedBarcodeActivity;
import com.example.filedgameapptest.users.login.LoginActivity;
import com.example.filedgameapptest.users.register.NewUserDataModel;
import com.example.filedgameapptest.users.register.RegisterActivity;

import java.util.Observable;
import java.util.Observer;

public class UserAccountActivity extends AppCompatActivity implements Observer, View.OnClickListener{

    private TextView emailTextView;
    private TextView usernameTextView;

    private Button btnShowStats;
    private Button btnLogOut;

    private Observable mUserDataRepositoryObservable;
    private UserDataRepository userDataRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);

        initViews();

        mUserDataRepositoryObservable = UserDataRepository.getInstance();
        userDataRepository = UserDataRepository.getInstance();
        setTextViews();
        mUserDataRepositoryObservable.addObserver(this);

    }

    private void  setTextViews(){
        emailTextView.setText(userDataRepository.getEmail());
        usernameTextView.setText(userDataRepository.getUsername());
    }

    private void initViews() {
        emailTextView = findViewById(R.id.emailTextView);
        usernameTextView = findViewById(R.id.usernameTextView);

        btnShowStats = findViewById(R.id.btnShowStats);
        btnShowStats.setOnClickListener(this);
        btnLogOut = findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnShowStats:
                //TODO Implement ShowStatsActivity
                Toast.makeText(getApplicationContext(), "Stats", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnLogOut:
                //TODO Implement LogOut
                Toast.makeText(getApplicationContext(), "Logged out", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof UserDataRepository) {
            UserDataRepository userDataRepository = (UserDataRepository) observable;
            emailTextView.setText(userDataRepository.getEmail());
            usernameTextView.setText(userDataRepository.getUsername());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUserDataRepositoryObservable.deleteObserver(this);
    }
}
