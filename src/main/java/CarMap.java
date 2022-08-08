import java.util.List;
import java.util.Set;

public interface CarMap<K, V> {

    V get(K key);
    boolean remove(K key);
    void put(K key, V value);
    Set<K> keySet(); // Set ������ ��� ��������� - ��� ���������� �������
    List<V> getValues();  // List ������ ��� � ����� ������ ����� ���� ��������� ����������
    int size();
    void clear();
}
