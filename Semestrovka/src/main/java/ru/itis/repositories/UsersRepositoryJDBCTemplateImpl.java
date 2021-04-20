package ru.itis.repositories;

import org.springframework.jdbc.core.*;
import org.springframework.stereotype.*;
import ru.itis.model.*;

import javax.sql.*;
import java.util.*;

@Repository
public class UsersRepositoryJDBCTemplateImpl implements UsersRepository {
    private JdbcTemplate jdbcTemplate;

    private RowMapper<User> userRowMapper = (row, i) -> {
        return User.builder()
               .id(row.getLong("id"))
               .email(row.getString("email"))
               .hashedPassword(row.getString("password"))
               .state((User.State) row.getObject("state"))
                .status((User.Status) row.getObject("status"))
                .role((User.Role) row.getObject("role"))
               .confirm_code(row.getString("confirm_code"))
               .build();
    };

    public UsersRepositoryJDBCTemplateImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void save(User user) {
        jdbcTemplate.update("INSERT into dbo.account(email, password, confirm_code)" +
                " values(?, ?, ?) ", user.getEmail(), user.getHashedPassword(), user.getConfirm_code());
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        List<User> users = jdbcTemplate.query("SELECT * FROM accounts", userRowMapper);
        return users;
    }
}
