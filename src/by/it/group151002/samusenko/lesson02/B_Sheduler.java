package lesson02;

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

    public static void QuickSort(Event[] evs, int left, int right){
        if(left>=right) {
            return;
        }
        Event key= evs[left];
        int i=left;
        int j=right;
        while(i<j){
            while(evs[j].stop>=key.stop && i<j){
                j--;
            }
            while(evs[i].stop<=key.stop && i<j){
                i++;
            }
            if(i<j){
                Event temp=evs[i];
                evs[i]=evs[j];
                evs[j]=temp;
            }
        }
        evs[left]=evs[i];
        evs[i]=key;
       // count++;
        QuickSort(evs,left,i-1);
        QuickSort(evs,i+1,right);
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
        QuickSort(events, 0, events.length -1); // сортировка событий по правому краю (stop)
        int i =0;
        while (i< events.length && events[i].start < from)
            i++;//доходим до начала промежутка (если будет не с нуля)

        while ((i < events.length)&&(events[i].stop <= to)){//смотрим, чтобы доходило не только до конца событий, но и до конца заданного промежутка
            result.add(events[i]);//добавляем event с самым малым stop
            int n = i;
            while ((i < events.length) && (events[i].start < events[n].stop))//исключаем все event, пересекающиеся с текущим
                i++;
        }

        return result;
    }
}