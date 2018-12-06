package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected void realUpdate(Resume resume, Object index) {
        storage[(int) index] = resume;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected void realSave(Resume resume, Object index) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow.", resume.getUuid());
        } else {
            insertElement(resume, (int) index);
            size++;
        }
    }

    protected void realDelete(String uuid, Object index) {
        fillDeletedElement((int) index);
        storage[size - 1] = null;
        size--;
    }

    protected Resume realGet(String uuid, Object index) {
        return storage[(int) index];
    }

    protected boolean isKeyExist(Object index) {
        return (int) index >= 0;

    }

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(Resume resume, int index);

}
