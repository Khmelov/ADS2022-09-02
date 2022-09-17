package by.it.group151001.pastukhou.lesson02;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
/*
Даны интервальные события events
реализуйте метод calcStartTimes, так, чтобы число принятых к выполнению
непересекающихся событий было максимально.
Алгоритм жадный. Для реализации обдумайте надежный шаг.
*/

public class B_Sheduler {

    static class Event implements Comparable<Event>{
        int start;
        int stop;

        Event(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }
        @Override
        public int compareTo(Event compareEvent) {
            return this.stop - compareEvent.stop;
        }

        @Override
        public String toString() {
            return "("+ start +":" + stop + ")";
        }
    }

    public static void main(String[] args) {
        B_Sheduler instance = new B_Sheduler();
        Event[] events = {  new Event(8, 10),  new Event(9, 11), new Event(10, 12), new Event(11, 13),
                new Event(12, 14)
        };

        List<Event> starts = instance.calcStartTimes(events,8,14);
        System.out.println(starts);
    }

    List<Event> calcStartTimes(Event[] events, int from, int to) {
        List<Event> result;
        result = new ArrayList<>();

        Arrays.sort(events);
        for (int i = 0; i < events.length; i++) {
            if (events[i].start >= from && events[i].stop <= to) {
                result.add(events[i]);
                from = events[i].stop;
            }
        }
        return result;
    }
}