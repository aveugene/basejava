package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.serialization.Serialization;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private Path directory;
    private Serialization serialization;

    public void setSerialization(Serialization serialization) {
        this.serialization = serialization;
    }

    public PathStorage(String dir, Serialization serialization) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "Directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directorey or not writable");
        }
        this.serialization = serialization;
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    protected void updateResume(Resume resume, Path path) {
        try {
            serialization.writeResume(resume, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Failed to write to file", getFileName(path), e);
        }
    }

    @Override
    protected void saveResume(Resume resume, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Failed to create file", getFileName(path), e);
        }
        updateResume(resume, path);
    }

    @Override
    protected void deleteResume(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Failed to delete file", getFileName(path), e);
        }
    }

    @Override
    protected Resume getResume(Path path) {
        try {
            return serialization.readResume(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Failed to read from file", getFileName(path), e);
        }
    }

    @Override
    protected List<Resume> getAllResumes() {
        try {
            return getFilesList().map(this::getResume).collect(Collectors.toList());
        } catch (IOException e) {
            throw new StorageException("Failed to get all resumes", e);
        }
    }

    @Override
    public void clear() {
        try {
            getFilesList().forEach(this::deleteResume);
        } catch (IOException e) {
            throw new StorageException("Failed to clear all resumes", e);
        }

    }

    @Override
    public int size() {
        try {
            return (int) getFilesList().count();
        } catch (IOException e) {
            throw new StorageException("Failed to count files in directory", e);
        }
    }

    private String getFileName(Path path) {
        return path.getFileName().toString();
    }

    private Stream<Path> getFilesList() throws IOException {
        return Files.list(directory);
    }
}
