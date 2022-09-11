package by.it.group151003.mytnik.lesson02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class A_VideoRegistrator {
    public List<Double> calcStartTimes(double[] events, double workDuration) {
        List<Double> stops = new ArrayList<>();
        Arrays.sort(events);

        stops.add(events[0]);

        for (int i = 1; i < events.length; i++) {
            if (Math.abs(events[i] - stops.get(stops.size() - 1)) - workDuration >= 10e-6) {
                stops.add(events[i]);
            }
        }

        return stops;
    }
}
