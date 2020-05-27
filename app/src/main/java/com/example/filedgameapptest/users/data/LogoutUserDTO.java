package com.example.filedgameapptest.users.data;
/**
 * Klasa służąca do przechowywania informacji o użytkowniku, gdy chce się wylogować.
 */
public class LogoutUserDTO {
    /**
     * Pole przechowujące email.
     */
    private String email;
    /**
     * Metoda ustawiająca pole z emailem.
     * @param email email użytkownika
     */
    public void setEmail(String email) {
        this.email = email;
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
     * @param email email
     */
    public LogoutUserDTO(String email) {
        this.email = email;
    }
}
