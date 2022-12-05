package by.it.group151004.afanasenko.lesson02;

import java.util.*;

public class B_Sheduler {
    public static class Event {
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
        B_Sheduler.Event[] events = { new B_Sheduler.Event(0, 3), new B_Sheduler.Event(0, 1),
                new B_Sheduler.Event(1, 2),
                new B_Sheduler.Event(3, 5), new B_Sheduler.Event(1, 3),
                new B_Sheduler.Event(1, 3), new B_Sheduler.Event(1, 3),
                new B_Sheduler.Event(3, 6), new B_Sheduler.Event(2, 7),
                new B_Sheduler.Event(2, 3), new B_Sheduler.Event(2, 7),
                new B_Sheduler.Event(7, 9), new B_Sheduler.Event(3, 5),
                new B_Sheduler.Event(2, 4), new B_Sheduler.Event(2, 3),
                new B_Sheduler.Event(3, 7), new B_Sheduler.Event(4, 5),
                new B_Sheduler.Event(6, 7), new B_Sheduler.Event(6, 9), new B_Sheduler.Event(7, 9),
                new B_Sheduler.Event(8, 9), new B_Sheduler.Event(4, 6), new B_Sheduler.Event(8, 10),
                new B_Sheduler.Event(7, 10) };
        List<B_Sheduler.Event> starts = instance.calcStartTimes(events, 0, 10);
        System.out.println(starts);
    }

    public List<B_Sheduler.Event> calcStartTimes(B_Sheduler.Event[] events, int from, int to) {
        List<B_Sheduler.Event> result = new ArrayList<>();
        Arrays.sort(events, new B_Sheduler.SortByRight());
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

    public class SortByRight implements Comparator<B_Sheduler.Event> {
        @Override
        public int compare(B_Sheduler.Event a, B_Sheduler.Event b) {
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