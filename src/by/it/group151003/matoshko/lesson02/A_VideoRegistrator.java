package by.it.group151003.matoshko.lesson02;

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
        // Первый элемент массива
        int i=0;
        // Сортируем весь массив
        Arrays.sort(events);
        // Пока мы не дошли до конца массива
        while(i<events.length)
        {
            // Добавляем точку, в которой включаем камеру
            result.add(events[i]);
            // Новая граница будет текущая точка + длительность работы камеры
            double border = events[i] + workDuration;
            ++i;
            // Выкидываем покрытые точки, сдвигая индекс
            while(i < events.length && events[i] <= border)
                ++i;
        }



        return result;
    }
}
