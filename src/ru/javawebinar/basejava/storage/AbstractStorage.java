package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        int index = getIndexIfUuidInStorage(resume.getUuid());
        realUpdate(resume, index);
    }

    public void save(Resume resume) {
        int index = getIndexIfUuidNotInStorage(resume.getUuid());
        realSave(resume, index);
    }

    public void delete(String uuid) {
        int index = getIndexIfUuidInStorage(uuid);
        realDelete(uuid, index);
    }

    public Resume get(String uuid) {
        int index = getIndexIfUuidInStorage(uuid);
        return realGet(uuid, index);
    }

    private int getIndexIfUuidInStorage(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return index;
    }

    private int getIndexIfUuidNotInStorage(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            throw new ExistStorageException(uuid);
        }
        return index;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void realUpdate(Resume resume, int index);

    protected abstract void realSave(Resume resume, int index);

    protected abstract void realDelete(String uuid, int index);

    protected abstract Resume realGet(String uuid, int index);
}
