/** Queue - FIFO. ����������� �� Collection.
 * ����������� ����� LinkedList. �� ���� LinkedList ��������� 2 ���������� - Queue � List.
 * */
public interface CarQueue<T> extends CarCollection<T>{
    boolean add(T car);
    T pick(); // ���������� ������ � ������� ��-�� ��� ��������
    T poll(); // ���������� ��� � ���������
}
