package ru.itis.javalab.repositories;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CookieJdbcImpl {
    private DataSource dataSource;

    public CookieJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String returnSessionId(String string) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String SQL = "SELECT * FROM cookie WHERE cookie_value = ?";
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL);
            statement.setString(1, string);
            resultSet = statement.executeQuery();
            String result = null;

            while (resultSet.next()) {
                result = resultSet.getString("cookie_value");
            }

            return result;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return null;
    }
}
