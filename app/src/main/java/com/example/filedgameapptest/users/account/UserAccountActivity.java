package com.example.filedgameapptest.users.account;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.filedgameapptest.MainActivity;
import com.example.filedgameapptest.R;
import com.example.filedgameapptest.apiconnections.RetrofitClientInstance;
import com.example.filedgameapptest.apiconnections.UserService;
import com.example.filedgameapptest.users.data.LogoutUserDTO;
import com.example.filedgameapptest.users.data.UserDataRepository;
import com.example.filedgameapptest.users.login.LoginActivity;
import com.example.filedgameapptest.users.stats.StatisticActivity;

import java.util.Observable;
import java.util.Observer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserAccountActivity extends AppCompatActivity implements Observer, View.OnClickListener{

    private TextView emailTextView;
    private TextView usernameTextView;

    private Button btnShowStats;
    private Button btnLogOut;

    private Observable mUserDataRepositoryObservable;
    private UserDataRepository userDataRepository;
    private AlertDialog.Builder alert;


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
                startActivity(new Intent(this, StatisticActivity.class));
                break;
            case R.id.btnLogOut:
                LogoutUserDTO logoutUserDTO  = new LogoutUserDTO(userDataRepository.getEmail());
                Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
                UserService userService = retrofit.create(UserService.class);
                Call<LogoutUserDTO> call = userService.logout(logoutUserDTO);
                call.enqueue(new Callback<LogoutUserDTO>() {
                    @Override
                    public void onResponse(Call<LogoutUserDTO> call, Response<LogoutUserDTO> response) {
                        if (response.isSuccessful()) {
                            userDataRepository.deleteAllData();
                            showAlertDialogOnSuccess();
                        } else {
                            showAlertDialogOnFailure();
                        }
                    }
                    @Override
                    public void onFailure(Call<LogoutUserDTO> call, Throwable t) {
                        showAlertDialogOnFailure();
                    }
                });
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

    private void showAlertDialogOnSuccess(){
        alert = new AlertDialog.Builder(this).setMessage("Successfully logout");
        alert.setPositiveButton("Log in", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity( new Intent(UserAccountActivity.this, LoginActivity.class));
            }
        });
        alert.setNegativeButton("Go back to menu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity( new Intent(UserAccountActivity.this, MainActivity.class));
            }
        });
        alert.show();
    }

    private void showAlertDialogOnFailure(){
        alert = new AlertDialog.Builder(this).setMessage("Failed to logout");
        alert.setPositiveButton("Go back to menu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity( new Intent(UserAccountActivity.this, MainActivity.class));
            }
        });
        alert.setNegativeButton("Try again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Please try again", Toast.LENGTH_SHORT).show();
            }
        });
        alert.show();
    }

}
