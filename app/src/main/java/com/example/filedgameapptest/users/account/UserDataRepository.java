package com.example.filedgameapptest.users.account;

import android.os.Handler;

import java.util.Observable;

public class UserDataRepository extends Observable {
    private String id;

    public String getId() {
        return id;
    }

    public boolean isActive() {
        return isActive;
    }

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

    public void deleteAllData(){
        this.username = null;
        this.email = null;
        this.isActive = false;
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

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public static void setINSTANCE(UserDataRepository INSTANCE) {
        UserDataRepository.INSTANCE = INSTANCE;
    }
}
