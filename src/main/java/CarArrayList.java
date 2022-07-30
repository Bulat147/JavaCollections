/** Плюсы Arraylist:
 *      Получение элемента по индексу,
 *      добавление в конец - O(1)
 *  Минусы ArrayList:
 *      Вставка в центр, удаление эл-та - O(n)
 *      Утечка памяти, если в нем нет реализации уменьшения capacity
 * */

import java.util.Arrays;

public class CarArrayList implements CarList{
    private Car[] array = new Car[10]; // capacity = 10
    private int size = 0;

    @Override
    public void add(Car car) { // O(1), но если придется увеличивать массив - O(n)
        increaseArray();
        array[size] = car;
        size++;
    }

    public void add(Car car, int index){ // O(n)
        if(index<0  || index>size){
            throw new ArrayIndexOutOfBoundsException();
        }
        increaseArray();
        // Это удобный сдвиг эл-ов массива вправо
        System.arraycopy(array, index, array, index + 1, size - index);
        // Меняет сам объект
        array[index] = car;
        size++;
    }

    @Override
    public Car get(int index) { // O(1)
        checkIndex(index);
        return array[index];
    }

    @Override
    public boolean remove(Car car) { // O(n)
        for (int i=0; i<size; i++){
            if (array[i].equals(car)){ // Все ссылочные типы нужно сравнивать так
                return removeAt(i);
            }
        }
        return false;
    }

    @Override
    public boolean removeAt(int index) { // O(n)
        checkIndex(index);
        // Сдвиг влево массива
        System.arraycopy(array, index+1, array, index, size-index-1);
        array[size-1] = null;
        size--;
        return true; // Здесь возвращается всегда true, т.к. при false-ситуации поднимается Exception
    }

    @Override
    public int size() { // O(1)
        return size;
    }

    @Override
    public void clear() { // O(1)
        array = new Car[10];
        size = 0;
    }

    private void checkIndex(int index) throws ArrayIndexOutOfBoundsException{
        if (index<0 || index >= size){
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private void increaseArray(){
        /** Метод для увеличения массива */
        if (size >= array.length){
            array = Arrays.copyOf(array, array.length*2);
        }
    }
}
