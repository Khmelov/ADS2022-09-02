package by.it.group151004.bashlikov.lesson02;

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
        List<Double> starts=instance.calcStartTimes(events,1); //рассчитаем моменты старта, с длинной сеанса 1
        System.out.println(starts);                            //покажем моменты старта
    }
    //модификаторы доступа опущены для возможности тестирования
    List<Double> calcStartTimes(double[] events, double workDuration){
        //events - события которые нужно зарегистрировать
        //timeWorkDuration время работы видеокамеры после старта
        List<Double> result = new ArrayList<>();
        int i = 0, j = 0;
        ArrayList<Double> startsTimes = new ArrayList<>();
        //Массив времен включений камеры

        Arrays.sort(events);
        //Сортировка

        double startTime = events[0];
        startsTimes.add(startTime);
        //сохранение времени первого включения в массив включений

        //Формирование времен включения камеры в зависимости от workDuration
        while (startTime < events[events.length - 1]) {
            startTime += workDuration;
            startsTimes.add(startTime);
        }

        //Выделение не покрываемых событий
        while (i < events.length) {
            if (j >= events.length - 1 || i >= startsTimes.size()) {
                //Проверка на выхождение за границы
                break;
            }
            if (events[j] >= startsTimes.get(i) - workDuration) {
                result.add(events[j]);
                i = (int) Math.abs(events[j]);
                i++;
                continue;
            }
            j++;
        }

        //[1.0, 2.2, 3.7, 5.5, 8.1]
        //[1.0, 1.1, 1.6, 2.2, 2.4, 2.7, 3.7, 3.9, 5.5, 8.1, 9.1]

        //[1.9, 2.1, 2.3, 2.5, 4.6, 5.0, 5.1, 5.4, 5.6, 6.3, 6.4, 6.5, 7.2, 7.8, 10.4]
        //[1.9, 4.6, 6.3, 7.8, 10.4]
        //6.3 -> events[9] > startsTimes.get(4)

        return result;
    }
}
