package com.authsystem;

import com.authsystem.dao.UserDAO;
import com.authsystem.dao.UserDAOImpl;
import com.authsystem.entity.User;
import java.sql.Connection;
import java.sql.Statement;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyContextListener implements ServletContextListener {

    private static final UserDAO dao = new UserDAOImpl();

    private static final String CREATETABLE = "CREATE TABLE USER(USERNAME VARCHAR(20),"
            + " PASSWORD VARCHAR(20), USERTYPE VARCHAR(20), EMAIL VARCHAR(30), PRIMARY KEY (USERNAME));";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        prepareDB();
        populateDB();
        test();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    private void prepareDB() {
        Connection connection = UserDAOImpl.getConnection();
        if (connection != null) {
            System.out.println("Connection established!");

            try {
                Statement statement = connection.createStatement();
                statement.execute(CREATETABLE);
                connection.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            System.out.println("Connection is NULL!");
        }
    }

    private void populateDB() {
        User u1 = new User();
        u1.setUsername("Admin");
        u1.setPassword("pass");
        u1.setEmail("admin@user.com");
        u1.setUserType(UserType.ADMIN);

        User u2 = new User();
        u2.setUsername("Regular");
        u2.setPassword("pass");
        u2.setEmail("regular@user.com");
        u2.setUserType(UserType.REGULAR);

        User u3 = new User();
        u3.setUsername("Premium");
        u3.setPassword("pass");
        u3.setEmail("premium@user.com");
        u3.setUserType(UserType.PREMIUM);

        dao.saveUser(u1);
        dao.saveUser(u2);
        dao.saveUser(u3);
    }

    private void test() {
        User u = dao.getUser("Admin");
        System.out.println(u);
    }

}
