package by.it;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
даны события events
реализуйте метод calcStartTimes, так, чтобы число включений регистратора на
заданный период времени (1) было минимальным, а все события events
были зарегистрированы.
Алгоритм жадный. Для реализации обдумайте надежный шаг.
*/

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
        Arrays.sort(events);
        int i = 0;
        double timeCounter = 0;
        while (timeCounter < events[events.length-1]){
            result.add(events[i]);
            timeCounter = events[i];
            timeCounter += workDuration;
            while (i < events.length-1 & timeCounter >= events[i]) i++;
        }
        return result;
        //вернем итог
    }
}
