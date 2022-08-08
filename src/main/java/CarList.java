/** ������ ���������������� (TDD):
 *      ���������: � SOLID-��������� ���� - ��������������� �� ������ ����������, � �� ����������
 *      ����: 1) ����� ���������
 *            2) ��� ��������� ����� �����
 *            3) ��� ����� ����� �����, ����������� ���������
 * */

import java.util.List;

public interface CarList<T> extends CarCollection<T>{
    // ���������� ������ ���������� �� ��������� � ����� �������� ������������� ������� - ��� ������ public
    // � ��� ������� ������ - ������ private
    boolean add(T car);
    boolean add(T car, int index);
    T get(int index);
    boolean remove(T car);
    boolean removeAt(int index);
    int size();
    void clear();
}
