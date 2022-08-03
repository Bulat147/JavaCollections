/** Иерархия коллекций:
 *      Iterable (интерфейс)
 *      -> Collection (интерфейс)
 *      -> List (интерфейс)                 -> Set (интерфейс)
 *      -> ArrayList    ->LinkedList        -> HashSet       -> TreeSet
 *
 */
public interface CarCollection extends Iterable<Car>{
    boolean add(Car car);
    boolean remove(Car car);
    int size();
    void clear();
    boolean contains(Car car);
}
