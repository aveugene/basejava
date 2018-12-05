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
    protected int getIndex(String uuid) {
        for (String storageUuid : storage.keySet()) {
            if (storageUuid.equals(uuid)){
                return 1;
            }
        }
        return -1;
    }

    protected void realUpdate(Resume resume, int index) {
        storage.put(resume.getUuid(), resume);
    }

    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    protected void realSave(Resume resume, int index) {
        storage.put(resume.getUuid(), resume);
    }

    protected void realDelete(String uuid, int index) {
        storage.remove(uuid);
    }

    protected Resume realGet(String uuid, int index) {
        return storage.get(uuid);
    }
}