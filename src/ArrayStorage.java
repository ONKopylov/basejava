import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume r) {
        if (findIndex(r.uuid) >= 0) {
            System.out.println("Резюме не сохранено, т.к. такое резюме уже зарегистрировано! uuid: " + r.uuid);
            return;
        }

        if (size == storage.length) {
            System.out.println("Резюме не сохранено, переполнение памяти!");
            return;
        }

        storage[size] = r;
        size++;
    }

    Resume get(String uuid) {
        int indexForGet = findIndex(uuid);
        if (indexForGet >= 0) {
            return storage[indexForGet];
        }
        return null;
    }

    void delete(String uuid) {
        int indexForDel = findIndex(uuid);

        if (indexForDel >= 0) {
            size--;

            //Сдвигаем все элементы после удаленного
            for (int i = indexForDel; i < size - 1; i++) {
                storage[i] = storage[i + 1];
            }
            storage[size] = null;
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

    private int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
