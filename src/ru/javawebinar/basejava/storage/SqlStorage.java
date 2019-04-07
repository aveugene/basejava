package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.*;
import java.util.*;

public class SqlStorage implements Storage {
    private final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.queryExecute("DELETE FROM resume");
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.transactionalExecute(connection -> {
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?, ?)")) {
                preparedStatement.setString(1, resume.getUuid());
                preparedStatement.setString(2, resume.getFullName());
                preparedStatement.execute();
            }
            insertContacts(resume, connection);
            return null;
        });
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.transactionalExecute(connection -> {
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
                preparedStatement.setString(1, resume.getFullName());
                preparedStatement.setString(2, resume.getUuid());
                if (preparedStatement.executeUpdate() == 0) throw new NotExistStorageException(resume.getUuid());
            }
            deleteContacts(resume);
            insertContacts(resume, connection);
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.queryExecute("" +
                        "   SELECT * FROM resume r " +
                        "LEFT JOIN contact c " +
                        "       ON r.uuid = c.resume_uuid " +
                        "    WHERE r.uuid = ? ",
                preparedStatement -> {
                    preparedStatement.setString(1, uuid);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (!resultSet.next()) throw new NotExistStorageException(uuid);
                    Resume resume = new Resume(uuid, resultSet.getString("full_name"));
                    do {
                        addContact(resultSet, resume);
                    } while (resultSet.next());
                    return resume;
                });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.<Void>queryExecute("DELETE FROM resume r WHERE r.uuid = ?", preparedStatement -> {
            preparedStatement.setString(1, uuid);
            if (preparedStatement.executeUpdate() == 0) throw new NotExistStorageException(uuid);
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.transactionalExecute(connection -> {
            Map<String, Resume> resumeMap = new LinkedHashMap<>();
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM resume r ORDER BY full_name,uuid")) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String uuid = resultSet.getString("uuid");
                    Resume resume = new Resume(uuid, resultSet.getString("full_name"));
                    resumeMap.put(uuid, resume);
                }
            }
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM contact")) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Resume resume = resumeMap.get(resultSet.getString("resume_uuid"));
                    addContact(resultSet, resume);
                }
            }
            return new ArrayList<>(resumeMap.values());
        });
    }

    @Override
    public int size() {
        return sqlHelper.queryExecute("SELECT COUNT(*) FROM resume", preparedStatement -> {
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next() ? rs.getInt("count") : 0;
        });
    }

    private void insertContacts(Resume resume, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?, ?, ?)")) {
            for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
                System.out.println();
                preparedStatement.setString(1, resume.getUuid());
                preparedStatement.setString(2, entry.getKey().name());
                preparedStatement.setString(3, entry.getValue());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        }
    }

    private void deleteContacts(Resume resume) {
        sqlHelper.queryExecute("DELETE FROM contact WHERE resume_uuid=?", preparedStatement -> {
            preparedStatement.setString(1, resume.getUuid());
            preparedStatement.execute();
            return null;
        });
    }

    private void addContact(ResultSet resultSet, Resume resume) throws SQLException {
        String value = resultSet.getString("value");
        ContactType type = ContactType.valueOf(resultSet.getString("type"));
        if (value != null) {
            resume.addContact(type, value);
        }
    }
}
