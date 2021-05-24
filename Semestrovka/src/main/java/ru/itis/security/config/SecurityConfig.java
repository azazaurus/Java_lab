package ru.itis.security.config;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.method.configuration.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.authentication.rememberme.*;
import org.springframework.security.web.util.matcher.*;
import org.springframework.session.config.annotation.web.http.*;
import org.springframework.session.jdbc.config.annotation.web.http.*;
import ru.itis.util.*;

import javax.sql.*;
import java.sql.*;

@EnableWebSecurity
@EnableJdbcHttpSession
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("customUserDetailService")
    private UserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//            .authorizeRequests()
//            .antMatchers("/signUp").permitAll()
//            .antMatchers("/profile").authenticated()
//            .antMatchers("/users").hasAuthority("ADMIN")
//            .antMatchers("/banAll").hasAuthority("ADMIN")
//            .and()
            .formLogin()
            .loginPage("/signIn")
            .usernameParameter("email")
            .defaultSuccessUrl("/profile")
            .failureUrl("/signIn?error")
            .and()
            .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
            .and()
            .rememberMe()
            .rememberMeParameter("remember-me").tokenRepository(persistentTokenRepository());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }
}
