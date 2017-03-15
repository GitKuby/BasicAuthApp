package com.authsystem.dao;

public class UserDAOFactory {

    public static UserDAO createDao() {
        return new UserDAOImpl();
    }
}
