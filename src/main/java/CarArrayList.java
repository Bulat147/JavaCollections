/** ����� Arraylist:
 *      ��������� �������� �� �������,
 *      ���������� � ����� - O(1)
 *  ������ ArrayList:
 *      ������� � �����, �������� ��-�� - O(n)
 *      ������ ������, ���� � ��� ��� ���������� ���������� capacity
 * */

import java.util.Arrays;
import java.util.Iterator;

public class CarArrayList<T> implements CarList<T>{
    /** � ����������� ������ ������ ���������:
     *      1) ��������� ������ ����� new T(), �� ����������, ���� �� � ����� ���� �����������
     *      2) ��������� ������� ���� T[10]. � ������ ����� ������� ������� ���� Object */
    private Object[] array = new Object[10];
    private int size = 0;

    @Override
    public boolean add(T car) { // O(1), �� ���� �������� ����������� ������ - O(n)
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
        // ��� ������� ����� ��-�� ������� ������
        System.arraycopy(array, index, array, index + 1, size - index);
        // ������ ��� ������
        array[index] = car;
        size++;
        return true;
    }

    @Override
    public T get(int index) { // O(1)
        checkIndex(index);
        /** ����� ��� return ��������� downCasting �� ���� Object � ��� T */
        // ����� ������������ ��-�� ��������, ��� � ������� ����� ���� ������� �� ���� T, �� ��� ����������
        // �.�. add 100% ��������� �� ���� ������ ��� T, � IDE ��� �� ������������
        return (T) array[index];
    }

    @Override
    public boolean contains(T car) {
        for (Object temp: array){
            // ����� ���� ����� downCasting �� equals ����� �� ��������� ��-�� �������� ����������
            if (car.equals((T) temp)){ // �� �� ������� ��� equals �� ���������� ������!!!
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean remove(T car) { // O(n)
        for (int i=0; i<size; i++){
            if (array[i].equals(car)){ // ��� ��������� ���� ����� ���������� ���
                return removeAt(i);
            }
        }
        return false;
    }

    @Override
    public boolean removeAt(int index) { // O(n)
        checkIndex(index);
        // ����� ����� �������
        System.arraycopy(array, index+1, array, index, size-index-1);
        array[size-1] = null;
        size--;
        return true; // ����� ������������ ������ true, �.�. ��� false-�������� ����������� Exception
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
        /** ����� ��� ���������� ������� */
        if (size >= array.length){
            array = Arrays.copyOf(array, array.length*2);
        }
    }

    @Override
    public Iterator<T> iterator() {
        // ���������� ������ ���������� ������, ������������ ��������� Iterator
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
