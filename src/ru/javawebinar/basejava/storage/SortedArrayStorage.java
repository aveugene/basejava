package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void fillDeletedElement(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(storage, index + 1, storage, index, numMoved);
        }
    }

    @Override
    protected void insertElement(Resume resume, int index) {
        int indexComplemented = ~index;
        System.arraycopy(storage, indexComplemented, storage, indexComplemented + 1, size - indexComplemented);
        storage[indexComplemented] = resume;
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        return Arrays.binarySearch(storage, 0, size, new Resume(uuid, "dummy"), RESUME_COMPARATOR);
    }
}
