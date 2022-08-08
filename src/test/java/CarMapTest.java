import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CarMapTest {
    CarMap<CarOwner, Car> carMap;

    @BeforeEach
    void setUp(){
        carMap = new CarHashMap<>();
        for (int i=0; i<50; i++){
            Car car = new Car("Model"+i, i);
            CarOwner owner = new CarOwner("Owner"+i, i);
            carMap.put(owner, car);
        }
    }

    @Test
    void whenPut50DifferentKeysThenSize50() {
        assertEquals(50, carMap.size());
    }

    @Test
    void whenPut55ValuesWith52KeysThenSize52(){
        for (int i=0; i<5; i++){
            int j = i%2;
            CarOwner carOwner = new CarOwner("Name"+j, j);
            Car car = new Car("Model"+i, i);
            carMap.put(carOwner, car);
        }
        assertEquals(52, carMap.size());
    }

    @Test
    void whenRemoveThenSizeDecrease() {
        CarOwner carOwner = new CarOwner("Owner2", 2);
        boolean removed = carMap.remove(carOwner);
        assertTrue(removed);
        assertEquals(49, carMap.size());
    }

    @Test
    void whenRemoveThenSizeDecreaseOnlyOnce(){
        CarOwner carOwner = new CarOwner("Owner"+3, 3);
        assertTrue(carMap.remove(carOwner));
        assertEquals(49, carMap.size());
        assertFalse(carMap.remove(carOwner));
        assertEquals(49, carMap.size());
    }

    @Test
    void whenGetThenReturnTrueValue() {
        Car car = new Car("ModelXXX", 999);
        CarOwner carOwner = new CarOwner("Name1", 999);
        carMap.put(carOwner, car);
        assertEquals(car, carMap.get(carOwner));
    }

    @Test
    void countOfKeysMustBeEqualsToCountOfValues() {
        Set<CarOwner> keys = carMap.keySet();
        assertEquals(50, keys.size());
        List<Car> cars = carMap.getValues();
        assertEquals(50, cars.size());
    }

    @Test
    void whenClearThenSize0() {
        carMap.clear();
        assertEquals(0, carMap.size());
    }
}