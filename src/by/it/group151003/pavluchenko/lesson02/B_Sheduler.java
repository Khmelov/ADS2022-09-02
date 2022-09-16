package by.it.group151003.pavluchenko.lesson02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class B_Sheduler {
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
        Event temp;
        result = new ArrayList<>();
        int min = 0;
        for (int i = 0; i <= events.length - 2; i++)
        {
            min = i;
            for (int j = i + 1; j <= events.length - 1; j++)
            {
                if( events[min].stop > events[j].stop)
                {
                    min = j;
                }
                temp = events[i];
                events[i] = events[min];
                events[min] = temp;
            }
        }
        int i = 0;
        int time = events[i].stop;
        result.add(events[i]);
        i = 1;
        while ( i < events.length)
        {
            if ( time <= events[i].start)
            {
                result.add(events[i]);
                time = events[i].stop;
            }
            i++;
        }
        return result;
    }
}