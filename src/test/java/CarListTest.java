import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarListTest {
    // ����� �������� ����� - ����� �� ��������� ������, �� ��� ���� ��� � ��� �������� �
    // ������ - ����� ��� �������������. � ����� �� ��� ����� ���������� � ��� �������.
    private CarList carList;

    @BeforeEach
    void setUp() {
        carList = new CarArrayList();
        for (int i=0; i<100; i++){
            // ���, ��������, ��� ����� ���� �������� ��� �� ������������� carList
            carList.add(new Car("Model"+Integer.toString(i), i));
        }
    }

    @AfterEach
    void tearDown() {
        carList.clear();
    }

    @Test
    void whenAdd100ElementsSizeIs100() {
        assertEquals(100, carList.size());
    }

    @Test
    void whenGetByIndexReturnTrueCar() {
        Car tempCar = carList.get(2);
        assertEquals(tempCar.getModel(), "Model2");
    }

    @Test
    void whenRemoveCarThenSizeMustBeDecreased() {
        Car car = new Car("Porshe", 111);
        carList.add(car);
        assertEquals(carList.size(), 101);
        boolean check = carList.remove(car);
        assertTrue(check); // ��������� ������� �� ���������
        assertEquals(carList.size(), 100);
    }

    @Test
    void WhenRemoveByIndexThenSizeMustBeDecreased() {
        assertTrue(carList.removeAt(3)); // ��������� ������� �� ���������
        assertEquals(carList.size(), 99);
    }

    @Test
    void whenIndexOutOfBoundsThenThrowException(){
        // assertThrows ���������� ������ ���������� � ��� ����� ��������� ����������
        ArrayIndexOutOfBoundsException thrown = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            carList.get(111); // �� ���� ������ ������ �������� ����������
        });

        ArrayIndexOutOfBoundsException thrown2 = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            carList.removeAt(112);
        });
    }

    @Test
    void whenListClearedThenSizeMustBe0() {
        carList.clear();
        assertEquals(0, carList.size());
    }

    @Test
    void whenRemovedNonExistentCarThenReturnFalse(){
        Car car = new Car("Toyota", 189);
        assertFalse(carList.remove(car));
        assertEquals(100, carList.size()); // ��������� �� ��, ��� �������� �� �� �� ���������
    }

}