package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import static org.junit.Assert.*;

public abstract class AbstractStorageTest {
    protected Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String FULLNAME_1 = "Will Turner";
    private static final Resume RESUME_1;
    private static final String UUID_2 = "uuid2";
    private static final String FULLNAME_2 = "Bill Turner";
    private static final Resume RESUME_2;
    private static final String UUID_3 = "uuid3";
    private static final String FULLNAME_3 = "Elizabeth Swan";
    private static final Resume RESUME_3;
    private static final String UUID_4 = "uuid4";
    private static final String FULLNAME_4 = "John Swan";
    private static final Resume RESUME_4;

    static {
        RESUME_1 = new Resume(UUID_1, FULLNAME_1);
        RESUME_2 = new Resume(UUID_2, FULLNAME_2);
        RESUME_3 = new Resume(UUID_3, FULLNAME_3);
        RESUME_4 = new Resume(UUID_4, FULLNAME_4);
    }

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
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
        Resume newResume2 = new Resume(UUID_2);
        storage.update(newResume2);
        assertSame(newResume2, storage.get(newResume2.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_4);
    }

    @Test
    public void getAll() {
        Resume[] resumeArrayExpected = {RESUME_1, RESUME_2, RESUME_3};
        Resume[] resumeArrayActual = storage.getAll();
        assertEquals(resumeArrayExpected.length, resumeArrayActual.length);
        assertArrayEquals(resumeArrayExpected, resumeArrayActual);
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