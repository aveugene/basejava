package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage<SK> implements Storage {
    protected static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid);

    public List<Resume> getAllSorted() {
        List<Resume> returnList = getStorageCopyList();
//        return returnList.stream().sorted(RESUME_COMPARATOR).collect(Collectors.toList());
        Collections.sort(returnList);
        return returnList;
    }

    public void update(Resume resume) {
        SK searchKey = getKeyIfExist(resume.getUuid());
        realUpdate(resume, searchKey);
    }

    public void save(Resume resume) {
        SK searchKey = getKeyIfNotExist(resume.getUuid());
        realSave(resume, searchKey);
    }

    public void delete(String uuid) {
        SK searchKey = getKeyIfExist(uuid);
        realDelete(searchKey);
    }

    public Resume get(String uuid) {
        SK searchKey = getKeyIfExist(uuid);
        return realGet(searchKey);
    }

    private SK getKeyIfExist(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isKeyExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getKeyIfNotExist(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isKeyExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract SK getSearchKey(String uuid);

    protected abstract boolean isKeyExist(SK searchKey);

    protected abstract void realUpdate(Resume resume, SK searchKey);

    protected abstract void realSave(Resume resume, SK searchKey);

    protected abstract void realDelete(SK searchKey);

    protected abstract Resume realGet(SK searchKey);

    protected abstract List<Resume> getStorageCopyList();
}
