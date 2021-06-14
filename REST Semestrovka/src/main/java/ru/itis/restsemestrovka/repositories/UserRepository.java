package ru.itis.restsemestrovka.repositories;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import ru.itis.restsemestrovka.models.*;

import java.util.*;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
