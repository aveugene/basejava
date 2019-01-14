package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;

    public AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "Directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected void updateResume(Resume resume, File file) {
        try {
            writeResume(resume, file);
        } catch (IOException e) {
            throw new StorageException("Failed to write to file", file.getName(), e);
        }
    }

    @Override
    protected void saveResume(Resume resume, File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Failed to create file", file.getName(), e);
        }
        updateResume(resume, file);
    }

    @Override
    protected void deleteResume(File file) {
        if (!file.delete()) {
            throw new StorageException("Failed to delete file", file.getName());
        }
    }

    @Override
    protected Resume getResume(File file) {
        try {
            return readResume(file);
        } catch (IOException e) {
            throw new StorageException("Failed to read from file", file.getName(), e);
        }
    }


    @Override
    protected List<Resume> getAllResumes() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Failed to read directory", null);
        }
        List<Resume> resumeList = new ArrayList<>(files.length);
        for (File file : files) {
            if (!file.isDirectory()) {
                resumeList.add(getResume(file));
            }
        }
        return resumeList;
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Failed to read directory", null);
        }
        for (File file : files) {
            if (!file.isDirectory()) {
                deleteResume(file);
            }
        }

    }

    @Override
    public int size() {
        String[] list = directory.list();
        if (list == null) {
            throw new StorageException("Failed to read directory", null);
        }
        return list.length;
    }

    protected abstract void writeResume(Resume resume, File file) throws IOException;

    protected abstract Resume readResume(File file) throws IOException;

}
