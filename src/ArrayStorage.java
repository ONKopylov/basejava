import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size;

    void clear() {
        Arrays.fill(storage, 0, size - 1, null);
        size = 0;
    }

    void save(Resume r) {
        if (size == storage.length) {
            System.out.println("Резюме не сохранено, переполнение памяти!");
        } else {
            storage[size] = r;
            size++;
        }
    }

    Resume get(String uuid) {
        int indexResume = findIndex(uuid);
        if (indexResume >= 0) {
            return storage[indexResume];
        }
        return null;
    }

    void delete(String uuid) {
        //Resume deletedResume = get(uuid);
        int indexDeletedResume = findIndex(uuid);

        if (indexDeletedResume >= 0) {
            //Присваиваем найденному элементу нулл
            storage[indexDeletedResume] = null;

            //Изменяем размер
            size--;

            //Сдвигаем все элементы после удаленного
            for (int i = indexDeletedResume; i < size; i++) {
                storage[i] = storage[i + 1];
            }

            if (size == (storage.length - 1)) {
                storage[storage.length - 1] = null;
            }
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
        for (int i = 0; i < size - 1; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
