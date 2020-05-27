package com.example.filedgameapptest.users.data;

import android.os.Handler;

import java.util.Observable;
/**
 * Klasa służąca do przechowywania danych o użytkowniku, pomiędzy widokami w aplikacji.
 */
public class UserDataRepository extends Observable {
    /**
     * Pole przechowujące ID obiektu.
     */
    private String id;
    /**
     * Pole przechowujące nazwę użytkownika.
     */
    private String username;
    /**
     * Pole przechowujące email użytkownika.
     */
    private String email;
    /**
     * Pole przechowujące hasło użytkownika.
     */
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
                setUserData("67", "changed", "data", true);
            }
        }, 500000);
    }
    /**
     * Metoda służąca do ustawienia wszystkich informacji o grze.
     * @param id ID obiektu
     * @param username nazwa użytkownika
     * @param email email użytkownika
     * @param isActive boolean określający czy użytkownik jest aktywny
     */
    public void setUserData(String id, String username, String email, Boolean isActive) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.isActive = isActive;
        setChanged();
        notifyObservers();
    }
    /**
     * Metoda służąca do usunięcia wszystkich informacji o użytkowniku.
     */
    public void deleteAllData(){
        this.username = null;
        this.email = null;
        this.isActive = false;
        setChanged();
        notifyObservers();
    }
    /**
     * Metoda pobierająca nazwę użytkownika
     * @return String z nazwą użytkownika
     */
    public String getUsername() {
        return username;
    }
    /**
     * Metoda pobierająca email użytkownika
     * @return String z emailem użytkownika
     */
    public String getEmail() {
        return email;
    }
    /**
     * Metoda pobierająca informację o stanie aktywności
     * @return boolean ze stanem aktywności
     */
    public Boolean getIsActive(){
        return isActive;
    }
    /**
     * Metoda służąca do pobrania ID.
     * @return  id String zawierający ID.
     */
    public String getId() {
        return id;
    }
    /**
     * Metoda służąca do ustawienia ID obiektu.
     * @param id String zawierający ID użytkownika
     */
    public void setId(String id) {
        this.id = id;
        setChanged();
        notifyObservers();
    }
    /**
     * Metoda służąca do ustawienia nazwy użytkownika.
     * @param username String zawierający nazwę użytkownika
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * Metoda służąca do ustawienia email użytkownika.
     * @param email String zawierający nazwę użytkownika
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Metoda służąca do określenia czy użytkownik jest aktywny.
     * @param active boolean określający czy użytkownik jest aktywny.
     */
    public void setActive(boolean active) {
        isActive = active;
    }
}
