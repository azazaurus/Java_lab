package ru.itis.services;

import org.springframework.stereotype.*;
import ru.itis.model.*;
import java.util.*;

@Service
public interface UsersService {
    List<User> getAllUsers();

    void banAll();
}
