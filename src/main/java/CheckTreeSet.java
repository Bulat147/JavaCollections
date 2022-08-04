import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/** Схожости HashSet и TreeSet:
 *      1) Не хранят одинаковые элементы
 *      2) Реализуют интерфейс Set
 *  Различия:
 *      1) TreeSet хранит элементы отсортированно (но не индексируемо)
 *      2) HashSet - хранит объекты любых классов, а TreeSet - объекты классов,
 *      реализующих интерфейс Comparable
 *      3) add, remove, contains: HashSet - O(1), TreeSet - O(log n) */

public class CheckTreeSet {

    public static void main(String[] args) {
        /** У TreeSet все операции по сложности - log(n) */
        Set<Car> carTreeSet = new TreeSet<>(); // Элементы этой коллекции должны реализовывать Comparable или ...
        for (int i = 0; i < 10; i++) {
            carTreeSet.add(new Car("Brand" + i, i));
        }
        // Пробуем пробежаться через for each - работает
        for (Car car : carTreeSet) {
            System.out.println(car);
        }

        // А это второй способ реализации сранения объектов Car - засунуть в TreeSet Comparator-объект
        MyComparator newComparator = new MyComparator();
        // Теперь TreeSet будет сравнивать объекты с помощью внешнего Comparator-а
        Set<Car> carTreeSet2 = new TreeSet<>(newComparator);
        for (int i = 0; i < 10; i++) {
            carTreeSet2.add(new Car("Model" + i, i));
        }
        for (Car car : carTreeSet2){
            System.out.println(car);
        }
    }
}

/** А это класс объектов-сравнивателей, которые могут сравнивать между собой два Car */
class MyComparator implements Comparator<Car>{

    @Override
    public int compare(Car o1, Car o2) {
        // Здесь попробуем сравнить по номеру машину в порядке убыания
        if (o1.getNum() < o2.getNum()){
            return 9;
        } else if(o1.getNum() > o2.getNum()){
            return -9;
        } else{
            return 0;
        }
        // А можно было просто вызвать -o2.compareTo() - и это будет по убыванию
    }
}
