package by.it.group151004.bashlikov.lesson02;

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
        return algorithm(events, from, to);
    }

    private void swap(Event[] events, int index1, int index2) {
        Event tmp = events[index1];
        events[index1] = events[index2];
        events[index2] = tmp;
    }


    private void sort(Event[] events) {
        /* Сортировка массива событий */
        prevSort(events);
        postSort(events);
    }

    private void prevSort(Event[] events) {
        /* Сортировка по значениям начала */
        for (int i = events.length - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                if (events[j].start > events[j + 1].start) {
                    swap(events, j, j + 1);
                }
            }
        }
    }

    private void postSort(Event[] events) {
        /* Сортировка по значениям конца */
        int index;
        for (int i = 0; i < events.length; i++){
            index = i;
            for (int j = i + 1; j < events.length; j++){
                if (events[i].start == events[j].start && events[j].stop < events[index].stop) {
                    index = j;
                }
            }
            swap(events, i, index);
        }
    }

    private List<Event> algorithm(Event[] events, int from, int to) {
        List<Event> result  = new ArrayList<>();
        int i = 0;

        sort(events);

        while (events[i].start < from) i++;
        result.add(events[i]);
        //Берем первое событие с начала времени аудитории

        i++;
        while (i < events.length){
            if ((events[i].start >= result.get(result.size() - 1).stop) && (events[i].stop <= to))
                result.add(events[i]);
            i++;
        }
        return result;
    }
}