package ru.itis.javalab.repositories;

import ru.itis.javalab.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 05.10.2020
 * 08. Web Application
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class UsersRepositoryJdbcImpl implements UsersRepository {

    //language=SQL
    private static final String SQL_FIND_ALL = "select * from student";

    //language=SQL
    private static final String SQL_FIND_ALL_BY_AGE = "select * from student where age = ?";

    private DataSource dataSource;
    private SimpleJdbcTemplate template;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.template = new SimpleJdbcTemplate(dataSource);
    }

    private RowMapper<User> userRowMapper = row -> User.builder()
            .id(row.getLong("id"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .age(row.getInt("age"))
            .build();

    @Override
    public void save(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        // TODO: вместо этого написать return template.query(SQL_FIND_ALL, usersRowMapper)
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_FIND_ALL);

            List<User> result = new ArrayList<>();

            while (resultSet.next()) {
                result.add(userRowMapper.mapRow(resultSet));
            }

            return result;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException throwables) {
                    //ignore
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    // ignore
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    // ignore
                }
            }
        }
    }

    @Override
    public List<User> findAllByAge(int age) {
        // TODO: вместо этого написать return template.query(SQL_FIND_ALL_BY_AGE, usersRowMapper, age)
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_FIND_ALL_BY_AGE);
            statement.setInt(1, age);
            resultSet = statement.executeQuery();

            List<User> result = new ArrayList<>();

            while (resultSet.next()) {
                result.add(userRowMapper.mapRow(resultSet));
            }

            return result;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException throwables) {
                    //ignore
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    // ignore
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    // ignore
                }
            }
        }
    }
}
