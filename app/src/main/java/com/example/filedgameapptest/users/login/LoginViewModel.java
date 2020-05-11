package com.example.filedgameapptest.users.login;

import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.filedgameapptest.R;
import com.example.filedgameapptest.users.register.RegisterFormState;


public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private String email;
    private String password;

    LiveData<LoginFormState> getLoginFormState(){
        return loginFormState;
    }

    public void loginDataChanged(String email, String password){
        if(!isEmailValid(email)){
            loginFormState.setValue(new LoginFormState(R.string.invalid_email, null));
        } else if(!isPasswordValid(password)){
            loginFormState.setValue(new LoginFormState(null,R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    private boolean isEmailValid(String email) {

        if(email == null || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return false;
        } else{

            return !email.trim().isEmpty();
        }
    }

    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}
