package by.it.group151003.mytnik.lesson02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class B_Sheduler {
    public static class Event {
        int start;
        int stop;

        public Event(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public String toString() {
            return "("+ start +":" + stop + ")";
        }
    }

    public List<Event> calcStartTimes(Event[] events, int from, int to) {
        List<Event> result = new ArrayList<>();

        Arrays.sort(events, Comparator.comparingInt(o -> o.stop));

        result.add(events[0]);

        for (int i = 1; i < events.length; i++) {
            if (events[i].start >= result.get(result.size() - 1).stop) {
                result.add(events[i]);
            }
        }

        return result;
    }
}