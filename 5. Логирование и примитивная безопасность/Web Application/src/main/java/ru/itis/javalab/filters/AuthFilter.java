package ru.itis.javalab.filters;

import ru.itis.javalab.repositories.CookieJdbcImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter()
public class AuthFilter implements Filter {
    private CookieJdbcImpl cookieJdbc;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        this.cookieJdbc = (CookieJdbcImpl) servletContext.getAttribute("sessionJdbc");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        if (!httpServletRequest.getRequestURI().contains("login") | !httpServletRequest.getRequestURI().contains("signup")) {
            Cookie[] cookies = httpServletRequest.getCookies();
            String cookieValue = null;
            for (Cookie cookie: cookies) {
                if (cookie.getName().equals("Auth"))
                    cookieValue = cookie.getValue();
            }

            if (cookieJdbc.returnSessionId(cookieValue) == null) {
                httpServletRequest.getRequestDispatcher("/login").forward(servletRequest, servletResponse);
            }

        }
        filterChain.doFilter(servletRequest, servletResponse);
    }


    @Override
    public void destroy() {

    }
}
