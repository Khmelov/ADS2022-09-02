package by.it.group151004.korol.lesson2;

import java.util.ArrayList;
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
        //events - события которые нужно распределить в аудитории
        //в период [from, int] (включительно).
        //оптимизация проводится по наибольшему числу непересекающихся событий.
        //начало и конец событий могут совпадать.
        List<Event> result;
        result = new ArrayList<>();
        //ваше решение.
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 1; i < events.length; i++) {
                if (events[i].start < events[i-1].start) {
                    Event tmp = events[i];
                    events[i]=events[i-1];
                    events[i-1]=tmp;
                    flag=true;
                }
            }
        }

        int i=0;
        int start,length;
        Event tmp;
        while(i<events.length-1) {
            start=events[i].start;
            length=events[i].stop-events[i].start;
            tmp = events[i];

            for(int j=i;j< events.length;j++){
                if (events[j].start==start && (events[j].stop-events[j].start<length)){
                    start=events[j].start;
                    length=events[j].stop-events[j].start;
                    tmp=events[j];
                }
            }
            while(events[i].start<start+length && i<events.length-1) i++;
            result.add(tmp);
        }
        return result;        //вернем итог
    }
}