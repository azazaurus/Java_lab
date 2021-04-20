package ru.itis.repositories;

import ru.itis.model.*;

import java.util.*;

public interface UsersRepository {
    void save(User user);

    Optional<User> findByEmail(String email);

    List<User> findAll();
}
