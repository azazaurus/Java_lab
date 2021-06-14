package ru.itis.restsemestrovka.security.details;

import com.auth0.jwt.*;
import com.auth0.jwt.algorithms.*;
import com.auth0.jwt.interfaces.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;
import ru.itis.restsemestrovka.models.*;
import ru.itis.restsemestrovka.models.User;
import ru.itis.restsemestrovka.repositories.*;

@Component("tokenUserDetailService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    public UserRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
	    DecodedJWT decodedToken = JWT.require(Algorithm.HMAC256("secret_key"))
		    .build()
		    .verify(token);
	    Long userId = Long.parseLong(decodedToken.getSubject());
	    User user = usersRepository.getById(userId);
        return new UserDetailsImpl(user);
    }
}
