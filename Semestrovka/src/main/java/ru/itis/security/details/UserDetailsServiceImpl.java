package ru.itis.security.details;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;
import ru.itis.model.User;
import ru.itis.repositories.*;

@Component("customUserDetailService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    public UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user =  usersRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
        return new UserDetailsImpl(user);
    }
}
