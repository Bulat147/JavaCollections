/**
 * Интерфейс коллекций с уникальными элементами
 *
 * Почему нельзя просто использовать для них интерфейс List? - Потому что в них нельзя реализовать методы с индексом,
 * ведь Set - неиндексируемая структура.
 */
public interface CarSet extends CarCollection{
    boolean add(Car car);
    boolean remove(Car car);
    int size();
    void clear();
}
