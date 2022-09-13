package by.it.group151004.haiko.lesson02;

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

        //Выполнил сортировку по возрастанию по параметру start
        int index;
        Event swap;
        for (int i=0; i<events.length; i++){
            index=i;
            for (int j=i+1; j<events.length; j++){
                if (events[j].start<events[index].start)    index = j;

            }
            swap = events[i];
            events[i] = events[index];
            events[index]=swap;
        }

        //Внутри каждого блока с одинаковым start повторил то же действие по параметру stop
        for (int i=0; i<events.length; i++){
            index=i;
            for (int j=i+1; j<events.length; j++){

                if (events[i].start == events[j].start)
                    if (events[j].stop < events[index].stop) index = j;

            }
            swap = events[i];
            events[i] = events[index];
            events[index]=swap;
        }

        //Взяли по первому элементу из каждого блока событий с одинаковым значением start
        index=0;
        while (events[index].start < from) index++;
        result.add(events[index]);
        index++;
        while (index < events.length){
            if ( (events[index].start>=result.get(result.size()-1).stop) && (events[index].stop <= to) )
                result.add(events[index]);
            index++;
        }


        return result;                        //вернем итог
    }
}