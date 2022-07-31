

public class CarLinkedList implements CarList{
    // ��������� ����� user �� ��� �� ������ �� �������� ������ �������
    private Node head = null;
    private Node tail = null;
    private int size = 0;

    @Override
    public void add(Car car) {
        // ����� ���������� � �����
        Node lastTail = tail;
        tail = new Node(lastTail, car, null);
        if (size == 0){
            head = tail;
        }else{
            lastTail.next = tail;
        }
        size++;
    }

    @Override
    public void add(Car car, int index) {
        if (index == size){
            add(car);
            return;
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
    }

    @Override
    public Car get(int index) {
        // ���������� �� getNode ���, ��� ���������� ��������, � �� ����
        return getNode(index).value;
    }

    @Override
    public boolean remove(Car car) {
        Node temp = head;
        for (int i=0; i<size;i++){
            if (temp.value.equals(car)){ // ����� �� ��������� ����
                return removeAt(i); // �.� removeAt() ���������� boolean
            }
            temp = temp.next;
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

    // ��������� ����� �����, �.�. ������� ������ ������� �� �����, ��� ��� �����
    private static class Node{
        public Node previous;
        public Car value;
        public Node next;

        public Node(Node previous, Car value, Node next){
            this.previous = previous;
            this.value = value;
            this.next = next;
        }
    }

}
