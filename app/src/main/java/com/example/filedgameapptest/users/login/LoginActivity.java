package com.example.filedgameapptest.users.login;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.filedgameapptest.MainActivity;
import com.example.filedgameapptest.R;
import com.example.filedgameapptest.apiconnections.RetrofitClientInstance;
import com.example.filedgameapptest.apiconnections.UserService;
import com.example.filedgameapptest.users.register.NewUserDataModel;
import com.example.filedgameapptest.users.register.RegisterActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private TextWatcher afterTextChangedListener;
    private Button btnSignIn;
    private AlertDialog.Builder alert;
    private LoginViewModel loginViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();

        loginViewModel = new LoginViewModel();

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(LoginFormState loginFormState) {
                if(loginFormState == null){
                    return;
                }
                btnSignIn.setEnabled(loginFormState.isDataValid());
                if(loginFormState.getEmailError() != null){
                    emailEditText.setError(getString(loginFormState.getEmailError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
                if(loginFormState.isDataValid()){
                    btnSignIn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String email = emailEditText.getText().toString();
                            String password = passwordEditText.getText().toString();
                            LoginUserDTO loginUserDTO = new LoginUserDTO(password, email);
                            Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
                            UserService userService = retrofit.create(UserService.class);
                            Call<NewUserDataModel> call = userService.login(loginUserDTO);
                            call.enqueue(new Callback<NewUserDataModel>() {
                                @Override
                                public void onResponse(Call<NewUserDataModel> call, Response<NewUserDataModel> response) {
                                    if (response.isSuccessful()) {
                                        NewUserDataModel loggedUser = response.body();
                                        //^tutaj  masz dane usera
                                        showAlertDialogOnSuccess();
                                    } else {
                                        showAlertDialogOnFailure();
                                    }
                                }
                                @Override
                                public void onFailure(Call<NewUserDataModel> call, Throwable t) {
                                    showAlertDialogOnFailure();
                                }
                            });
                            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        setTextWatcher();


    }

    private void initViews() {
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);

        btnSignIn = findViewById(R.id.signIn);

    }

    private void setTextWatcher() {
        afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(emailEditText.getText().toString(),
                        passwordEditText.getText().toString()
                );
            }
        };

        emailEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);

    }
    private void showAlertDialogOnSuccess(){
        //TODO: implement this
//        alert = new AlertDialog.Builder(this).setMessage("Successfully sign up");
//        alert.setPositiveButton("Log in", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                startActivity( new Intent(RegisterActivity.this, LoginActivity.class));
//            }
//        });
//        alert.setNegativeButton("Go back to menu", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                startActivity( new Intent(RegisterActivity.this, MainActivity.class));
//            }
//        });
//        alert.show();
    }

    private void showAlertDialogOnFailure(){
        //TODO: implement this
//        alert = new AlertDialog.Builder(this).setMessage("Failed to sign up");
//        alert.setPositiveButton("Go back to menu", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                startActivity( new Intent(RegisterActivity.this, LoginActivity.class));
//            }
//        });
//        alert.setNegativeButton("Try again", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(getApplicationContext(), "Please try again", Toast.LENGTH_SHORT).show();
//            }
//        });
//        alert.show();
    }
}
