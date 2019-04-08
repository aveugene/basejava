package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;

import java.util.UUID;

import static ru.javawebinar.basejava.ResumeTestData.createResume;

public class TestData {
    public static final String UUID_1 = UUID.randomUUID().toString();
    public static final Resume RESUME_1;
    public static final String UUID_2 = UUID.randomUUID().toString();
    public static final Resume RESUME_2;
    public static final String UUID_3 = UUID.randomUUID().toString();
    public static final Resume RESUME_3;
    public static final String UUID_4 = UUID.randomUUID().toString();
    public static final Resume RESUME_4;

    static {
        RESUME_1 = createResume(UUID_1, "A Will Turner");
        RESUME_2 = createResume(UUID_2, "B Fill Turner");
        RESUME_3 = createResume(UUID_3, "C Elizabeth Swan");
        RESUME_4 = createResume(UUID_4, "D John Swan");
    }
}
