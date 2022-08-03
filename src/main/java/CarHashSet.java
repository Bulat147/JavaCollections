import java.util.Iterator;

public class CarHashSet implements CarSet{
    private final int INITIAL_CAPACITY = 16;
    private Entry[] array = new Entry[INITIAL_CAPACITY]; // вначале - 16 св€зных списков
    private int size = 0; // показывает кол-во элементов, а не размер array
    private final double LOAD_FACTOR = 0.75;

    @Override
    public boolean add(Car car) {
        if (size >= array.length*LOAD_FACTOR) {
            // ѕроверку на лоуд-фактор написал здесь, а не в increaseArray, потому что ф-ци€ должна
            // выполн€ть как можно более специфичную задачу.
            increaseArray();
        }
        boolean added = add(car, array);
        if (added){
            size++;
        }
        return added;
    }

    private boolean add(Car car, Entry[] someArray) {
        int position = getElementPosition(car, someArray.length);
        Entry temp = someArray[position];
        // »нтересный вариант написани€ кода - сначала писать один шаг и только потом его обхватывать циклом
        if (temp == null){
            someArray[position] = new Entry(car, null);
        }else {
            while (true){
                if (car.equals(temp.car)){
                    return false;
                } else if (temp.next == null){
                    temp.next = new Entry(car, null);
                    break;
                } else{
                    temp = temp.next;
                }
            }
        }
        return true;
    }

    @Override
    public boolean remove(Car car) {
        int position = getElementPosition(car, array.length);
        Entry temp = array[position];
        if (temp == null){
            return false;
        }else if(temp.car == car){
            array[position] = temp.next;
            size--;
            return true;
        }else{
            Entry last = temp;
            temp = temp.next;;
            while(temp != null){
                if (temp.car == car) {
                    last.next = temp.next;
                    size--;
                    return true;
                }
                last = temp;
                temp = temp.next;
            }
            return false;
        }
    }

    @Override
    public boolean contains(Car car) {
        int position = getElementPosition(car, array.length);
        Entry entry = array[position];
        while (entry != null){
            if (entry.car.equals(car)){
                return true;
            }
            entry = entry.next;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        array = new Entry[16];
        size = 0;
    }

    private void increaseArray(){
        // ћы увеличиваем capacity, но size остаетс€ таким же -> его не трогаем
        Entry[] newArray = new Entry[array.length*2];
        for (Entry entry: array){
            Entry temp = entry;
            while (temp != null){
                add(temp.car, newArray); // ¬от здесь понадобилось, чтобы add было не прив€зано к array
                temp = temp.next;
            }
        }
        array = newArray;
    }

    private static int getElementPosition(Car car, int arrayLength){
        // hashcode может быть отрицательным, тогда может быть ошибка, поэтому нужно брать abs
        return Math.abs(car.hashCode() % arrayLength);
    }

    @Override
    public Iterator<Car> iterator() {
        return new Iterator<Car>() {
            private int index = 0;
            private Entry entry = array[index];
            @Override
            public boolean hasNext() {
                while (index < array.length) { // именно длина массива, а не size - тут она как кол-во элементов
                    if (entry != null) {
                        return true;
                    }
                    index++;
                    if(index >= array.length){
                        break;
                    }
                    entry = array[index];
                }
                return false;
            }

            @Override
            public Car next() {
                Car car = entry.car;
                entry = entry.next;
                return car;
            }
        };
    }

    private static class Entry { // ¬нутренние св€зные списки
        private final Car car;
        private Entry next;

        public Entry(Car car, Entry next){
            this.car = car;
            this.next = next;
        }
    }
}
