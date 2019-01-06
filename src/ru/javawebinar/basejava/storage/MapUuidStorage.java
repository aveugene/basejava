package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage<String> {
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
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isKeyExist(String searchKey) {
        return storage.containsKey(searchKey);
    }

    @Override
    protected void realUpdate(Resume resume, String searchKey) {
        storage.replace(searchKey, resume);
    }

    @Override
    public List<Resume> getStorageCopyList() {
        return new ArrayList<>(storage.values());
    }

    @Override
    protected void realSave(Resume resume, String searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void realDelete(String searchKey) {
        storage.remove(searchKey);
    }

    @Override
    protected Resume realGet(String searchKey) {
        return storage.get(searchKey);
    }
}