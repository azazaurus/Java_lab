package ru.itis.springbootdemo.repositories;

import ru.itis.springbootdemo.models.*;

/**
 * 10.02.2021
 * spring-boot-demo
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface UsersRepository {
    public void save(User user);
}
