package ru.javawebinar.basejava.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SqlSerializer<T> {
    T paramExecute(PreparedStatement preparedStatement) throws SQLException;
}