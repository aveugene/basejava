package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid);

    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> returnList = getStorageCopyList();
//        return returnList.stream().sorted(RESUME_COMPARATOR).collect(Collectors.toList());
        Collections.sort(returnList);
        return returnList;
    }

    public void update(Resume resume) {
        LOG.info("Update " + resume);
        SK searchKey = getKeyIfExist(resume.getUuid());
        realUpdate(resume, searchKey);
    }

    public void save(Resume resume) {
        LOG.info("Save " + resume);
        SK searchKey = getKeyIfNotExist(resume.getUuid());
        realSave(resume, searchKey);
    }

    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        SK searchKey = getKeyIfExist(uuid);
        realDelete(searchKey);
    }

    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        SK searchKey = getKeyIfExist(uuid);
        return realGet(searchKey);
    }

    private SK getKeyIfExist(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isKeyExist(searchKey)) {
            LOG.warning("Resume " + uuid + " not exist.");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getKeyIfNotExist(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isKeyExist(searchKey)) {
            LOG.warning("Resume " + uuid + " already exist.");
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
