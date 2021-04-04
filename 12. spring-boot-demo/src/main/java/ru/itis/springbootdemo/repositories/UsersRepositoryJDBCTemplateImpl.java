package ru.itis.springbootdemo.repositories;

import org.springframework.beans.factory.annotation.*;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.*;
import ru.itis.springbootdemo.models.*;
import javax.sql.*;

@Repository
public class UsersRepositoryJDBCTemplateImpl implements UsersRepository {
    private JdbcTemplate jdbcTemplate;

    private RowMapper<User> userRowMapper = (row, i) -> {
        return User.builder()
               .id(row.getLong("id"))
               .email(row.getString("email"))
               .password(row.getString("password"))
               .state((User.State) row.getObject("state"))
               .confirm_code(row.getString("confirm_code"))
               .build();
    };

    @Autowired
    DataSource dataSource;

    UsersRepositoryJDBCTemplateImpl() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void save(User user) {
        jdbcTemplate.update("INSERT into account(email, password, state, confirm_code)" +
                " values(?, ?, ?, ?) ", user.getEmail(), user.getPassword(),
                user.getState(), user.getConfirm_code());
    }






}
