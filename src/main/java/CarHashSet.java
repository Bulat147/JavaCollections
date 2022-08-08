import java.util.Iterator;

public class CarHashSet<T> implements CarSet<T>{
    private final int INITIAL_CAPACITY = 16;
    private Object[] array = new Object[INITIAL_CAPACITY]; // ������� - 16 ������� �������
    private int size = 0; // ���������� ���-�� ���������, � �� ������ array
    private final double LOAD_FACTOR = 0.75;

    @Override
    public boolean add(T car) {
        if (size >= array.length*LOAD_FACTOR) {
            // �������� �� ����-������ ������� �����, � �� � increaseArray, ������ ��� �-��� ������
            // ��������� ��� ����� ����� ����������� ������.
            increaseArray();
        }
        boolean added = add(car, array);
        if (added){
            size++;
        }
        return added;
    }

    private boolean add(T car, Object[] someArray) {
        int position = getElementPosition(car, someArray.length);
        Entry temp = (Entry) someArray[position];
        // ���������� ������� ��������� ���� - ������� ������ ���� ��� � ������ ����� ��� ����������� ������
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
    public boolean remove(T car) {
        int position = getElementPosition(car, array.length);
        Entry temp = (Entry) array[position];
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
    public boolean contains(T car) {
        int position = getElementPosition(car, array.length);
        Entry entry = (Entry) array[position];
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
        array = new Object[16];
        size = 0;
    }

    private void increaseArray(){
        // �� ����������� capacity, �� size �������� ����� �� -> ��� �� �������
        Object[] newArray = new Object[array.length*2];
        for (Object entry: array){
            Entry temp = (Entry) entry;
            while (temp != null){
                add(temp.car, newArray); // ��� ����� ������������, ����� add ���� �� ��������� � array
                temp = temp.next;
            }
        }
        array = newArray;
    }

    private static int getElementPosition(Object car, int arrayLength){
        // hashcode ����� ���� �������������, ����� ����� ���� ������, ������� ����� ����� abs
        return Math.abs(car.hashCode() % arrayLength);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;
            private Entry entry = (Entry) array[index];
            @Override
            public boolean hasNext() {
                while (index < array.length) { // ������ ����� �������, � �� size - ��� ��� ��� ���-�� ���������
                    if (entry != null) {
                        return true;
                    }
                    index++;
                    if(index >= array.length){
                        break;
                    }
                    entry = (Entry) array[index];
                }
                return false;
            }

            @Override
            public T next() {
                T car = entry.car;
                entry = entry.next;
                return car;
            }
        };
    }

    private class Entry { // ���������� ������� ������
        private final T car;
        private Entry next;

        public Entry(T car, Entry next){
            this.car = car;
            this.next = next;
        }
    }
}
