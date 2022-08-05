import java.util.*;

/** У реального HashMap есть переход ячеек из LinkedList в TreeSet для производительности */
public class CarHashMap implements CarMap{
    private final int INITIAL_CAPACITY = 10;
    private static final double LOAD_FACTOR = 0.75;
    private Entry[] array = new Entry[INITIAL_CAPACITY];
    private int size = 0;

    @Override
    public Car get(CarOwner key) {
        int index = getPosition(key, array.length);
        if (array[index] == null){
            return null;
        }else{
            Entry current = array[index];
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
    public boolean remove(CarOwner key) {
        int index = getPosition(key, array.length);
        if (array[index] == null){
            return false;
        }else if(array[index].key.equals(key)){
            array[index] = null;
            size--;
            return true;
        }else{
            Entry last = array[index];
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
    public void put(CarOwner key, Car value) { // Вынести отдельным методом putInto - НЕТ, просто перегруженным put!!!
        if (size >= (array.length * LOAD_FACTOR)){
            increaseArray();
        }
        boolean added = put(key, value, array);
        if (added){
            size++;
        }
    }

    public boolean put(CarOwner key, Car value, Entry[] someArray){
        int position = getPosition(key, someArray.length);
        if (someArray[position] == null){
            Entry empty = new Entry(key, value, null);
            someArray[position] = empty;
            return true;
        }else{
            Entry current = someArray[position];
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
    public Set<CarOwner> keySet() {
        Set<CarOwner> carOwners = new HashSet<>();
        for (Entry entry : array){
            Entry existedEntry = entry;
            while (existedEntry != null){
                carOwners.add(existedEntry.key);
                existedEntry = existedEntry.next;
            }
        }
        return carOwners;
    }

    @Override
    public List<Car> getValues() {
        List<Car> cars = new ArrayList<>();
        for (Entry entry : array){
            Entry existedEntry = entry;
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
        array = new Entry[INITIAL_CAPACITY];
        size = 0;
    }

    private void increaseArray(){
        Entry[] newArray = new Entry[array.length*2];
        for (Entry entry : array){
            Entry existedEntry = entry;
            while (existedEntry != null){
                put(existedEntry.key, existedEntry.value, newArray);
                existedEntry = existedEntry.next;
            }
        }
        array = newArray;
    }



    private int getPosition(CarOwner key, int arrayLength){
        return Math.abs(key.hashCode() % arrayLength);
    }

    static private class Entry{
        public CarOwner key;
        public Car value;
        public Entry next;

        public Entry(CarOwner key, Car value, Entry next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
