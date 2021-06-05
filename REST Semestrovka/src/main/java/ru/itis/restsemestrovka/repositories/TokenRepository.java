package ru.itis.restsemestrovka.repositories;

import org.springframework.data.jpa.repository.*;
import ru.itis.restsemestrovka.models.*;

import java.util.*;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByToken(String token);
}
