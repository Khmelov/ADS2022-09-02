package by.it.group151003.halai.lesson2;

import java.util.*;

public class A_VideoRegistrator {
    public static void main(String[] args) {
        A_VideoRegistrator instance = new A_VideoRegistrator();
        double[] events = { 1, 4.2, 3.9, 2.4, 2.2, 2.8, 3.9, 7.1, 8.1, 5.5, 7.3 };
        List<Double> starts = instance.calcStartTimes(events, 1);
        System.out.println(starts);
    }

    public List<Double> calcStartTimes(double[] events, double workDuration) {
        List<Double> result = new ArrayList<>();
        Arrays.sort(events);
        int i = 0;
        while (i <   events.length) {
            result.add(events[i]);
            double finish = result.get(result.size() - 1) + workDuration;
            i++;
            while (i < events.length && events[i] <= finish) {
                i++;
            }
        }
        return result;
    }
}
