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

    protected void realUpdate(Resume resume, Object searchKey) {
        storage[(int) searchKey] = resume;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected void realSave(Resume resume, Object searchKey) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow.", resume.getUuid());
        } else {
            insertElement(resume, (int) searchKey);
            size++;
        }
    }

    protected void realDelete(Object searchKey) {
        fillDeletedElement((int) searchKey);
        storage[size - 1] = null;
        size--;
    }

    protected Resume realGet(Object searchKey) {
        return storage[(int) searchKey];
    }

    protected boolean isKeyExist(Object searchKey) {
        return (int) searchKey >= 0;

    }

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(Resume resume, int index);

}
