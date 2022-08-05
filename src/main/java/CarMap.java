import java.util.List;
import java.util.Set;

public interface CarMap {

    Car get(CarOwner key);
    boolean remove(CarOwner key);
    void put(CarOwner key, Car value);
    Set<CarOwner> keySet(); // Set ������ ��� ��������� - ��� ���������� �������
    List<Car> getValues();  // List ������ ��� � ����� ������ ����� ���� ��������� ����������
    int size();
    void clear();
}
