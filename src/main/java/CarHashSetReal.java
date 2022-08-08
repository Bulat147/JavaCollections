import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;

/** Но на самом деле HashSet реализуется под капотом с помощью HashMap, поэтому в случае
 *  одной большой коллизии - в ячейке будет не LnkedList, а TreeMap */

public class CarHashSetReal<T> implements CarSet<T>{

    private final HashMap<T, Object> cars = new HashMap<>();
    private final Object obj = new Object();

    @Override
    public boolean add(T car) {
       Object added = cars.put(car, obj);
       return added != null;
    }

    @Override
    public boolean remove(T car) {
        Object removed = cars.remove(car);
        return removed != null;
    }

    @Override
    public boolean contains(T car) {
        return cars.containsKey(car);
    }

    @Override
    public int size() {
        return cars.size();
    }

    @Override
    public void clear() {
        cars.clear();
    }

    @Override
    public Iterator<T> iterator() {
        return cars.keySet().iterator(); // Так мы просто итерируемся по Set ключей в HashMap
    }
}
