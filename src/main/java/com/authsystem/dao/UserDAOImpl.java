package com.authsystem.dao;

import com.authsystem.UserType;
import com.authsystem.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class UserDAOImpl implements UserDAO {

    private static final String JNDI_DS = "java:comp/env/jdbc/DS";

    @Override
    public User getUser(String username) {
        String selectSQL = "SELECT USERNAME, PASSWORD, EMAIL, USERTYPE FROM USER WHERE USERNAME = ?;";
        User user = null;
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(selectSQL);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String userName = rs.getString("USERNAME");
                String password = rs.getString("PASSWORD");
                String email = rs.getString("EMAIL");
                UserType userType = UserType.valueOf(rs.getString("USERTYPE"));

                user = new User();
                user.setUsername(userName);
                user.setEmail(email);
                user.setPassword(password);
                user.setUserType(userType);
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        System.out.println("getUser: " + user);
        return user;
    }

    @Override
    public List<User> listUsers() {
        String selectTableSQL = "SELECT USERNAME, PASSWORD, EMAIL, USERTYPE  from USER;";
        List<User> users = new ArrayList<>();
        try {
            Statement statement = getConnection().createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {

                String username = rs.getString("USERNAME");
                String password = rs.getString("PASSWORD");
                String email = rs.getString("EMAIL");
                UserType userType = UserType.valueOf(rs.getString("USERTYPE"));

                User u = new User();
                u.setUsername(username);
                u.setEmail(email);
                u.setPassword(password);
                u.setUserType(userType);
                users.add(u);
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }

        for (User u : users) {
            System.out.println("list users: " + u);
        }

        return users;
    }

    @Override
    public void updateUser(User user) {
        String deleteSQL = "DELETE FROM USER WHERE USERNAME = ?";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(deleteSQL);
            preparedStatement.setString(1, user.getUsername());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        saveUser(user);

    }

    @Override
    public void saveUser(User user) {
        if (user == null) {
            System.err.println("ERROR! Cannot save null User.");
        }

        String insertTableSQL = "INSERT INTO USER"
                + "(USERNAME, PASSWORD, USERTYPE, EMAIL) VALUES (?,?,?,?);";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(insertTableSQL);

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getUserType().name());
            preparedStatement.setString(4, user.getEmail());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            InitialContext context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup(JNDI_DS);
            connection = dataSource.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
