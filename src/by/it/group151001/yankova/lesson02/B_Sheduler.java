package by.it.group151001.yankova.lesson02;
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

    private static void qsort(Event[] events, int l, int r, boolean flag){
        int i = l, j = r; Event x;
        x = events[(l+r)/2];
        while(i<=j){
            if(flag){
                while(events[i].start < x.start) i++;
                while(events[j].start > x.start) j--;
            }else{
                while(events[i].stop < x.stop) i++;
                while(events[j].stop > x.stop) j--;
            }
            if(i <= j){
                Event temp = events[i];
                events[i] = events[j];
                events[j] = temp;
                i++; j--;
            }
        }
        if(j>l) qsort(events, l, j, flag);
        if(i<r) qsort(events, i, r, flag);
    }
    //events - события которые нужно распределить в аудитории
    //в период [from, int] (включительно).
    //оптимизация проводится по наибольшему числу непересекающихся событий.
    //начало и конец событий могут совпадать.8/
    List<Event> calcStartTimes(Event[] events, int from, int to) {
        List<Event> result = new ArrayList<>();
        int min = 0, start = 0;
        boolean fl;
        if(events.length == 0) return result;
        while(from<to){
            qsort(events, start, events.length - 1, true);
            fl = false;
            for(int i = start; i < events.length; i++)
                if(events[i].start >= from) {
                    min = i;
                    start = min + 1;
                    fl = true;
                    break;
                }
            qsort(events, start-1, events.length - 1, false);
            if (events[min].stop > to || !fl) break;
            result.add(events[min]);
            from = events[min].stop;
        }
        return result;
    }
}