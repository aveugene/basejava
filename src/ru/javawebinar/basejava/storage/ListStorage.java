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
    protected boolean isKeyExist(Object index) {
        return (int) index >= 0;
    }

    protected void realUpdate(Resume resume, Object index) {
        storage.set((int) index, resume);
    }

    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    protected void realSave(Resume resume, Object index) {
        storage.add(resume);
    }

    protected void realDelete(String uuid, Object index) {
        storage.remove((int) index);
    }

    protected Resume realGet(String uuid, Object index) {
        return storage.get((int) index);
    }
}