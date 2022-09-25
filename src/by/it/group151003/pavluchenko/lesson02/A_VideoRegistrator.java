package by.it.group151003.pavluchenko.lesson02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class A_VideoRegistrator {

    public static void main(String[] args) {
        A_VideoRegistrator instance=new A_VideoRegistrator();
        double[] events=new double[]{1, 1.1, 1.6, 2.2, 2.4, 2.7, 3.9, 8.1, 9.1, 5.5, 3.7};
        List<Double> starts=instance.calcStartTimes(events,1);
        System.out.println(starts);
    }
    List<Double> calcStartTimes(double[] events, double workDuration){
        List<Double> result;
        result = new ArrayList<>();
        int i=0;
        Arrays.sort(events);
        result.add(events[i]);
        i = 1;
        workDuration = events[i] +1;
        while ( i < events.length)
        {
            if ( workDuration >= events[i])
                ;
            else {
                workDuration = events[i] + 1;
                result.add(events[i]);
            }
            i++;


        }
        return result;
    }
}
