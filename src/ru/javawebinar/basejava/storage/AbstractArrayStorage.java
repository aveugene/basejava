package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
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

    @Override
    protected void realUpdate(Resume resume, Integer searchKey) {
        storage[searchKey] = resume;
    }

    @Override
    public List<Resume> getStorageCopyList() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    @Override
    protected void realSave(Resume resume, Integer searchKey) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow.", resume.getUuid());
        } else {
            insertElement(resume, searchKey);
            size++;
        }
    }

    @Override
    protected void realDelete(Integer searchKey) {
        fillDeletedElement(searchKey);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected Resume realGet(Integer searchKey) {
        return storage[(Integer) searchKey];
    }

    protected abstract Integer getSearchKey(String uuid);

    protected boolean isKeyExist(Integer searchKey) {
        return searchKey >= 0;

    }

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(Resume resume, int index);

}
