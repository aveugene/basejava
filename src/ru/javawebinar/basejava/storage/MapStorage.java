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
        if (storage.containsKey(uuid)) {
            return uuid;
        }
        return null;
    }

    @Override
    protected boolean checkKey(Object index) {
        return index != null;
    }

    protected void realUpdate(Resume resume, Object index) {
        storage.put(resume.getUuid(), resume);
    }

    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    protected void realSave(Resume resume, Object index) {
        storage.put(resume.getUuid(), resume);
    }

    protected void realDelete(String uuid, Object index) {
        storage.remove(uuid);
    }

    protected Resume realGet(String uuid, Object index) {
        return storage.get(uuid);
    }
}