package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static ru.javawebinar.basejava.ResumeTestData.createResume;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = Config.get().getStorageDir();
    protected Storage storage;
    private static final String UUID_1 = UUID.randomUUID().toString();
    private static final Resume RESUME_1;
    private static final String UUID_2 = UUID.randomUUID().toString();
    private static final Resume RESUME_2;
    private static final String UUID_3 = UUID.randomUUID().toString();
    private static final Resume RESUME_3;
    private static final String UUID_4 = UUID.randomUUID().toString();
    private static final Resume RESUME_4;

    static {
        RESUME_1 = createResume(UUID_1, "A Will Turner");
        RESUME_2 = createResume(UUID_2, "B Fill Turner");
        RESUME_3 = createResume(UUID_3, "C Elizabeth Swan");
        RESUME_4 = createResume(UUID_4, "D John Swan");
    }

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_2);
        storage.save(RESUME_1);
        storage.save(RESUME_3);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void update() {
        Resume newResume2 = new Resume(UUID_2, "Bill Turner");
        storage.update(newResume2);
        assertEquals(newResume2, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_4);
    }

    @Test
    public void getAllSorted() {
        List<Resume> resumeListExpected = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);
        List<Resume> resumeListActual = storage.getAllSorted();
        assertEquals(resumeListExpected.size(), resumeListActual.size());
        assertEquals(resumeListExpected, resumeListActual);
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_2);
        assertSize(2);
        storage.get(UUID_2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_4);
    }

    @Test
    public void get() {
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_4);
    }

    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }
    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }
}