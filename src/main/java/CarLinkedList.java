import java.util.Iterator;

public class CarLinkedList<T> implements CarList<T>, CarQueue<T>{
    // приватные чтобы user не мог их менять не применяя нужных методов
    private Node head = null;
    private Node tail = null;
    private int size = 0;

    @Override
    public boolean add(T car) {
        // Метод добавления в конец
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
        // Тернарный оператор: сайз больше нуля? Да - верни get(0) : Нет - верни null
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
        return true;
    }

    @Override
    public T get(int index) {
        // Отличается от getNode тем, что возвращает значение, а не узел
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

    // Вложенный класс узлов, т.к. связный список СОСТОИТ из узлов, это его части
    // Есть 2 варианта, чтобы внести T в Node - либо параметризовать его, либо избавиться от static, чтобы Node видел поля объекта
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
