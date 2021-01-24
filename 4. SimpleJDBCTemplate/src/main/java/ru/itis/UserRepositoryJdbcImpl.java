package ru.itis;

import javax.sql.DataSource;

public class UserRepositoryJdbcImpl {
    private final DataSource dataSource;

    public UserRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
