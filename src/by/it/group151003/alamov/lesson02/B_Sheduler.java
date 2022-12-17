package by.it.group151003.alamov.lesson02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class B_Sheduler {
    static class Event implements Comparable<Event> {
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

        public int compareTo(Event o) {
            return Integer.compare(this.stop, o.stop);
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
        List<Event> res = new ArrayList<>();
        Arrays.sort(events);
        Event currentEvent = null;

        int i = 0;
        boolean fl = true;
        while (fl) {
            if (events[i].start >= from) {
                currentEvent = events[i];
                res.add(events[i]);
                fl = false;
            }
        }

        for (i = 1; i < events.length; i++)
            if (currentEvent.stop <= events[i].start && currentEvent.stop <= to) {
                res.add(events[i]);
                currentEvent = events[i];
            }
        return res;
    }
}