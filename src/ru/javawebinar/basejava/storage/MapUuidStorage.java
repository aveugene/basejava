package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage {
    Map<String, Resume> storage = new HashMap<>();

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isKeyExist(Object searchKey) {
        return storage.containsKey((String) searchKey);
    }

    @Override
    protected void realUpdate(Resume resume, Object searchKey) {
        storage.replace((String) searchKey, resume);
    }

    @Override
    public List<Resume> getStorageCopyList() {
        return new ArrayList<>(storage.values());
    }

    @Override
    protected void realSave(Resume resume, Object searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void realDelete(Object searchKey) {
        storage.remove((String) searchKey);
    }

    @Override
    protected Resume realGet(Object searchKey) {
        return storage.get((String) searchKey);
    }
}