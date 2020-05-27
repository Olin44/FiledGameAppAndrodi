package com.example.filedgameapptest.users.data;
/**
 * Klasa służąca do przechowywania informacji o użytkowniku, gdy chce się zalogować.
 */
public class LoginUserDTO {
    /**
     * Pole przechowujące hasło.
     */
    private String password;
    /**
     * Pole przechowujące email.
     */
    private String email;
    /**
     * Metoda ustawiająca pole z hasłem.
     * @param password hasło użytkownika
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Metoda ustawiająca pole z emailem.
     * @param email email użytkownika
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Metoda pobierająca hasło
     * @return String z hasłem
     */
    public String getPassword() {
        return password;
    }
    /**
     * Metoda pobierająca email
     * @return String z emailem
     */
    public String getEmail() {
        return email;
    }
    /**
     * Konstruktor do tworzenia obiektu
     * @param password hasło
     * @param email email
     */
    public LoginUserDTO(String password, String email) {
        this.password = password;
        this.email = email;
    }
}
