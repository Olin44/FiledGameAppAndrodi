package com.example.filedgameapptest.users.register;

import android.text.TextUtils;
import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.filedgameapptest.R;

/**
 *Klasa służąca do walidacji danych wprowadzanych w formularzu rejestracji.
 */
public class RegisterViewModel extends ViewModel {
    private MutableLiveData<RegisterFormState> registerFormState = new MutableLiveData<>();
    /**
     * Pole przechowujące email użytkownika.
     */
    private String email;
    /**
     * Pole przechowujące hasło użytkownika.
     */
    private String password;
    /**
     * Pole przechowujące nazwę użytkownika.
     */
    private String username;
    /**
     *Metoda pobieająca dane wprowadzone przez użytkownika.
     */
    LiveData<RegisterFormState> getRegisterFormState() {
        return registerFormState;
    }

    /**
     *Metoda przeprwadzająca walidację danych, gdy dane zostaną zmienione w widoku
     * @param email email użytkownika
     * @param username nazwa użytkownika
     * @param password hasło użytkownika
     * @param passwordConf hasło wpisane drugi raz dla potwierdzenia poprawności
     */
    public void registerDataChanged(String email, String username, String password, String passwordConf) {
        if (!isEmailValid(email)) {
            registerFormState.setValue(new RegisterFormState(R.string.invalid_email, null, null, null));
        } else if (!isUserNameValid(username)) {
            registerFormState.setValue(new RegisterFormState(null, R.string.invalid_username, null, null));
        } else if (!isPasswordValid(password)) {
            registerFormState.setValue(new RegisterFormState(null, null, R.string.invalid_password, null));
        } else if (!isPasswordConfValid(password, passwordConf)) {
            registerFormState.setValue(new RegisterFormState(null, null, null, R.string.invalid_passwordConf));
        } else {
            registerFormState.setValue(new RegisterFormState(true));
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
     *Metoda przeprwadzająca walidację nazwy użytkownika
     * @return true, gdy dane są poprawne, false dla niepoprawnych danych
     */
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        } else {
            return !username.trim().isEmpty();
        }
    }
    /**
     *Metoda przeprwadzająca walidację hasła.
     * @return true, gdy dane są poprawne, false dla niepoprawnych danych
     */
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
    /**
     *Metoda przeprwadzająca walidację ponownie wpisanego hasła.
     * @return true, gdy dane są poprawne, false dla niepoprawnych danych
     */
    private boolean isPasswordConfValid(String password, String passwordConf) {
        return password.equals(passwordConf);
    }
}
