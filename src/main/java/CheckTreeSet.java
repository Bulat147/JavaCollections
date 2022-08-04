import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/** �������� HashSet � TreeSet:
 *      1) �� ������ ���������� ��������
 *      2) ��������� ��������� Set
 *  ��������:
 *      1) TreeSet ������ �������� �������������� (�� �� ������������)
 *      2) HashSet - ������ ������� ����� �������, � TreeSet - ������� �������,
 *      ����������� ��������� Comparable
 *      3) add, remove, contains: HashSet - O(1), TreeSet - O(log n) */

public class CheckTreeSet {

    public static void main(String[] args) {
        /** � TreeSet ��� �������� �� ��������� - log(n) */
        Set<Car> carTreeSet = new TreeSet<>(); // �������� ���� ��������� ������ ������������� Comparable ��� ...
        for (int i = 0; i < 10; i++) {
            carTreeSet.add(new Car("Brand" + i, i));
        }
        // ������� ����������� ����� for each - ��������
        for (Car car : carTreeSet) {
            System.out.println(car);
        }

        // � ��� ������ ������ ���������� �������� �������� Car - �������� � TreeSet Comparator-������
        MyComparator newComparator = new MyComparator();
        // ������ TreeSet ����� ���������� ������� � ������� �������� Comparator-�
        Set<Car> carTreeSet2 = new TreeSet<>(newComparator);
        for (int i = 0; i < 10; i++) {
            carTreeSet2.add(new Car("Model" + i, i));
        }
        for (Car car : carTreeSet2){
            System.out.println(car);
        }
    }
}

/** � ��� ����� ��������-�������������, ������� ����� ���������� ����� ����� ��� Car */
class MyComparator implements Comparator<Car>{

    @Override
    public int compare(Car o1, Car o2) {
        // ����� ��������� �������� �� ������ ������ � ������� �������
        if (o1.getNum() < o2.getNum()){
            return 9;
        } else if(o1.getNum() > o2.getNum()){
            return -9;
        } else{
            return 0;
        }
        // � ����� ���� ������ ������� -o2.compareTo() - � ��� ����� �� ��������
    }
}
