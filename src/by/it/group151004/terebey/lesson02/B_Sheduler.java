package by.it.group151004.terebey.lesson02;

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
        public String toString() {
            return "("+ start +":" + stop + ")";
        }
        public int compareTo(Event compareEvent) {
            return this.stop - compareEvent.stop;
        }
    }

    public static void main(String[] args) {
        B_Sheduler instance = new B_Sheduler();
        Event[] events = new B_Sheduler.Event[] {new B_Sheduler.Event(6, 12),  new B_Sheduler.Event(7, 8), new B_Sheduler.Event(8, 11), new B_Sheduler.Event(11, 13),
                new B_Sheduler.Event(12, 14)
        };

        List<Event> starts = instance.calcStartTimes(events,8,14);
        System.out.println(starts);
    }

    List<Event> calcStartTimes(Event[] events, int from, int to) {
        //events - события которые нужно распределить в аудитории
        //в период [from, int] (включительно).
        //оптимизация проводится по наибольшему числу непересекающихся событий.
        //начало и конец событий могут совпадать.
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