package com.example.filedgameapptest.users.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Klasa służąca do przechowywania danych o użytkowniku, który chce się zarejestrować.
 */
@SuppressWarnings("serial")
@Getter
@Setter
@ToString
public class NewUserDataModel implements Serializable {
    /**
     * Pole przechowujące ID obiektu.
     */
    @SerializedName("id")
    @Expose
    private String id;
    /**
     * Pole przechowujące nazwę użytkownika.
     */
    @SerializedName("username")
    @Expose
    private String username;
    /**
     * Pole przechowujące email użytkownika.
     */
    @SerializedName("email")
    @Expose
    private String email;
    /**
     * Pole przechowujące hasło użytkownika.
     */
    @SerializedName("password")
    @Expose
    private String password;
    /**
     * Pole przechowujące informację czy użytkownik jest aktywny.
     */
    @SerializedName("is_active")
    @Expose
    private boolean isActive;
    /**
     * Metoda do pobierania ID
     * @return String zawierający ID
     */
    public String getId() {
        return id;
    }
    /**
     * Klasa Bulider do łatwiejszego tworzenia obiektów klasy NewUserDataModel.
     */
    public static final class Builder {
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
        private String password;
        /**
         * Pole przechowujące informację czy użytkownik jest aktywny.
         */
        private boolean isActive = false;

        /**
         * Metoda służaca do ustawiania nazwy użytkownika
         * @param username nazwa użytkownika
         * @return obiekt klasy Builder
         */
        public Builder setUsername(final String username) {
            this.username = username;
            return this;
        }
        /**
         * Metoda służaca do ustawiania emaila użytkownika
         * @param email email użytkownika
         * @return obiekt klasy Builder
         */
        public Builder setEmail(final String email) {
            this.email = email;
            return this;
        }
        /**
         * Metoda służaca do ustawiania nazwy użytkownika
         * @param password hasło użytkownika
         * @return obiekt klasy Builder
         */
        public Builder setPassword(final String password) {
            this.password = password;
            return this;
        }
        /**
         * Metoda służaca do ustawiania nazwy użytkownika
         * @param isActive czy użytkownik jest aktywny
         * @return obiekt klasy Builder
         */
        public Builder setIsActive(final boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        /**
         * Metoda służaca do stworzenia nowego obiektu klasy NewUserDataModel.
         * @return nowy obiekt NewUserDataModel.
         * @exception IllegalStateException jeśli stworzenie obiektu się nie powiedzie zwraca wyjątek z tekstem, które pole zostało ustawione niepoprawnie.
         */
        public NewUserDataModel build() {
            if (this.username.isEmpty()) {
                throw new IllegalStateException(
                        "Username can not be empty!");
            }
            if (this.email.isEmpty()) {
                throw new IllegalStateException(
                        "Email can not be empty!");
            }
            if (this.password.isEmpty()) {
                throw new IllegalStateException(
                        "Password can not be empty!");
            }

            NewUserDataModel newUserModel = new NewUserDataModel();
            newUserModel.username = this.username;
            newUserModel.email = this.email;
            newUserModel.password = this.password;
            newUserModel.isActive = this.isActive;

            return newUserModel;
        }

    }

    /**
     * Metoda pobierająca nazwę użytkownika
     * @return String z nazwą użytkownika
     */
    public String getUsername() {
        return username;
    }

    /**
     * Metoda pobierająca email
     * @return String z emailem
     */
    public String getEmail() {
        return email;
    }
    /**
     * Metoda pobierająca hasło użytkownika
     * @return String z hasłem użytkownika
     */
    public String getPassword() {
        return password;
    }
    /**
     * Metoda pobierająca boolean określający czy użytkownik jest aktywny
     * @return boolean określający czy użytkownik jest aktywny
     */
    public boolean isActive() {
        return isActive;
    }
}
