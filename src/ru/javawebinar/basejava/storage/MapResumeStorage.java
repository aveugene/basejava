package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage<Resume> {
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
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected boolean isKeyExist(Resume searchKey) {
        return searchKey != null;
    }

    @Override
    protected void realUpdate(Resume resume, Resume searchKey) {
        storage.replace(resume.getUuid(), resume);
    }

    @Override
    public List<Resume> getStorageCopyList() {
        return new ArrayList<>(storage.values());
    }

    @Override
    protected void realSave(Resume resume, Resume searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void realDelete(Resume searchKey) {
        storage.remove((searchKey).getUuid());
    }

    @Override
    protected Resume realGet(Resume searchKey) {
        return searchKey;
    }
}