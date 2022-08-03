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
    void whenGetByIndexReturnTrueCar() {
        Car tempCar = carList.get(2);
        assertEquals(tempCar.getModel(), "Model2");
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
    void whenAddInMiddleThenGetTrueValueByTheIndex(){
        Car car = new Car("BMW190", 190);
        // ������� � �����
        carList.add(car, 50);
        assertEquals(101, carList.size());
        Car inputCar = carList.get(50);
        assertEquals("BMW190", inputCar.getModel());
    }

    @Test
    void whenAddInHeadThenGetTrueValueByTheIndex() {
        Car car = new Car("BMW190", 190);
        // ������� � ������
        carList.add(car, 0);
        assertEquals(101, carList.size());
        Car inputCar = carList.get(0);
        assertEquals("BMW190", inputCar.getModel());
    }

    @Test
    void whenAddInTailThenGetTrueValueByTheIndex() {
        Car car = new Car("BMW190", 190);
        // ������� � ������
        carList.add(car, carList.size());
        assertEquals(101, carList.size());
        Car inputCar = carList.get(100);
        assertEquals("BMW190", inputCar.getModel());
    }
}