package ru.itis.repositories;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import ru.itis.model.*;

import java.util.*;

public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
