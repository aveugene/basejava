package ru.javawebinar.basejava.sql;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/*
 * like PathStorage ( Strategy pattern)  Lesson 9 HW + <T> (any type for return statement.
 * two parameters - object (query) and interface (strategy implementation - lambda method to process result)
 *
 * */
public class SqlHelper {
    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void queryExecute(String query) {
        queryExecute(query, PreparedStatement::execute);
    }

    public <T> T queryExecute(String query, SqlSerializer<T> sqlSerializer) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
        ) {
            return sqlSerializer.paramExecute(ps);
        } catch (SQLException e) {
//            https://www.postgresql.org/message-id/D960CB61B694CF459DCFB4B0128514C27F6E2C@exadv11.host.magwien.gv.at
//            System.out.println("Error code: " + e.getErrorCode());
//            System.out.println("State: " + e.getSQLState());
            if (e.getSQLState().equals("23505")) {
                throw new ExistStorageException(null);
            }
            throw new StorageException(e);
        }
    }
}