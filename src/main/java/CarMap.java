import java.util.List;
import java.util.Set;

public interface CarMap {

    Car get(CarOwner key);
    boolean remove(CarOwner key);
    void put(CarOwner key, Car value);
    Set<CarOwner> keySet(); // Set потому что владельцы - это уникальные объекты
    List<Car> getValues();  // List потому что у одной машины могут быть несколько владельцев
    int size();
    void clear();
}
