package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractStorage implements Storage {
    protected static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid);

    public List<Resume> getAllSorted() {
        List<Resume> returnList = getList();
        return returnList.stream().sorted(RESUME_COMPARATOR).collect(Collectors.toList());
    }

    public void update(Resume resume) {
        Object searchKey = getKeyIfExist(resume.getUuid());
        realUpdate(resume, searchKey);
    }

    public void save(Resume resume) {
        Object searchKey = getKeyIfNotExist(resume.getUuid());
        realSave(resume, searchKey);
    }

    public void delete(String uuid) {
        Object searchKey = getKeyIfExist(uuid);
        realDelete(searchKey);
    }

    public Resume get(String uuid) {
        Object searchKey = getKeyIfExist(uuid);
        return realGet(searchKey);
    }

    private Object getKeyIfExist(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isKeyExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getKeyIfNotExist(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isKeyExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean isKeyExist(Object searchKey);

    protected abstract void realUpdate(Resume resume, Object searchKey);

    protected abstract void realSave(Resume resume, Object searchKey);

    protected abstract void realDelete(Object searchKey);

    protected abstract Resume realGet(Object searchKey);

    protected abstract List<Resume> getList();
}
