package by.it.group151002.zavaliuk.lesson02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
даны интервальные события events
реализуйте метод calcStartTimes, так, чтобы число принятых к выполнению
непересекающихся событий было максимально.
Алгоритм жадный. Для реализации обдумайте надежный шаг.
*/

public class B_Sheduler {
    //событие у аудитории(два поля: начало и конец)
    static class Event implements Comparable<Event> {
        private final int start;
        private final int stop;

        Event(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public String toString() {
            return "(" + start + ":" + stop + ")";
        }

        public int getStop() {
            return stop;
        }

        public int getStart() {
            return start;
        }

        @Override
        public int compareTo(Event o) {
            return Integer.compare(this.stop, o.getStop());
        }

    }


    public static void main(String[] args) {
        B_Sheduler instance = new B_Sheduler();
        Event[] events = {new Event(0, 3), new Event(0, 1), new Event(1, 2), new Event(3, 5),
                new Event(1, 3), new Event(1, 3), new Event(1, 3), new Event(3, 6),
                new Event(2, 7), new Event(2, 3), new Event(2, 7), new Event(7, 11),
                new Event(3, 5), new Event(2, 4), new Event(2, 3), new Event(3, 7),
                new Event(4, 5), new Event(6, 7), new Event(6, 9), new Event(7, 11),
                new Event(8, 10), new Event(4, 6), new Event(8, 10), new Event(7, 11)
        };

        List<Event> starts = instance.calcStartTimes(events, 0, 10);  //рассчитаем оптимальное заполнение аудитории
        System.out.println(starts);                                 //покажем рассчитанный график занятий
    }

    List<Event> calcStartTimes(Event[] events, int from, int to) {
        List<Event> res = new ArrayList<>();
        Arrays.sort(events);
        Event currentEvent = null;

        int i = 0;
        boolean fl = true;
        while (fl) {
            if (events[i].getStart() >= from) {
                currentEvent = events[i];
                res.add(events[i]);
                fl = false;
            }
        }

        for (i = 1; i < events.length; i++)
            if (currentEvent.getStop() <= events[i].getStart() && currentEvent.getStop() <= to) {
                res.add(events[i]);
                currentEvent = events[i];
            }
        return res;
    }
}