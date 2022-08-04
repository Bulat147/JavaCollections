/** Про работу памяти в Java:
 *      1) В ОЗУ выделен 1 мб на стек, в этом стеке хранятся примитивные типы с их переменными
 *      и хранятся переменные с адресами на объекты в куче(RAM, ОЗУ).
 *      2) А остальную часть ОЗУ занимает куча - в ней уже хранятся сами объекты.
 *      3) Когда мы делаем ==, то сравниваем переменные именно из стека, поэтому сравниваются адреса.
 *
 *  Правила для hashcode и equals:
 *      1) Если переопределил equals - переопредели и hashcode
 *      2) Если hashcode двух объектов разный - объекты тоже разные
 *      3) Если hashcode объектов одинаковый - НЕ ФАКТ, что объекты одни и те же, это может быть коллизия
 * */

import java.util.Objects;

public class Car implements Comparable<Car>{ // Generics в Comparable - это то с чем можно данный класс сравнивать
    private final String model;
    private final int number;

    public static void main(String[] args) {
        Car car1 = new Car("TTC", 123);
        Car car2 = new Car("TTC", 123);
        System.out.println(car1 == car2); // Сравнение адресов в стеке
        System.out.println(car1.equals(car2)); // Сравнение объектов в куче
        System.out.println(car1.hashCode()+" "+car2.hashCode()); // вывод хэшкодов
    }

    public Car(String model, int number){
        this.model = model;
        this.number = number;
    }

    public String getModel(){
        return this.model;
    }

    public int getNum(){
        return this.number;
    }

    @Override
    public boolean equals(Object obj) {
        // Проверяем, что наш obj действительно car
        // Эта хитрость - downCasting в if - создаем объект car класса Car из объекта obj, если true
        if (obj instanceof Car car){
            // Делаем DownCasting, чтобы у obj работать с методами Car
            return this.model.equals(car.model) && this.number == car.number;
        }else{
            return false;
        }
    }
    // Но правило гласит: переопределил equals - переопредели hashcode
    /** Хэшкод объекта особенно важен для корректной работы HashSet, ведь тогда объекты с одинаковыми полями (но разными
     * хэшами -> разными позициями) оба будут лежать в коллекции, хотя не должны */
    @Override
    public int hashCode() {
        // Этот метод создает хэш, основываясь на полях объекта
        return Objects.hash(model, number);
    }

    /** Этот метод нужно реализовывать, чтобы работал TreeSet, чтоб там можно было объекты сравнивать */
    @Override
    public int compareTo(Car o) {
        return this.getModel().compareTo(o.getModel()); // вернет 0 - равны, -1 - this меньше, 1+ - this больше
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", number=" + number +
                '}';
    }
}
