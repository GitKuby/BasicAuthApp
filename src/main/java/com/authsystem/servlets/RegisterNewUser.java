package com.authsystem.servlets;

import com.authsystem.UserType;
import com.authsystem.dao.UserDAO;
import com.authsystem.dao.UserDAOImpl;
import com.authsystem.entity.User;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/signIn")
public class RegisterNewUser extends HttpServlet {

    private static UserDAO dao = new UserDAOImpl();

    //request parameters
    private static final String USERNAME = "username";
    private static final String EMAIL = "email";
    private static final String PASS = "password";
    private static final String PASS_AGAIN = "confirmedPassword";

    private static final String SIGN_IN_JSP = "/register.jsp";
    private static final String PROFILE_JSP = "/userProfile.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.getWriter().println("Do not be so smart!");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pass = request.getParameter(PASS);
        String passAgain = request.getParameter(PASS_AGAIN);

        if (pass.isEmpty() || passAgain.isEmpty() || !pass.equals(passAgain)) {
            request.setAttribute("errText", "Provided passwords do not match!");
            RequestDispatcher view = request.getRequestDispatcher(SIGN_IN_JSP);
            view.forward(request, response);
            return;
        }
        User user = prepareUser(request);

        if (dao.getUser(user.getUsername()) != null) {
            request.setAttribute("errText", "Username already exists in database!");
            RequestDispatcher view = request.getRequestDispatcher(SIGN_IN_JSP);
            view.forward(request, response);
            return;
        }
        dao.saveUser(user);

        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        RequestDispatcher view = request.getRequestDispatcher(PROFILE_JSP);
        view.forward(request, response);
    }

    private User prepareUser(HttpServletRequest request) {
        User newUser = new User();
        newUser.setUsername(request.getParameter(USERNAME));
        newUser.setEmail(request.getParameter(EMAIL));
        newUser.setPassword(request.getParameter(PASS));
        newUser.setUserType(UserType.REGULAR);
        return newUser;
    }
}
