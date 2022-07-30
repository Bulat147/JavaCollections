/** ������ ���������������� (TDD):
 *      ���������: � SOLID-��������� ���� - ��������������� �� ������ ����������, � �� ����������
 *      ����: 1) ����� ���������
 *            2) ��� ��������� ����� �����
 *            3) ��� ����� ����� �����, ����������� ���������
 * */

import java.util.List;

public interface CarList{
    // ���������� ������ ���������� �� ��������� � ����� �������� ������������� ������� - ��� ������ public
    // � ��� ������� ������ - ������ private
    void add(Car car);
    void add(Car car, int index);
    Car get(int index);
    boolean remove(Car car);
    boolean removeAt(int index);
    int size();
    void clear();
}
