package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Map;
import java.util.TreeMap;

public class MapStorage extends AbstractStorage {
    Map<String, Resume> storage = new TreeMap<>();

    public int size() {
        return storage.size();
    }

    public void clear() {
        storage.clear();
    }

    @Override
    protected Object getKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isKeyExist(Object searchKey) {
       return storage.containsKey((String) searchKey);
    }

    protected void realUpdate(Resume resume, Object searchKey) {
        storage.replace((String) searchKey, resume);
    }

    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    protected void realSave(Resume resume, Object searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    protected void realDelete(Object searchKey) {
        storage.remove((String) searchKey);
    }

    protected Resume realGet(Object searchKey) {
        return storage.get((String) searchKey);
    }
}