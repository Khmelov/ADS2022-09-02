package by.it.group151003.romanko.lesson02;

import java.util.*;
import java.util.function.Function;
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

        public int length(){
            return this.stop - this.start;
        }

        public boolean hasCollision(Event e) {
            if (this.stop > e.start) {
                return e.stop > this.start;
            }else
                return false;
        }


        @Override
        public String toString() {
            return "(" + start + ":" + stop + ")";
        }
    }

    public static void main(String[] args) {
        B_Sheduler instance = new B_Sheduler();
        Event[] events = {new Event(0, 3), new Event(0, 1), new Event(1, 2), new Event(3, 5),
                new Event(1, 3), new Event(1, 3), new Event(1, 3), new Event(3, 6),
                new Event(2, 7), new Event(2, 3), new Event(2, 7), new Event(7, 9),
                new Event(3, 5), new Event(2, 4), new Event(2, 3), new Event(3, 7),
                new Event(4, 5), new Event(6, 7), new Event(6, 9), new Event(7, 9),
                new Event(8, 9), new Event(4, 6), new Event(8, 10), new Event(7, 10)
        };

        List<Event> starts = instance.calcStartTimes(events, 0, 10);  //рассчитаем оптимальное заполнение аудитории
        System.out.println(starts);                                 //покажем рассчитанный график занятий
    }

    List<Event> calcStartTimes(Event[] events, int from, int to) {
        List<Event> result = new ArrayList<>();
        //sort by "stop" field and then (stop - start) so most of requirable elems would be right before
        //the shift of "stop" fields
        Arrays.sort(events, Comparator.comparingInt((Event a) -> a.stop).thenComparingInt(a -> a.stop - a.start));
        int i = 0;
        //finding first elem
        while (i < events.length && result.isEmpty()) {
            if (events[i].start >= from && events[i].stop <= to) {
                result.add(events[i]);
            }
            i++;
        }
        if (!result.isEmpty()) {
            //prev - value of last added element
            Event prev = result.get(result.size() - 1);
            //patentionally the next elem to be added
            Event candidate = prev;
            while (i < events.length) {
                if (!events[i].hasCollision(prev)) {
                    //if there is a shift of "stop" field it usually desired elem
                    if (events[i].stop != candidate.stop) {
                        //check if that actually it
                        if (!candidate.hasCollision(prev)) {
                            result.add(candidate);
                            prev = candidate;
                        }
                    }
                    candidate = events[i];
                }
                i++;
            }
        }
        return result;
    }
}