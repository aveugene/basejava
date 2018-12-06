package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    List<Resume> storage = new ArrayList<>();

    public int size() {
        return storage.size();
    }

    public void clear() {
        storage.clear();
    }

    @Override
    protected Object getKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) return i;
        }
        return -1;
    }

    @Override
    protected boolean isKeyExist(Object searchKey) {
        return (int) searchKey >= 0;
    }

    protected void realUpdate(Resume resume, Object searchKey) {
        storage.set((int) searchKey, resume);
    }

    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    protected void realSave(Resume resume, Object searchKey) {
        storage.add(resume);
    }

    protected void realDelete(Object searchKey) {
        storage.remove((int) searchKey);
    }

    protected Resume realGet(Object searchKey) {
        return storage.get((int) searchKey);
    }
}