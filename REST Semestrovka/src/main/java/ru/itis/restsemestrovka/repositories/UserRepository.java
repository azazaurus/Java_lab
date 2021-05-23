package ru.itis.restsemestrovka.repositories;

import org.springframework.data.jpa.repository.*;
import ru.itis.restsemestrovka.models.*;

public interface UserRepository extends JpaRepository<User, Long> {
}
