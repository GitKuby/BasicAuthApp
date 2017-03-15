package com.authsystem.servlets;

import com.authsystem.dao.UserDAO;
import com.authsystem.dao.UserDAOFactory;

import com.authsystem.entity.User;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logIn")
public class LogIn extends HttpServlet {

    private static final String USERNAME = "username";
    private static final String PASS = "password";
    private static final String LOG_IN_JSP = "/logIn.jsp";
    private UserDAO dao = UserDAOFactory.createDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User fromDb = dao.getUser(request.getParameter(USERNAME));
        if (fromDb == null) {
            request.setAttribute("errText", "Username does not exist in the system!");
            RequestDispatcher view = request.getRequestDispatcher(LOG_IN_JSP);
            view.forward(request, response);
            return;
        }
        if (!fromDb.getPassword().equals(request.getParameter(PASS))) {
            request.setAttribute("errText", "Wrong password!");
            RequestDispatcher view = request.getRequestDispatcher(LOG_IN_JSP);
            view.forward(request, response);
            return;
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", fromDb);
        RequestDispatcher view = request.getRequestDispatcher("/");
        view.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.getWriter().println("Do not be so smart!");
    }

}
