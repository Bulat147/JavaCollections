/** Queue - FIFO. Наследуется от Collection.
 * Реализуется через LinkedList. То есть LinkedList реализует 2 интерфейса - Queue и List.
 * */
public interface CarQueue extends CarCollection{
    boolean add(Car car);
    Car pick(); // Возвращает первый в очереди эл-нт без удаления
    Car poll(); // Возвращает его с удалением
}
