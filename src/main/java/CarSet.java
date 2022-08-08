/**
 * Интерфейс коллекций с уникальными элементами
 *
 * Почему нельзя просто использовать для них интерфейс List? - Потому что в них нельзя реализовать методы с индексом,
 * ведь Set - неиндексируемая структура.
 */
public interface CarSet<T> extends CarCollection<T>{
    boolean add(T car);
    boolean remove(T car);
    int size();
    void clear();
}
