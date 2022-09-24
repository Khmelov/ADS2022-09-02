package by.it.group151003.matoshko.lesson02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
/*
даны интервальные события events
реализуйте метод calcStartTimes, так, чтобы число принятых к выполнению
непересекающихся событий было максимально.
Алгоритм жадный. Для реализации обдумайте надежный шаг.
*/

public class B_Sheduler {
    //событие у аудитории(два поля: начало и конец)
    static class Event {
        int start;
        int stop;

        Event(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public String toString() {
            return "("+ start +":" + stop + ")";
        }
    }

    public static void main(String[] args) {
        B_Sheduler instance = new B_Sheduler();
        Event[] events = {  new Event(0, 3),  new Event(0, 1), new Event(1, 2), new Event(3, 5),
                new Event(1, 3),  new Event(1, 3), new Event(1, 3), new Event(3, 6),
                new Event(2, 7),  new Event(2, 3), new Event(2, 7), new Event(7, 9),
                new Event(3, 5),  new Event(2, 4), new Event(2, 3), new Event(3, 7),
                new Event(4, 5),  new Event(6, 7), new Event(6, 9), new Event(7, 9),
                new Event(8, 9),  new Event(4, 6), new Event(8, 10), new Event(7, 10)
        };

        List<Event> starts = instance.calcStartTimes(events,0,10);  //рассчитаем оптимальное заполнение аудитории
        System.out.println(starts);                                 //покажем рассчитанный график занятий
    }

    List<Event> calcStartTimes(Event[] events, int from, int to) {
        List<Event> result;
        result = new ArrayList<>();
        // Описываем компаратор, который будет сравнивать объекты не целиком,
        // как будто уних олно поле, а по stop (obj -> obj.stop)
        Comparator<Event> comparator = Comparator.comparing(obj -> obj.stop);
        Arrays.sort(events,comparator);
        // Будем начинать искать события с элемента с индексом 1
        int i=1;
        // Сохраняем текущую правую границу последнего события
        int currEnd=events[0].stop;
        // Добавляем событие в решение
        result.add(events[0]);
        // Проход по оставшимся элементам
        while (i<events.length)
        {
            // Если начало элемента совпадает или дальше текущего конца, то оно нам подходит
            if(events[i].start>=currEnd && events[i].stop<=to)
            {
                // Добавляем его в решение
                result.add(events[i]);
                // Смещаем текущую правую границу последнего события
                currEnd=events[i].stop;
            }
            // Идем к следующему элементу в любом случае(даже если текущий не подошел)
            i++;
        }



        return result;                        //вернем итог
    }
}