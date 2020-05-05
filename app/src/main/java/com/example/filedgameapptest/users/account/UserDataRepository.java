package com.example.filedgameapptest.users.account;

import android.os.Handler;

import java.util.Observable;

public class UserDataRepository extends Observable {
    private String username;
    private String email;
    private String password;
    private static UserDataRepository INSTANCE = null;

    private UserDataRepository() {
        getNewDataFromRemote();
    }

    public static UserDataRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserDataRepository();
        }
        return INSTANCE;
    }

    // Simulate API
    private void getNewDataFromRemote() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                setUserData("changed", "data", "observer");
            }
        }, 5000);
    }

    public void setUserData(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        setChanged();
        notifyObservers();
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
