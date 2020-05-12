package com.example.filedgameapptest.users.data;

public class LoginUserDTO {
    private String password;
    private String email;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public LoginUserDTO(String password, String email) {
        this.password = password;
        this.email = email;
    }
}
