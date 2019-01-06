package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
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
    protected boolean isKeyExist(Integer searchKey) {
        return searchKey != null;
    }

    @Override
    protected void realUpdate(Resume resume, Integer searchKey) {
        storage.set(searchKey, resume);
    }

    @Override
    public List<Resume> getStorageCopyList() {
        return new ArrayList<>(storage);
    }

    @Override
    protected void realSave(Resume resume, Integer searchKey) {
        storage.add(resume);
    }

    @Override
    protected void realDelete(Integer searchKey) {
        storage.remove((int) searchKey);
    }

    @Override
    protected Resume realGet(Integer searchKey) {
        return storage.get(searchKey);
    }
}