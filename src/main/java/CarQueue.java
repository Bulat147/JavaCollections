/** Queue - FIFO. ����������� �� Collection.
 * ����������� ����� LinkedList. �� ���� LinkedList ��������� 2 ���������� - Queue � List.
 * */
public interface CarQueue extends CarCollection{
    boolean add(Car car);
    Car pick(); // ���������� ������ � ������� ��-�� ��� ��������
    Car poll(); // ���������� ��� � ���������
}
