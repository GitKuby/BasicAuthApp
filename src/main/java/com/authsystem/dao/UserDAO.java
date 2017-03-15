package com.authsystem.dao;

import com.authsystem.entity.User;
import java.util.List;

public interface UserDAO {

    User getUser(String username);

    List<User> listUsers();

    void saveUser(User user);

    void updateUser(User user);

}
