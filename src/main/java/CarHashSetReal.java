import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;

/** �� �� ����� ���� HashSet ����������� ��� ������� � ������� HashMap, ������� � ������
 *  ����� ������� �������� - � ������ ����� �� LnkedList, � TreeMap */

public class CarHashSetReal implements CarSet{

    private final HashMap<Car, Object> cars = new HashMap<>();
    private final Object obj = new Object();

    @Override
    public boolean add(Car car) {
       Object added = cars.put(car, obj);
       return added != null;
    }

    @Override
    public boolean remove(Car car) {
        Object removed = cars.remove(car);
        return removed != null;
    }

    @Override
    public boolean contains(Car car) {
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
    public Iterator<Car> iterator() {
        return cars.keySet().iterator(); // ��� �� ������ ����������� �� Set ������ � HashMap
    }
}
