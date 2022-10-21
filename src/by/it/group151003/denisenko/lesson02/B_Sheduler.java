package by.it.group151003.denisenko.lesson02;
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

        int prevStop = from;
        for (Event event : events) {
            if (to >= event.stop && prevStop <= event.start) {
                prevStop = event.stop;
                result.add(event);
            }
        }

        return result;
    }
}