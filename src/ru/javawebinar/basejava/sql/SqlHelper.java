package ru.javawebinar.basejava.sql;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
            if (e.getSQLState().equals("23505")) {
                throw new ExistStorageException(null);
            }
            throw new StorageException(e);
        }
    }
}