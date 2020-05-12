package com.example.filedgameapptest.users.data;

public class LogoutUserDTO {
    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    LogoutUserDTO(String email) {
        this.email = email;
    }
}
