package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> storage = new ArrayList<>();

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) return i;
        }
        return null;
    }

    @Override
    protected boolean isKeyExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected void realUpdate(Resume resume, Object searchKey) {
        storage.set((Integer) searchKey, resume);
    }

    @Override
    public List<Resume> getList() {
        return storage;
    }

    @Override
    protected void realSave(Resume resume, Object searchKey) {
        storage.add(resume);
    }

    @Override
    protected void realDelete(Object searchKey) {
        storage.remove((int) searchKey);
    }

    @Override
    protected Resume realGet(Object searchKey) {
        return storage.get((Integer) searchKey);
    }
}