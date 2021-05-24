package ru.itis.security.details;

import org.springframework.security.core.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.userdetails.*;
import ru.itis.model.User;

import java.util.*;

public class UserDetailsImpl implements UserDetails {
    private final String email;
	private final String hashedPassword;
	private final String role;
	private final boolean isActive;

    public UserDetailsImpl(User user) {
        email = user.getEmail();
        hashedPassword = user.getHashPassword();
        role = user.getRole().toString();
        isActive = user.isActive();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
        return Collections.singleton(authority);
    }

    @Override
    public String getPassword() {
        return hashedPassword;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }
}
