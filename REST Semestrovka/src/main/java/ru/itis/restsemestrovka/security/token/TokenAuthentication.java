package ru.itis.restsemestrovka.security.token;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.*;
import ru.itis.restsemestrovka.security.details.*;

import java.util.*;

public class TokenAuthentication implements Authentication {
    @Autowired
    private UserDetailsImpl userDetails;

    private String token;

    public TokenAuthentication(String token) {
        this.token = token;
    }

    public void setUserDetails (UserDetails userDetails) {
        this.userDetails = (UserDetailsImpl) userDetails;
    }
    private boolean isAuthenticated;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userDetails.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return userDetails.getPassword();
    }

    @Override
    public Object getDetails() {
        return userDetails;
    }

    @Override
    public Object getPrincipal() {
        if (userDetails != null) {
            return userDetails.getUser();
        } else
            return null;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return token;
    }
}
