package com.authsystem.filters;

import com.authsystem.UserType;
import com.authsystem.entity.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/premium.jsp")
public class PremiumFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);

        List<UserType> allowedUserTypes = new ArrayList<>();
        allowedUserTypes.add(UserType.PREMIUM);
        allowedUserTypes.add(UserType.ADMIN);
        if (session == null || session.getAttribute("user") == null
                || !allowedUserTypes.contains(((User) session.getAttribute("user")).getUserType())) {
            request.setAttribute("errText", "Access denied! You have no permission to visit premium page.");
            RequestDispatcher view = request.getRequestDispatcher("/");
            view.forward(request, response);
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
