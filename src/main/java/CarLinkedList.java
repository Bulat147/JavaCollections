

public class CarLinkedList implements CarList{
    // приватные чтобы user не мог их менять не применяя нужных методов
    private Node head = null;
    private Node tail = null;
    private int size = 0;

    @Override
    public void add(Car car) {
        // Метод добавления в конец
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
        Node nextNode = getNode(index); // уходит вправо
        Node lastNode = nextNode.previous;
        Node newNode = new Node(lastNode, car, nextNode);
        nextNode.previous = newNode;
        if (lastNode != null){ // Если до нового узла стоит что-то, то оно меняет next
            lastNode.next = newNode;
        }else{    // если не стоит, то голова на новом узле
            head = newNode;
        }
        size++;
    }

    @Override
    public Car get(int index) {
        // Отличается от getNode тем, что возвращает значение, а не узел
        return getNode(index).value;
    }

    @Override
    public boolean remove(Car car) {
        Node temp = head;
        for (int i=0; i<size;i++){
            if (temp.value.equals(car)){ // равны ли ссылочные типы
                return removeAt(i); // т.к removeAt() возвращает boolean
            }
            temp = temp.next;
        }
        return false;
    }

    @Override
    public boolean removeAt(int index) {
        Node removing = getNode(index);
        Node right = removing.next; // Ссылочные объекты, какой бы класс у них не был, спокойно могут быть null
        Node left = removing.previous;

        // Предыдущий узел, если он не пустой, ссылаем на следующий
        if(left != null){
            left.next = right;
        }else{ // Если же пустой, то head переходит на следующий узел
            head = right;
        }
        // Следующий узел, если не пустой, ссылаем на предыдущий
        if (right != null){
            right.previous = left;
        }else{ // Если же пустой, то хвост tail становится предыдущий узел
            tail = left;
        }
        size--;
        return true;
        // Теперь после снятия метода со стека вызовов на removing никто не ссылается => она удаляется сборщиком мусора
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

    private Node getNode(int index){ // В нем же проверка на адекватный index
        if (index<0 || index>=size){
            throw new IndexOutOfBoundsException();
        }
        Node temp = head;
        for (int i=0; i<index;i++){
            temp = temp.next;
        }
        return temp;
    }

    // Вложенный класс узлов, т.к. связный список СОСТОИТ из узлов, это его части
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
