/** Иерархия коллекций:
 *      Iterable (интерфейс)
 *      -> Collection (интерфейс)
 *      -> List (интерфейс)                 -> Set (интерфейс)
 *      -> ArrayList    ->LinkedList        -> HashSet       -> TreeSet
 *
 */
public interface CarCollection<T> extends Iterable<T>{ // Делаем интерфейс параметризованным
    boolean add(T car);
    boolean remove(T car);
    int size();
    void clear();
    boolean contains(T car);
}
