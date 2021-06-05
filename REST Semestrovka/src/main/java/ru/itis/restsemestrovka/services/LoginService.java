package ru.itis.restsemestrovka.services;

import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
import ru.itis.restsemestrovka.dto.*;
import ru.itis.restsemestrovka.models.*;
import ru.itis.restsemestrovka.models.User;
import ru.itis.restsemestrovka.repositories.*;

import java.util.*;
import java.util.function.*;

@Service
public class LoginService {
    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @SneakyThrows
    public TokenDto login(UserForm userForm) {
        User user = userRepository.findByEmail(userForm.getEmail())
                .orElseThrow((Supplier<Throwable>)() -> new UsernameNotFoundException("User not found")) ;

        if (passwordEncoder.matches(userForm.getPassword(), user.getHashPassword())) {
            String tokenValue = UUID.randomUUID().toString();
            Token token = Token.builder()
                    .token(tokenValue)
                    .user(user)
                    .build();

            tokenRepository.save(token);

            return TokenDto.builder()
                    .token(tokenValue)
                    .build();
        } else {
            throw  new UsernameNotFoundException("Invalid username or password");
        }
    }


}
