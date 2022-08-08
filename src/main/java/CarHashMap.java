import java.util.*;

/** У реального HashMap есть переход ячеек из LinkedList в TreeSet для производительности */
public class CarHashMap<K, V> implements CarMap<K, V>{
    private final int INITIAL_CAPACITY = 10;
    private static final double LOAD_FACTOR = 0.75;
    private Object[] array = new Object[INITIAL_CAPACITY];
    private int size = 0;

    @Override
    public V get(K key) {
        int index = getPosition(key, array.length);
        if (array[index] == null){
            return null;
        }else{
            Entry current = (Entry) array[index];
            do{
                if(current.key.equals(key)){ // Чуть не ошибся - ссылочные типы сравниваем через equals
                    return current.value;
                }
                current = current.next;
            }while(current != null);
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        int index = getPosition(key, array.length);
        Entry headEntry = (Entry) array[index];
        if (headEntry == null){
            return false;
        }else if(headEntry.key.equals(key)){
            array[index] = null;
            size--;
            return true;
        }else{
            Entry last = (Entry) array[index];
            Entry current = last.next;
            while(true) {
                if (current == null) {
                    return false;
                } else if (current.key.equals(key)) {
                    last.next = current.next;
                    size--;
                    return true;
                }
                last = current;
                current = current.next;
            }
        }
    }

    @Override
    public void put(K key, V value) { // Вынести отдельным методом putInto - НЕТ, просто перегруженным put!!!
        if (size >= (array.length * LOAD_FACTOR)){
            increaseArray();
        }
        boolean added = put(key, value, array);
        if (added){
            size++;
        }
    }

    public boolean put(K key, V value, Object[] someArray){
        int position = getPosition(key, someArray.length);
        if (someArray[position] == null){
            Entry empty = new Entry(key, value, null);
            someArray[position] = empty;
            return true;
        }else{
            Entry current = (Entry) someArray[position];
            do{
                if(current.key.equals(key)){
                    current.value = value;
                    return false;
                }else if(current.next == null){
                    current.next = new Entry(key, value, null);
                    return true;
                }
                current = current.next;
            }while(true);
        }
    }

    @Override
    public Set<K> keySet() {
        Set<K> carOwners = new HashSet<>();
        for (Object entry : array){
            Entry existedEntry = (Entry) entry;
            while (existedEntry != null){
                carOwners.add(existedEntry.key);
                existedEntry = existedEntry.next;
            }
        }
        return carOwners;
    }

    @Override
    public List<V> getValues() {
        List<V> cars = new ArrayList<>();
        for (Object entry : array){
            Entry existedEntry = (Entry) entry;
            while (existedEntry != null){
                cars.add(existedEntry.value);
                existedEntry = existedEntry.next;
            }
        }
        return cars;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        array = new Object[INITIAL_CAPACITY];
        size = 0;
    }

    private void increaseArray(){
        Object[] newArray = new Object[array.length*2];
        for (Object entry : array){
            Entry existedEntry = (Entry) entry;
            while (existedEntry != null){
                put(existedEntry.key, existedEntry.value, newArray);
                existedEntry = existedEntry.next;
            }
        }
        array = newArray;
    }



    private int getPosition(K key, int arrayLength){
        return Math.abs(key.hashCode() % arrayLength);
    }

    private class Entry{
        public K key;
        public V value;
        public Entry next;

        public Entry(K key, V value, Entry next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
