package by.it.group151004.buhovets.lesson02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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

    public void sort(Event[] e){
        int min;
        Event temp;

        for(int i=0; i< e.length-1;i++){
            min = i;
            for(int j=i+1;j<e.length;j++){
                if((e[j].start<e[min].start) || ((e[j].start==e[min].start) && (e[j].stop<e[min].stop)) ){
                    min=j;
                }
            }
            temp=e[min];
            e[min]=e[i];
            e[i]=temp;
        }
    }

    List<Event> calcStartTimes(Event[] events, int FROM, int TO) {
        //events - события которые нужно распределить в аудитории
        //в период [from, int] (включительно).
        //оптимизация проводится по наибольшему числу непересекающихся событий.
        //начало и конец событий могут совпадать.
        List<Event> result;
        result = new ArrayList<>();


        //ваше решение.
        // sort(events);
        Arrays.sort(events, Comparator.comparing(option -> option.stop));
        int savedstop=FROM;
        int i=0;

      //  for(int i=0;i<events.length;i++){
        while(savedstop<TO){
            if(events[i].start==savedstop)
            {
                result.add((events[i]));
                savedstop=events[i].stop;
            }

            if(i<events.length-1)
                i++;
            else {
               i=0;
               savedstop++;
            }
        }








        return result;                        //вернем итог
    }
}