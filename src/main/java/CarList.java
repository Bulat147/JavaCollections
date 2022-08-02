/** Способ программирования (TDD):
 *      идеология: в SOLID-принципах есть - программировать на уровне абстракций, а не реализаций
 *      шаги: 1) Пишем интерфейс
 *            2) Под интерфейс пишем тесты
 *            3) Под тесты пишем класс, реализующий интерфейс
 * */

import java.util.List;

public interface CarList extends CarCollection{
    // бестельные методы интерфейса не нуждаются в явном указании модификаторов доступа - они всегда public
    // а вот тельные методы - всегда private
    boolean add(Car car);
    boolean add(Car car, int index);
    Car get(int index);
    boolean remove(Car car);
    boolean removeAt(int index);
    int size();
    void clear();
}
