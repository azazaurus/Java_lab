package ru.itdrive.web.filters;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

import static ru.itdrive.web.filters.ResponseUtil.sendForbidden;

public class CsrfFilter implements Filter {
    private final String csrfTokenAttributeName = "_csrf_token";
    private final String csrfTokensAttributeName = "_csrf_tokens";
    private final int maxCsrfTokenCount = 100;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (request.getMethod().equals("POST")) {
            HttpSession session = request.getSession(false);
            String requestCsrf = request.getParameter(csrfTokenAttributeName);
            Object[] sessionCsrfs = (Object[])session.getAttribute(csrfTokensAttributeName);
            if (Arrays.asList(sessionCsrfs).contains(requestCsrf))
                filterChain.doFilter(servletRequest, servletResponse);
            else
                sendForbidden(request, response);
            return;
        }

        if (request.getMethod().equals("GET")) {
            HttpSession session = request.getSession();
            Object csrfTokensAttribute = session.getAttribute(csrfTokensAttributeName);
            Deque<Object> sessionCsrfs = csrfTokensAttribute != null
                    ? new ArrayDeque<>(Arrays.asList((Object[])csrfTokensAttribute))
                    : new ArrayDeque<>(maxCsrfTokenCount);

            if (sessionCsrfs.size() >= maxCsrfTokenCount)
                sessionCsrfs.removeLast();

            String csrf = UUID.randomUUID().toString();
            sessionCsrfs.addFirst(csrf);

            session.setAttribute(csrfTokensAttributeName, sessionCsrfs.toArray());
            session.setAttribute(csrfTokenAttributeName, csrf);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
