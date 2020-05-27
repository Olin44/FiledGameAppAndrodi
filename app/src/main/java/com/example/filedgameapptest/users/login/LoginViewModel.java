package com.example.filedgameapptest.users.login;

import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.filedgameapptest.R;

/**
 *Klasa służąca do walidacji danych wprowadzanych w formularzu logowania.
 */
public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    /**
     * Pole przechowujące email użytkownika.
     */
    private String email;
    /**
     * Pole przechowujące hasło użytkownika.
     */
    private String password;

    /**
     *Metoda pobieająca dane wprowadzone przez użytkownika.
     */
    LiveData<LoginFormState> getLoginFormState(){
        return loginFormState;
    }
    /**
     *Metoda przeprwadzająca walidację danych, gdy dane zostaną zmienione w widoku
     * @param email email użytkownika
     * @param password hasło użytkownika
     */
    public void loginDataChanged(String email, String password){
        if(!isEmailValid(email)){
            loginFormState.setValue(new LoginFormState(R.string.invalid_email, null));
        } else if(!isPasswordValid(password)){
            loginFormState.setValue(new LoginFormState(null,R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }
    /**
     *Metoda przeprwadzająca walidację emaila.
     * @return true, gdy dane są poprawne, false dla niepoprawnych danych
     */
    private boolean isEmailValid(String email) {

        if(email == null || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return false;
        } else{

            return !email.trim().isEmpty();
        }
    }
    /**
     *Metoda przeprwadzająca walidację hasła.
     * @return true, gdy dane są poprawne, false dla niepoprawnych danych
     */
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}
