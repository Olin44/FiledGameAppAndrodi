package com.example.filedgameapptest.users.register;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.filedgameapptest.MainActivity;
import com.example.filedgameapptest.MapsService;
import com.example.filedgameapptest.R;
import com.example.filedgameapptest.RetrofitClientInstance;
import com.example.filedgameapptest.UserService;
import com.example.filedgameapptest.users.account.UserAccountActivity;
import com.example.filedgameapptest.users.login.LoginActivity;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {

    private RegisterViewModel registerViewModel;
    private Button btnSignUp;
    private EditText emailEditText;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText passwordConfEditText;
    private TextWatcher afterTextChangedListener;
    private Boolean isUserRegistered;
    private AlertDialog.Builder alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerViewModel = new RegisterViewModel();
        initViews();

        registerViewModel.getRegisterFormState().observe(this, new Observer<RegisterFormState>() {
            @Override
            public void onChanged(@Nullable RegisterFormState registerFormState) {
                if (registerFormState == null) {
                    return;
                }
                btnSignUp.setEnabled(registerFormState.isDataValid());
                if (registerFormState.getEmailError() != null) {
                    emailEditText.setError(getString(registerFormState.getEmailError()));
                }
                if (registerFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(registerFormState.getUsernameError()));
                }
                if (registerFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(registerFormState.getPasswordError()));
                }
                if (registerFormState.getPasswordConfError() != null) {
                    passwordConfEditText.setError(getString(registerFormState.getPasswordConfError()));
                }
                if (registerFormState.isDataValid()) {

                    btnSignUp.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String username = usernameEditText.getText().toString();
                            String email = emailEditText.getText().toString();
                            String password = passwordEditText.getText().toString();
                            NewUserDataModel userModel = new NewUserDataModel.Builder()
                                    .setUsername(username)
                                    .setEmail(email)
                                    .setPassword(password)
                                    .setIsActive(false)
                                    .build();
                            isUserRegistered = registerUser(userModel);
                            if(isUserRegistered){
                                showAlertDialogOnSuccess();
                            } else{
                                showAlertDialogOnFailure();
                            }
                        }
                    });
                }

            }
        });
        setTextWatcher();

    }
    private Boolean registerUser(NewUserDataModel userModel) {
        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        UserService userService = retrofit.create(UserService.class);
        Call<Boolean> call = userService.registerUser(userModel);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                isUserRegistered = response.body();
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                isUserRegistered = false;
                System.out.println(t.toString());
            }
        });
        return isUserRegistered;
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
                registerViewModel.registerDataChanged(emailEditText.getText().toString(),
                        usernameEditText.getText().toString(),
                        passwordEditText.getText().toString(),
                        passwordConfEditText.getText().toString()
                );
            }
        };

        emailEditText.addTextChangedListener(afterTextChangedListener);
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordConfEditText.addTextChangedListener(afterTextChangedListener);
    }


    private void initViews() {
        emailEditText = findViewById(R.id.email);
        usernameEditText = findViewById(R.id.username);
        ;
        passwordEditText = findViewById(R.id.password);
        ;
        passwordConfEditText = findViewById(R.id.passwordConf);
        ;

        btnSignUp = findViewById(R.id.btnSignUp);

    }

    private void showAlertDialogOnSuccess(){
        alert = new AlertDialog.Builder(this).setMessage("Successfully sign up");
        alert.setPositiveButton("Log in", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity( new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
        alert.setNegativeButton("Go back to menu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity( new Intent(RegisterActivity.this, MainActivity.class));
            }
        });
        alert.show();
    }

    private void showAlertDialogOnFailure(){
        alert = new AlertDialog.Builder(this).setMessage("Failed to sign up");
        alert.setPositiveButton("Go back to menu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity( new Intent(RegisterActivity.this, LoginActivity.class));
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
