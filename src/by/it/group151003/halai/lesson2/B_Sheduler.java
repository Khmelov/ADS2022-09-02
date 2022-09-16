package by.it.group151003.halai.lesson2;

import java.util.*;

public class B_Sheduler {
    public static class Event {
        int    start;
        int stop;

        Event(int start,    int stop) {
            this.start =    start;
            this.stop =    stop;
        }

        @Override
        public String toString() {
              return "(" + start + ":" + stop + ")";
        }
    }

    public static void main(String[]    args) {
        B_Sheduler instance = new B_Sheduler();
        Event[] events = { new Event(0, 3), new Event(0, 1), new Event(1, 2), new Event(3, 5), new Event(1, 3), new Event(1, 3), new Event(1, 3), new Event(3, 6), new Event(2, 7), new Event(2, 3), new Event(2, 7), new Event(7, 9), new Event(3, 5), new Event(2, 4), new Event(2, 3), new Event(3, 7), new Event(4, 5), new Event(6, 7), new Event(6, 9), new Event(7, 9), new Event(8, 9), new Event(4, 6), new Event(8, 10), new Event(7, 10) };
        List<Event> starts = instance.calcStartTimes(events, 0, 10);
        System.out.println(starts);
    }

    public List<Event> calcStartTimes(Event[] events, int from, int to) {
        List<Event> result = new ArrayList<>();
        Arrays.sort(events, new SortByRight());
        int i = 0;
        int n = events.length;
        while (events[i].start < from) {
            i++;
            if (i >= n) {
                break;
            }
        }

        if (i < n && events[i].stop <= to) {
            result.add(events[i]);
            i++;
            while (i < n && events[i].stop <= to) {
                if (events[i].start >= result.get(result.size() - 1).stop) {
                    result.add(events[i]);
                }
                i++;
            }
        }

        return result;
    }

    public class SortByRight implements Comparator<Event> {
        @Override
        public int compare(Event a, Event b) {
            if (a.stop < b.stop) {
                return -1;
            }
            if (a.stop == b.stop) {
                return 0;
            }
            return 1;
        }
    }
}