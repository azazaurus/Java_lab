package ru.itis.services;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import ru.itis.model.*;
import ru.itis.repositories.*;
import java.util.*;

@Component
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public void banAll() {
        List<User> users = usersRepository.findAll();
        for (User user : users) {
            if (!user.isAdmin()) {
                user.setStatus(User.Status.BANNED);
                usersRepository.save(user);
            }
        }
    }
}
