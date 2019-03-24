package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
        sqlHelper.queryExecute("INSERT INTO resume (uuid, full_name) VALUES (?, ?)", preparedStatement -> {
            preparedStatement.setString(1, resume.getUuid());
            preparedStatement.setString(2, resume.getFullName());
            if (preparedStatement.executeUpdate() == 0){
                throw new ExistStorageException(resume.getUuid());
            };
            return null;
        });
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.queryExecute("UPDATE resume SET full_name = ? WHERE uuid = ?", preparedStatement -> {
            preparedStatement.setString(1, resume.getFullName());
            preparedStatement.setString(2, resume.getUuid());
            if (preparedStatement.executeUpdate() == 0){
                throw new NotExistStorageException(resume.getUuid());
            };
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.queryExecute("SELECT * FROM resume r WHERE r.uuid = ?", preparedStatement -> {
            preparedStatement.setString(1, uuid);
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Resume(uuid, rs.getString("full_name"));
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.queryExecute("DELETE FROM resume r WHERE r.uuid = ?", preparedStatement -> {
            preparedStatement.setString(1, uuid);
            if (preparedStatement.executeUpdate() == 0){
                throw new NotExistStorageException(uuid);
            };
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.queryExecute("SELECT * FROM resume r", preparedStatement -> {
            ResultSet rs = preparedStatement.executeQuery();
            List<Resume> resumeList = new ArrayList<>();
            while (rs.next()){
                resumeList.add(new Resume(rs.getString("uuid").trim(), rs.getString("full_name")));
            }
            return resumeList;
        });
    }

    @Override
    public int size() {
        return sqlHelper.queryExecute("SELECT COUNT(*) FROM resume", preparedStatement -> {
            ResultSet rs = preparedStatement.executeQuery();
            int count = 0;
            while(rs.next()) {
                count = rs.getInt("count");
            }
            return count;
        });
    }
}
