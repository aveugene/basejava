package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected int getIndex(String uuid) {
        return Arrays.binarySearch(storage, 0, size, new Resume(uuid));
    }

    @Override
    protected void insertResume(Resume resume, int index) {
        int indexComplemented = ~index;
        System.arraycopy(storage, indexComplemented, storage, indexComplemented + 1, size - indexComplemented);
        storage[indexComplemented] = resume;
    }

    @Override
    protected void deleteResume(int index) {
        if (index < size) {
            System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        }
    }
}
