/**
 * Интерфейс коллекций с уникальными элементами
 */
public interface CarSet {
    boolean add(Car car);
    boolean remove(Car car);
    int size();
    void clear();
}
