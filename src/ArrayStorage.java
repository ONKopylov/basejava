import java.util.Arrays;
import java.util.function.IntFunction;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[3];
    int size;

    void clear() {
        Arrays.fill(storage, null);
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
            if (storage[i].uuid == uuid){
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        Resume deletedElem = get(uuid);
        int indexDeletedElem;

        if (deletedElem != null) {

            //Присваиваем найденному элементу нулл
            indexDeletedElem = Arrays.asList(storage).indexOf(deletedElem);
            storage[indexDeletedElem] = null;

            //Изменяем размер
            size--;

            //Сдвигаем все элементы после удаленного
            for (int i = indexDeletedElem; i < size; i++) {
                storage[i] = storage [i+1];
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
//        return new Resume[0];
        return Arrays.copyOf(storage, storage.length);
    }

    int size() {
        return size;
    }
}
