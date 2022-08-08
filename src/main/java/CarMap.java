import java.util.List;
import java.util.Set;

public interface CarMap<K, V> {

    V get(K key);
    boolean remove(K key);
    void put(K key, V value);
    Set<K> keySet(); // Set потому что владельцы - это уникальные объекты
    List<V> getValues();  // List потому что у одной машины могут быть несколько владельцев
    int size();
    void clear();
}
