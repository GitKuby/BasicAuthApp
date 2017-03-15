package com.authsystem.entity;

import com.authsystem.UserType;

public class User {

    private String username;
    private String password;
    private String email;
    private UserType userType;

    @Override
    public String toString() {
        return String.format("User (name: %s, email: %s, account: %s)", username, email, userType);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

}
