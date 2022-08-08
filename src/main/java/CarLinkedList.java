import java.util.Iterator;

public class CarLinkedList<T> implements CarList<T>, CarQueue<T>{
    // ��������� ����� user �� ��� �� ������ �� �������� ������ �������
    private Node head = null;
    private Node tail = null;
    private int size = 0;

    @Override
    public boolean add(T car) {
        // ����� ���������� � �����
        Node lastTail = tail;
        tail = new Node(lastTail, car, null);
        if (size == 0){
            head = tail;
        }else{
            lastTail.next = tail;
        }
        size++;
        return true;
    }

    @Override
    public T pick() {
        // ��������� ��������: ���� ������ ����? �� - ����� get(0) : ��� - ����� null
        return size>0 ? get(0) : null;
    }

    @Override
    public T poll() {
        if (size > 0){
            T car = pick();
            removeAt(0);
            return car;
        }
        return null;
    }

    @Override
    public boolean add(T car, int index) {
        if (index == size){
            return add(car);
        }
        Node nextNode = getNode(index); // ������ ������
        Node lastNode = nextNode.previous;
        Node newNode = new Node(lastNode, car, nextNode);
        nextNode.previous = newNode;
        if (lastNode != null){ // ���� �� ������ ���� ����� ���-��, �� ��� ������ next
            lastNode.next = newNode;
        }else{    // ���� �� �����, �� ������ �� ����� ����
            head = newNode;
        }
        size++;
        return true;
    }

    @Override
    public T get(int index) {
        // ���������� �� getNode ���, ��� ���������� ��������, � �� ����
        return getNode(index).value;
    }

    @Override
    public boolean contains(T car) {
        return findCar(car) != -1;
    }

    @Override
    public boolean remove(T car) {
        int index = findCar(car);
        if (index != -1){
            removeAt(index);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAt(int index) {
        Node removing = getNode(index);
        Node right = removing.next; // ��������� �������, ����� �� ����� � ��� �� ���, �������� ����� ���� null
        Node left = removing.previous;

        // ���������� ����, ���� �� �� ������, ������� �� ���������
        if(left != null){
            left.next = right;
        }else{ // ���� �� ������, �� head ��������� �� ��������� ����
            head = right;
        }
        // ��������� ����, ���� �� ������, ������� �� ����������
        if (right != null){
            right.previous = left;
        }else{ // ���� �� ������, �� ����� tail ���������� ���������� ����
            tail = left;
        }
        size--;
        return true;
        // ������ ����� ������ ������ �� ����� ������� �� removing ����� �� ��������� => ��� ��������� ��������� ������
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    private int findCar(T car){
        Node node = head;
        for (int i=0; i<size;i++){
            if (node.value == car){
                return i;
            }
            node = node.next;
        }
        return -1;
    }

    private Node getNode(int index){ // � ��� �� �������� �� ���������� index
        if (index<0 || index>=size){
            throw new IndexOutOfBoundsException();
        }
        Node temp = head;
        for (int i=0; i<index;i++){
            temp = temp.next;
        }
        return temp;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node node = head;
            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                T car = node.value;
                node = node.next;
                return car;
            }
        };
    }

    // ��������� ����� �����, �.�. ������� ������ ������� �� �����, ��� ��� �����
    // ���� 2 ��������, ����� ������ T � Node - ���� ��������������� ���, ���� ���������� �� static, ����� Node ����� ���� �������
    private class Node{
        public Node previous;
        public T value;
        public Node next;

        public Node(Node previous, T value, Node next){
            this.previous = previous;
            this.value = value;
            this.next = next;
        }
    }

}
