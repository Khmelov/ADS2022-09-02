package by.it.group151001.shlyk.lesson02;

import java.security.InvalidAlgorithmParameterException;
import java.sql.PreparedStatement;
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
    interface methodSort {
        void sort(int lBorder, int rBorder);
    }
        static class Event {

        static void sort(Event[] array){
            methodSort self = new methodSort() {
                @Override
                public void sort(int lBorder, int rBorder){
                    int i = lBorder;
                    int j = rBorder;
                    Event x = array[ (i + j) / 2];
                    do{
                        while(x.start > array[i].start)
                            i++;
                        while(x.start < array[j].start)
                            j--;
                        if (i <= j){
                            Event temp = array[i];
                            array[i] = array[j];
                            array[j] = temp;
                            j--;
                            i++;
                        }
                    } while (i < j);
                    if (i < rBorder)
                        sort(i, rBorder);
                    if (j > lBorder)
                        sort(lBorder, j);
                }
            };
            self.sort(0, array.length - 1);
        }
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

        if (events.length == 0)
            return new ArrayList<>();
        Event.sort(events);
        List<Event> result = new ArrayList<>();

        Event bestChoice;
        int iEvents = 0;
        while(events[iEvents].start < from) {
            iEvents++;
            if (iEvents >= events.length)
                return  new ArrayList<>();
        }
        bestChoice = events[iEvents];
        boolean isUpdated;
        for (int i = iEvents + 1; i < events.length; i++){
            isUpdated = events[i].start <= bestChoice.start && bestChoice.stop > events[i].stop;
            if(isUpdated)
            {
                bestChoice = events[i];
            }
            else
                if (events[i].start >= bestChoice.stop)
                {
                if (events[i].start < to) {
                    result.add(bestChoice);
                    bestChoice = events[i];
                } else
                    break;
            }
        }
        result.add(bestChoice);
        return result;
    }
}