package ru.itis.restsemestrovka.security.config;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.method.configuration.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.authentication.logout.*;
import ru.itis.restsemestrovka.security.jwt.*;
import ru.itis.restsemestrovka.security.token.*;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private TokenAuthenticationFilter tokenAuthenticationFilter;

    @Autowired
    private TokenAuthenticationProvider provider;

    @Autowired
    private JwtLogoutFilter jwtLogoutFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(provider);
    }

    @Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
			.addFilterAt(jwtLogoutFilter, LogoutFilter.class)
			.authorizeRequests()
			.antMatchers("/users").permitAll()
			.antMatchers("/login").permitAll()
            .and().sessionManagement().disable()
			.csrf().disable();
	}
}
