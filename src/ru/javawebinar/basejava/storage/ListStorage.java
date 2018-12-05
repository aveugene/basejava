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
    protected int getIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) return i;
        }
        return -1;
    }

    protected void realUpdate(Resume resume, int index) {
        storage.set(index, resume);
    }

    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    protected void realSave(Resume resume, int index) {
        storage.add(resume);
    }

    protected void realDelete(String uuid, int index) {
        storage.remove(index);
    }

    protected Resume realGet(String uuid, int index) {
        return storage.get(index);
    }
}