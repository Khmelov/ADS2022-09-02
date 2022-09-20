package by.it.a_khmelev.lesson02;

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

import by.it.group151003.shafarenko.lesson01.lesson02.A_VideoRegistrator;

public class A_VideoRegistrator {

    public static void main(String[] args) {
        A_VideoRegistrator instance=new A_VideoRegistrator();
        double[] events=new double[]{2.5, 5.1, 4.6, 6.4, 5.0, 6.3, 7.2, 2.1, 2.3, 7.8, 10.4, 1.9, 5.6, 5.4, 6.5};
        List<Double> starts=instance.calcStartTimes(events,5); //рассчитаем моменты старта, с длинной сеанса 1
        System.out.println(starts);                            //покажем моменты старта
    }
    //модификаторы доступа опущены для возможности тестирования
    List<Double> calcStartTimes(double[] events, double workDuration){
        //events - события которые нужно зарегистрировать
        //timeWorkDuration время работы видеокамеры после старта
        List<Double> result;
        result = new ArrayList<>();
        Arrays.sort(events);
        int i=0;                              //i - это индекс события events[i]
        while (i < events.length) {
        	result.add(events[i]);
        	i++;
        	while (i < events.length && events[i] <= result.get(result.size()-1) + workDuration) {
        		i++;
        	}
        }

        return result;                        //вернем итог
    }
}

