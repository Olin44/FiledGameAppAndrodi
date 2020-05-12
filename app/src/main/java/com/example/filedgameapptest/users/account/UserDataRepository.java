package com.example.filedgameapptest.users.account;

import android.os.Handler;

import java.util.Observable;

public class UserDataRepository extends Observable {
    private String username;
    private String email;
    private boolean isActive;
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
                setUserData("changed", "data", true);
            }
        }, 500000);
    }

    public void setUserData(String username, String email, Boolean isActive) {
        this.username = username;
        this.email = email;
        this.isActive = isActive;
        setChanged();
        notifyObservers();
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getIsActive(){
        return isActive;
    }
}
