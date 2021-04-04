package ru.itis.repositories;

import org.springframework.jdbc.core.*;
import org.springframework.stereotype.*;
import ru.itis.model.*;

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

    public UsersRepositoryJDBCTemplateImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void save(User user) {
        jdbcTemplate.update("INSERT into dbo.account(email, password, confirm_code)" +
                " values(?, ?, ?) ", user.getEmail(), user.getPassword(), user.getConfirm_code());
    }
}
