package ru.itis.javalab.repositories;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionJdbcImpl {
    private DataSource dataSource;

    public SessionJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String find(String string) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String SQL = "SELECT * FROM session_attributes WHERE auth_value = ?";
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
