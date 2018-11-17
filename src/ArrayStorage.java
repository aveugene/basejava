import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int size;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume resume) {
        if (size >= storage.length) {
            System.out.println("ERROR: Storage is full.");
        } else {
            if (findResumeIndex(resume.getUuid()) == -1) {
                storage[size] = resume;
                size++;
            } else {
                System.out.println("ERROR: Resume is in the Storage already.");
            }
        }
    }

    void update(Resume resume) {
        int resumeIndex = findResumeIndex(resume.getUuid());
        if (resumeIndex > -1) {
            storage[resumeIndex] = resume;
        } else {
            System.out.println("ERROR: Resume not found.");
        }
    }

    Resume get(String uuid) {
        int resumeIndex = findResumeIndex(uuid);
        if (resumeIndex > -1) {
            return storage[resumeIndex];
        }
        System.out.println("ERROR: Resume not found.");
        return null;
    }

    void delete(String uuid) {
        int resumeIndex = findResumeIndex(uuid);
        if (resumeIndex > -1) {
            storage[resumeIndex] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("ERROR: Resume not found.");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }

    private int findResumeIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
