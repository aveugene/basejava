package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        sqlHelper.<Void>queryExecute("INSERT INTO resume (uuid, full_name) VALUES (?, ?)", preparedStatement -> {
            preparedStatement.setString(1, resume.getUuid());
            preparedStatement.setString(2, resume.getFullName());
            preparedStatement.execute();
            return null;
        });
        for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
            sqlHelper.<Void>queryExecute("INSERT INTO contact (resume_uuid, type, value) VALUES (?, ?, ?)", preparedStatement -> {
                preparedStatement.setString(1, resume.getUuid());
                preparedStatement.setString(2, entry.getKey().name());
                preparedStatement.setString(3, entry.getValue());
                preparedStatement.execute();
                return null;
            });
        }
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.<Void>queryExecute("UPDATE resume SET full_name = ? WHERE uuid = ?", preparedStatement -> {
            preparedStatement.setString(1, resume.getFullName());
            preparedStatement.setString(2, resume.getUuid());
            if (preparedStatement.executeUpdate() == 0) throw new NotExistStorageException(resume.getUuid());
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
                    ResultSet rs = preparedStatement.executeQuery();
                    if (!rs.next()) throw new NotExistStorageException(uuid);
                    Resume resume = new Resume(uuid, rs.getString("full_name"));
                    do {
                        String value = rs.getString("value");
                        ContactType type = ContactType.valueOf(rs.getString("type"));
                        resume.addContact(type, value);
                    } while (rs.next());
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
        return sqlHelper.queryExecute("SELECT * FROM resume r ORDER BY full_name,uuid", preparedStatement -> {
            ResultSet rs = preparedStatement.executeQuery();
            List<Resume> resumes = new ArrayList<>();
            while (rs.next()) {
                resumes.add(new Resume(rs.getString("uuid"), rs.getString("full_name")));
            }
            return resumes;
        });
    }

    @Override
    public int size() {
        return sqlHelper.queryExecute("SELECT COUNT(*) FROM resume", preparedStatement -> {
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next() ? rs.getInt("count") : 0;
        });
    }
}
