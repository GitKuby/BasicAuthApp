package com.authsystem.servlets;

import com.authsystem.UserType;
import com.authsystem.entity.User;
import com.authsystem.filters.AdminPanelFilter;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestCases extends Mockito {

    @InjectMocks
    private LogIn servlet;

    @Test
    public void servlet_should_redirect_to_root() throws IOException, ServletException {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        Logout servlet = new Logout();

        when(request.getSession()).thenReturn(session);
        servlet.doGet(request, response);
        verify(response).sendRedirect("/");
    }

    @Test
    public void filter_should_let_admin_in() throws ServletException, IOException {
        AdminPanelFilter filter = new AdminPanelFilter();
        HttpServletRequest httpRequest = mock(HttpServletRequest.class);
        ServletResponse response = mock(ServletResponse.class);
        FilterChain chain = mock(FilterChain.class);
        HttpSession session = mock(HttpSession.class);

        User u = new User();
        u.setUserType(UserType.ADMIN);

        when(httpRequest.getSession(false)).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(u);
        filter.doFilter(httpRequest, response, chain);
        verify(chain).doFilter(httpRequest, response);
    }
}
