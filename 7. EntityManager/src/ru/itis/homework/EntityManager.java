package ru.itis.homework;

import javax.sql.DataSource;
import java.lang.reflect.*;
import java.sql.*;
import java.util.*;

/**
 * 02.11.2020
 * 10. Reflection
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class EntityManager {
    private DataSource dataSource;

    public EntityManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // createTable("account", User.class);
    public <T> void createTable(String tableName, Class<T> entityClass) {
        StringBuilder SQL_CREATE_TABLE = new StringBuilder("CREATE TABLE " + tableName + "( ");

        // Через рефлексию вытащим поля "entityClass"
        Field[] fields = entityClass.getFields();
        for (Field field : fields) {
            String fieldType = field.getType().getSimpleName();
            String fieldName = field.getName();

            SQL_CREATE_TABLE.append(fieldName);

            // добавляем в выражения типы соответствующих полей таблицы
            // cтроковые типы
            if (field.getType().isAssignableFrom(String.class)) {
                SQL_CREATE_TABLE.append(" " + "varchar(255)");
            }
            // целочисленные типы
            if (field.getType().isAssignableFrom(byte.class)) {
                SQL_CREATE_TABLE.append(" " + "integer");
            }
            // вещественные типы
            if (field.getType().isAssignableFrom(float.class)) {
                SQL_CREATE_TABLE.append(" " + "real");
            }
            // символьные типы
            if (field.getType().isAssignableFrom(char.class)) {
                SQL_CREATE_TABLE.append(" " + "char(50)");
            }
            // логические типы
            if (field.getType().isAssignableFrom(boolean.class)) {
                SQL_CREATE_TABLE.append(" " + "boolean");
            }
        }

        SQL_CREATE_TABLE.append(";");

        Connection connection;
        Statement statement;

        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();

            // отправляем запрос в бд
            statement.executeUpdate(SQL_CREATE_TABLE.toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void save(String tableName, Object entity) {
        Class<?> entityClass = entity.getClass();

        // Создаем SQL-выражение
        StringBuilder SQL_INSERT = new StringBuilder("INSERT INTO "+ tableName + "(");

        // вытаскиваем названия полей класса объекта и добавляем в SQL-выражение
        Field[] fields = entityClass.getFields();
        boolean isFirstField = true;
        for (Field field: fields) {
            if (!isFirstField)
                SQL_INSERT.append(", ");
            SQL_INSERT.append(field.getName());
            isFirstField = false;
        }
        SQL_INSERT.append(") VALUES (");

        // INSERT INTO table (a, b, c) VALUES (
        // вытаскиваем значения полей объекта и тоже добавляем в SQL-выражение
        isFirstField = true;
        for (Field field: fields) {
            String fieldName = field.getName();
            try {
                field = entityClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                throw new IllegalStateException(e);
            }

            Object fieldValue = null;
            try {
                fieldValue = field.get(entity);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException(e);
            }
            if (!isFirstField) {
                SQL_INSERT.append(", ");

            }

            SQL_INSERT.append(fieldValue);
            isFirstField = false;
        }

        SQL_INSERT.append(");");

        Connection connection = null;
        Statement statement = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(SQL_INSERT.toString());

        } catch ( SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    //ignore
                }
            }
            if (statement != null)
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    //ignore
                }
        }
    }

    // User user = entityManager.findById("account", User.class, Long.class, 10L);
    public <T, ID> T findById(String tableName, Class<T> resultType, Class<ID> idType, ID idValue) throws SQLException, IllegalAccessException, InvocationTargetException, InstantiationException {
        StringBuilder SQL_SELECT_BY_ID = new StringBuilder("SELECT ");

        Field[] fields = resultType.getDeclaredFields();
        boolean isFirstField = true;
        for (Field field: fields) {
            String fieldName = field.getName();
            if (!isFirstField) {
                SQL_SELECT_BY_ID.append(", ");
            }

            SQL_SELECT_BY_ID.append(fieldName);
            isFirstField = false;
        }

        String idFieldName = fields[0].getName();
        SQL_SELECT_BY_ID.append(" FROM ").append(tableName).append(" WHERE ").append(idFieldName)
                .append(" = ").append(idValue).append(";");
        // выполнить select

        Constructor<?> constructor = Arrays.stream(resultType.getConstructors())
                .filter(x -> x.getParameterCount() == fields.length)
                .findFirst()
                .get();
        // создать объект, используя конструктор

        Connection connection= null;
        Statement statement = null;
        ResultSet resultSet = null;

        connection = dataSource.getConnection();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(SQL_SELECT_BY_ID.toString());
        resultSet.next();

        Object[] fieldValues = new Object[fields.length];
        for (int i = 0; i < fields.length; ++i) {
            String fieldName = fields[i].getName();
            fieldValues[i] = resultSet.getObject(fieldName);
        }

        connection.close();
        statement.close();
        resultSet.close();
        //noinspection unchecked
        return (T) constructor.newInstance(fieldValues);
    }


}
