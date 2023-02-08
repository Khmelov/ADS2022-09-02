package by.it.group151003.saydalikhujaev.lesson02;

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

    void sortEvents(Event[] events) {
        for (int i = 1; i < events.length; i++ ) {
            int j = i;
            boolean wasExchange;
            do {
                wasExchange = false;
                if (events[j].stop < events[j - 1].stop) {
                    Event tmp = events[j];
                    events[j] = events[j - 1];
                    events[j - 1] = tmp;
                    wasExchange = true;
                }
                j--;
            } while (wasExchange && j > 0);
        }
    }

    List<Event> calcStartTimes(Event[] events, int from, int to) {
        //events - события которые нужно распределить в аудитории
        //в период [from, int] (включительно).
        //оптимизация проводится по наибольшему числу непересекающихся событий.
        //начало и конец событий могут совпадать.
        List<Event> result;
        result = new ArrayList<>();
        //ваше решение.

        sortEvents(events);
        result = new ArrayList<>(Arrays.asList(events));
        int currentEventEndTime = 0;
        if(result.size() != 0)
            currentEventEndTime = result.get(0).stop;
        for (int i = 1; i < result.size(); ) {
            if (result.get(i).start < currentEventEndTime)
                result.remove(i);
            else {
                currentEventEndTime = result.get(i).stop;
                i++;
            }
        }
        return result;                        //вернем итог
    }
}