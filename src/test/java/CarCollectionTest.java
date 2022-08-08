import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarCollectionTest {

    private CarCollection<Car> cars;

    @BeforeEach
    void setUp(){
        cars = new CarHashSet<>();
        for (int i=0; i<100; i++){
            // Вот, например, эта часть была написана ещё до инициализации carList
            cars.add(new Car("Model"+i, i));
        }
    }

    @Test
    void whenAdd100ElementsSizeIs100() {
        assertEquals(100, cars.size());
    }

    @Test
    void whenRemoveCarThenSizeMustBeDecreased() {
        Car car = new Car("Porshe", 111);
        cars.add(car);
        assertEquals(cars.size(), 101);
        boolean check = cars.remove(car);
        assertTrue(check); // проверяем успешно ли удалилось
        assertEquals(cars.size(), 100);
    }

    @Test
    void whenListClearedThenSizeMustBe0() {
        cars.clear();
        assertEquals(0, cars.size());
    }

    @Test
    void whenAddNewCarThenContainsItIsTrue(){
        Car car = new Car("Brand", 145);
        assertTrue(cars.add(car)); // в этой строчке carList меняется!
        assertTrue(cars.contains(car));
    }

    @Test
    void whenCheckNotExistedCarThenContainsIsFalse(){
        Car car = new Car("Brooookliiin", 14);
        assertFalse(cars.contains(car));
    }

    @Test
    void whenRemovedNonExistentCarThenReturnFalse(){
        Car car = new Car("Toyota", 189);
        assertFalse(cars.remove(car));
        assertEquals(100, cars.size()); // Проверяем на то, что нечаянно не то не удалилось
    }

    @Test
    void testForeach(){
        int index = 0;
        for (Car car: cars){
            assertTrue(cars.contains(car));
            index++;
        }
        assertEquals(100, index);
    }


}