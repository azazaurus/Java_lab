package ru.itis.restsemestrovka.security.details;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;
import ru.itis.restsemestrovka.models.*;
import ru.itis.restsemestrovka.repositories.*;

@Component("tokenUserDetailService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    public TokenRepository tokenRepository;

    @Autowired
    public UserRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
        Token result =  tokenRepository.findByToken(token)
                .orElseThrow(()-> new UsernameNotFoundException("Token not found"));
        return new UserDetailsImpl(result.getUser());
    }
}
