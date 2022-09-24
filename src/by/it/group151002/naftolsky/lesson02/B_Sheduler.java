package by.it.group151002.naftolsky.lesson02;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Comparator;

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

    public class sortingRight implements Comparator<Event> {
        public int compare(Event first, Event second) {
            if (first.stop == second.stop) {
                return 0;
            } else if (first.stop < second.stop) {
                return -1;
            }
            return 1;
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
        //events - события которые нужно распределить в аудитории
        //в период [from, to] (включительно).
        //оптимизация проводится по наибольшему числу непересекающихся событий.
        //начало и конец событий могут совпадать.
        List<Event> result = new ArrayList<>();
        //ваше решение.
        Arrays.sort(events, new sortingRight());
        int i = 0;
        int n = events.length;
        while (events[i].start < from && i < n) {
            i++;
        }

        if (i < n && events[i].stop <= to) {
            result.add(events[i]);
            i++;
            while (i < n && events[i].stop <= to) {
                if (events[i].start >= result.get(result.size() - 1).stop) {
                    result.add(events[i]);
                }
                i++;
            }
        }

        return result;
    }
}