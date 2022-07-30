/** ����� Arraylist:
 *      ��������� �������� �� �������,
 *      ���������� � ����� - O(1)
 *  ������ ArrayList:
 *      ������� � �����, �������� ��-�� - O(n)
 *      ������ ������, ���� � ��� ��� ���������� ���������� capacity
 * */

import java.util.Arrays;

public class CarArrayList implements CarList{
    private Car[] array = new Car[10]; // capacity = 10
    private int size = 0;

    @Override
    public void add(Car car) { // O(1), �� ���� �������� ����������� ������ - O(n)
        increaseArray();
        array[size] = car;
        size++;
    }

    public void add(Car car, int index){ // O(n)
        if(index<0  || index>size){
            throw new ArrayIndexOutOfBoundsException();
        }
        increaseArray();
        // ��� ������� ����� ��-�� ������� ������
        System.arraycopy(array, index, array, index + 1, size - index);
        // ������ ��� ������
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
        array = new Car[10];
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
}
