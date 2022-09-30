package by.it.group151001.manchik.lesson02;

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
            return "(" + start + ":" + stop + ")";
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
    public static void Sort(Event[] array, int min, int max){
        if (array.length == 0) return;
        if (min >= max) return;
        int i = min, j = max, x = array[(i+j)/2].stop;
        Event temp;
        while (i <= j) {
            while (array[i].stop < x) i++;
            while (array[j].stop > x) j--;
            if (i <= j) {
                temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++; j--;
            }
        }
        if (j > min) Sort(array, min, j);
        if (i < max) Sort(array, i ,max);
    }
    List<Event> calcStartTimes(Event[] events, int from, int to) {
        //events - события которые нужно распределить в аудитории
        //в период [from, int] (включительно).
        //оптимизация проводится по наибольшему числу непересекающихся событий.
        //начало и конец событий могут совпадать.
        List<Event> result;
        result = new ArrayList<>();
        //ваше решение.
        int i = 0, j ,maxID = events.length-1, lastop = 0, minstart;
        Sort(events,0,maxID);
        while (i<=maxID){
            if ((events[i].start>=from) && (events[i].stop<=to) && (events[i].start>=lastop)) {
                j = i+1; minstart = i;
                while ((j<=maxID) && (events[i].stop == events[j].stop)){
                    if ((events[j].start>=lastop) && (events[j].start<events[minstart].start)) minstart = j;
                    j++;
                }
                i = minstart;
                result.add(events[i]);
                lastop = events[i].stop;
            }
            i++;
        }
        return result;                        //вернем итог
    }
}