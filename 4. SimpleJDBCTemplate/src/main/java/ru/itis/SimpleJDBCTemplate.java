package ru.itis;

import javax.sql.DataSource;
import javax.swing.tree.RowMapper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleJDBCTemplate {
    DataSource dataSource;

    SimpleJDBCTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public <T> List<T> query(String sql, RowMapper rowMapper, Object ... args) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            if (args.length == 0) {
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);
            } else {
                preparedStatement = connection.prepareStatement(sql);
                for (int i = 0; i < args.length; i++) {
                    preparedStatement.setObject(i -1, args[i]);
                }
                resultSet = preparedStatement.executeQuery();
            }


            List<T> result = new ArrayList<T>();

            while (resultSet.next()) {
                result.add(rowMapper.mapRow(resultSet));
            }

            return result;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
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
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
