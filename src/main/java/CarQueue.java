/** Queue - FIFO. Наследуется от Collection.
 * Реализуется через LinkedList. То есть LinkedList реализует 2 интерфейса - Queue и List.
 * */
public interface CarQueue<T> extends CarCollection<T>{
    boolean add(T car);
    T pick(); // Возвращает первый в очереди эл-нт без удаления
    T poll(); // Возвращает его с удалением
}
