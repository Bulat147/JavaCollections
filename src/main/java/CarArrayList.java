/** Плюсы Arraylist:
 *      Получение элемента по индексу,
 *      добавление в конец - O(1)
 *  Минусы ArrayList:
 *      Вставка в центр, удаление эл-та - O(n)
 *      Утечка памяти, если в нем нет реализации уменьшения capacity
 * */

import java.util.Arrays;
import java.util.Iterator;

public class CarArrayList<T> implements CarList<T>{
    /** С обобщенными типами нельзя следующее:
     *      1) Создавать объект через new T(), тк неизвестно, есть ли у этого типа конструктор
     *      2) Создавать массивы типа T[10]. И вместо этого создают массивы типа Object */
    private Object[] array = new Object[10];
    private int size = 0;

    @Override
    public boolean add(T car) { // O(1), но если придется увеличивать массив - O(n)
        increaseArray();
        array[size] = car;
        size++;
        return true;
    }

    public boolean add(T car, int index){ // O(n)
        if(index<0  || index>size){
            throw new ArrayIndexOutOfBoundsException();
        }
        increaseArray();
        // Это удобный сдвиг эл-ов массива вправо
        System.arraycopy(array, index, array, index + 1, size - index);
        // Меняет сам объект
        array[index] = car;
        size++;
        return true;
    }

    @Override
    public T get(int index) { // O(1)
        checkIndex(index);
        /** Здесь для return необходим downCasting из типа Object в тип T */
        // Здесь подсвечивает из-за ситуации, что в массиве может быть элемент не типа T, но это невозможно
        // т.к. add 100% принимает на вход только тип T, а IDE это не просчитывает
        return (T) array[index];
    }

    @Override
    public boolean contains(T car) {
        for (Object temp: array){
            // Здесь тоже нужен downCasting тк equals может не сработать из-за различия реализации
            if (car.equals((T) temp)){ // Ну не забывай про equals со ссылочными типами!!!
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean remove(T car) { // O(n)
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
        array = new Object[10];
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

    @Override
    public Iterator<T> iterator() {
        // возвращаем объект анонимного класса, реализующего интерфейс Iterator
        return new Iterator<T>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                return (T) array[index++];
            }
        };
    }
}
