import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarQueueTest {

    private CarQueue cars;

    @BeforeEach
    void setUp() {
        cars = new CarLinkedList();
        for (int i=0; i<10; i++){
            cars.add(new Car("Brand"+i, i));
        }
    }

    @Test
    void add() {
        assertEquals(10, cars.size());
    }

    @Test
    void pick() {
        Car car = cars.pick();
        assertEquals(new Car("Brand0", 0), car);
        assertEquals(10, cars.size());
    }

    @Test
    void poll() {
        Car car = cars.poll();
        assertEquals(new Car("Brand0", 0), car);
        assertEquals(9, cars.size());
    }
}