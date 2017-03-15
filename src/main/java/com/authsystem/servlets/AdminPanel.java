package com.authsystem.servlets;

import com.authsystem.UserType;
import com.authsystem.dao.UserDAO;
import com.authsystem.dao.UserDAOImpl;
import com.authsystem.entity.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adminPanel")
public class AdminPanel extends HttpServlet {

    private static UserDAO dao = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<User> users = dao.listUsers();
        request.setAttribute("users", users);
        RequestDispatcher view = request.getRequestDispatcher("adminPanel.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String submit = request.getParameter("submit");

        if (submit == null) {
            return;
        }
        char prefix = submit.charAt(0);
        submit = submit.substring(1);

        if (prefix == '-') {
            User user = dao.getUser(submit);
            user.setUserType(UserType.REGULAR);
            dao.updateUser(user);
        }
        if (prefix == '+') {
            User user = dao.getUser(submit);
            user.setUserType(UserType.PREMIUM);
            dao.updateUser(user);
        }
        response.sendRedirect("/adminPanel");
    }

}
