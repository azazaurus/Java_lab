package ru.itis.javalab.filters;

import ru.itis.javalab.repositories.SessionJdbcImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

@WebFilter()
public class AuthFilter implements Filter {
    private SessionJdbcImpl sessionJdbc;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        this.sessionJdbc = (SessionJdbcImpl) servletContext.getAttribute("sessionJdbc");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        if (!httpServletRequest.getRequestURI().contains("login") | !httpServletRequest.getRequestURI().contains("signup")) {
            HttpSession httpSession = httpServletRequest.getSession();
            UUID uuid = (UUID) httpSession.getAttribute("Auth");

            if (uuid == null | sessionJdbc.find(uuid.toString()) == null) {
                httpServletRequest.getRequestDispatcher("/login").forward(servletRequest, servletResponse);
            }

        }
    }


    @Override
    public void destroy() {

    }
}
