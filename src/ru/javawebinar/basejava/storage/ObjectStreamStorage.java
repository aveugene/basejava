package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;

public class ObjectStreamStorage extends AbstractFileStorage {
    public ObjectStreamStorage(File directory) {
        super(directory);
    }

    @Override
    protected void writeResume(Resume resume, OutputStream outputStream) throws IOException {
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)){
            objectOutputStream.writeObject(resume);
        }
    }

    @Override
    protected Resume readResume(InputStream inputStream) throws IOException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream((inputStream))){
            return (Resume) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Failed to read resume", null, e);
        }
    }
}
