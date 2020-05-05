package com.example.filedgameapptest.users.register;

public class NewUserModel {

    private String username;
    private String email;
    private String password;
    private boolean isActive;

    public static final class Builder {
        private String username;
        private String email;
        private String password;
        private boolean isActive = false;

        public Builder setUsername(final String username) {
            this.username = username;
            return this;
        }

        public Builder setEmail(final String email) {
            this.email = email;
            return this;
        }

        public Builder setPassword(final String password) {
            this.password = password;
            return this;
        }

        public Builder setIsActive(final boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public NewUserModel build() {
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

            NewUserModel newUserModel = new NewUserModel();
            newUserModel.username = this.username;
            newUserModel.email = this.email;
            newUserModel.password = this.password;
            newUserModel.isActive = this.isActive;

            return newUserModel;
        }

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

    public boolean isActive() {
        return isActive;
    }
}
