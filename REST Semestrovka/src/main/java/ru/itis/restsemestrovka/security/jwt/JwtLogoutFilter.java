package ru.itis.restsemestrovka.security.jwt;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.context.*;
import org.springframework.security.web.util.matcher.*;
import org.springframework.stereotype.*;
import org.springframework.web.filter.*;
import ru.itis.restsemestrovka.services.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

@Component
public class JwtLogoutFilter extends OncePerRequestFilter {

	@Autowired
	private JwtBlackListService jwtBlackListService;

	private final RequestMatcher logoutRequest = new
		AntPathRequestMatcher("/logout", "GET");

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		if (logoutRequest.matches(request)) {
			jwtBlackListService.add(request.getHeader("Authorization"));
			SecurityContextHolder.clearContext();
			return;
		}
		filterChain.doFilter(request, response);
	}
}
