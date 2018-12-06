package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        Object index = getKeyIfExist(resume.getUuid());
        realUpdate(resume, index);
    }

    public void save(Resume resume) {
        Object index = getKeyIfNotExist(resume.getUuid());
        realSave(resume, index);
    }

    public void delete(String uuid) {
        Object index = getKeyIfExist(uuid);
        realDelete(uuid, index);
    }

    public Resume get(String uuid) {
        Object index = getKeyIfExist(uuid);
        return realGet(uuid, index);
    }

    private Object getKeyIfExist(String uuid) {
        Object index = getKey(uuid);
        if (!checkKey(index)) {
            throw new NotExistStorageException(uuid);
        }
        return index;
    }

    private Object getKeyIfNotExist(String uuid) {
        Object index = getKey(uuid);
        if (checkKey(index)) {
            throw new ExistStorageException(uuid);
        }
        return index;
    }

    protected abstract Object getKey(String uuid);

    protected abstract boolean checkKey(Object index);

    protected abstract void realUpdate(Resume resume, Object index);

    protected abstract void realSave(Resume resume, Object index);

    protected abstract void realDelete(String uuid, Object index);

    protected abstract Resume realGet(String uuid, Object index);
}
