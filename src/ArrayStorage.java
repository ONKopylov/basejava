import java.util.Arrays;
import java.util.function.IntFunction;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10];
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
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid == uuid) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        Resume deletedElem = get(uuid);
        int indexDeletedElem = indexOfElem(deletedElem);

        if (indexDeletedElem >= 0) {
            //Присваиваем найденному элементу нулл
            storage[indexDeletedElem] = null;

            //Изменяем размер
            size--;

            //Сдвигаем все элементы после удаленного
            for (int i = indexDeletedElem; i < size; i++) {
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

    int indexOfElem(Resume elem) {
        int indexDeletedElem = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == elem) {
                return i;
            }
        }
        return -1;
    }
}
