package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface StreamSerializer {
    void writeResume(Resume resume, OutputStream outputStream) throws IOException;

    Resume readResume(InputStream inputStream) throws IOException;
}
