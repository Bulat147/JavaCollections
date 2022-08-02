import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarSetTest {
    private CarSet carSet;

    @BeforeEach
    void setUp() {
        carSet = new CarHashSet();
        Car temp;
        for (int i=0; i<100; i++){
            temp = new Car("Model"+Integer.toString(i), i);
            carSet.add(temp);
        }
    }

    @Test
    void whenAdd100DifferentCarsThenSizeIs100() {
        assertEquals(100, carSet.size());
    }

    @Test
    void whenAddNewCarThenSizeIncrease() {
        // true - size ++ а если false - size==size
        Car car1 = new Car("BMW", 123);
        int sizeBeforeAdd = carSet.size();
        boolean successfulAdd = carSet.add(car1);
        assertTrue(successfulAdd);
        assertEquals(sizeBeforeAdd+1, carSet.size());
    }

    @Test
    void whenAddExistCarThenSizeDontIncrease() {
        Car car1 = new Car("Model1", 1);
        int sizeBeforeAdd = carSet.size();
        boolean unsuccessfulAdd = carSet.add(car1);
        assertFalse(unsuccessfulAdd);
        assertEquals(sizeBeforeAdd, carSet.size());
    }

    @Test
    void whenSuccessfulRemoveCarThenSizeDecrease() {
        Car newCar = new Car("Toyota", 777);
        boolean successfulAdd = carSet.add(newCar);
        assertTrue(successfulAdd);
        int newSize = carSet.size();
        boolean successfulRemove = carSet.remove(newCar);
        assertTrue(successfulRemove);
        assertEquals(newSize-1, carSet.size());
    }

    @Test
    void whenRemoveExistCarThenSizeDontDecrease() {
        Car newCar = new Car("Toyota", 777); // Мы не добавляем этот элемент
        int currentSize = carSet.size();
        boolean unsuccessfulRemove = carSet.remove(newCar);
        assertFalse(unsuccessfulRemove);
        assertEquals(currentSize, carSet.size());
    }

    @Test
    void whenClearThenSizeIs0() {
        carSet.clear();
        assertEquals(0, carSet.size());
    }

    @Test
    void whenAddNewCarThenContainsItIsTrue(){
        Car car = new Car("Brand", 145);
        assertTrue(carSet.add(car)); // в этой строчке carList меняется!
        assertTrue(carSet.contains(car));
    }

    @Test
    void whenCheckNotExistedCarThenContainsIsFalse(){
        Car car = new Car("Brooookliiin", 14);
        assertFalse(carSet.contains(car));
    }
}