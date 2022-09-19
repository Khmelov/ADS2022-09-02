package by.it.group151001.beryozkin.lesson02;

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
    public static class Event implements Comparable<Event> {
        int start;
        int stop;

        public Event(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Event event) {
         if (event.stop == this.stop) return 0;
         if (event.stop < this.stop) return 1;
         return -1;
        }

        @Override
        public String toString() {
            return "("+ start +":" + stop + ")";
        }
    }

    public static void main(String[] args) {
        B_Sheduler instance = new B_Sheduler();
        Event[] events =  {  new B_Sheduler.Event(2, 3),  new B_Sheduler.Event(2, 6), new B_Sheduler.Event(1, 2), new B_Sheduler.Event(3, 5),
                new B_Sheduler.Event(1, 4),  new B_Sheduler.Event(1, 3), new B_Sheduler.Event(1, 3), new B_Sheduler.Event(3, 6),
                new B_Sheduler.Event(2, 7),  new B_Sheduler.Event(2, 3), new B_Sheduler.Event(2, 7), new B_Sheduler.Event(7, 9),
                new B_Sheduler.Event(4, 5),  new B_Sheduler.Event(3, 4), new B_Sheduler.Event(2, 3), new B_Sheduler.Event(3, 7),
                new B_Sheduler.Event(4, 5),  new B_Sheduler.Event(6, 7), new B_Sheduler.Event(6, 9), new B_Sheduler.Event(7, 9),
                new B_Sheduler.Event(8, 9),  new B_Sheduler.Event(4, 8), new B_Sheduler.Event(9, 10), new B_Sheduler.Event(7, 11)
        };

        List<Event> starts = instance.calcStartTimes(events,1,11);  //рассчитаем оптимальное заполнение аудитории
        System.out.println(starts);                                 //покажем рассчитанный график занятий
    }


    public List<Event> calcStartTimes(Event[] events, int from, int to) {
        //events - события которые нужно распределить в аудитории
        //в период [from, int] (включительно).
        //оптимизация проводится по наибольшему числу непересекающихся событий.
        //начало и конец событий могут совпадать.
        List<Event> result;
        result = new ArrayList<>();
        Arrays.sort(events);
        int start = from;
        int stop = to;
        for (int i=0; i< events.length; i++){
          if (events[i].start >=start && events[i].stop<=stop){
            result.add(events[i]);
            start = events[i].stop;
          }
        }
        return result;                        //вернем итог
    }
}