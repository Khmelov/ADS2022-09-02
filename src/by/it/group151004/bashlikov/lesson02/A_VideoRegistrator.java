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
        List<Double> tmpArray = new ArrayList<>();
        int i = 0, j = 0;
        //result - коллекция для хранения результатов
        //tmpArray - коллекция для хранения отсортированного events и работы с ним

        Arrays.sort(events);
        //Заполнение tmpArray элементами events
        for (int k = 0; k < events.length; k++) {
            tmpArray.add(events[k]);
        }

        while (tmpArray.size() != 0) {
            result.add(tmpArray.get(0));
            //Добавление элемента в результирующую коллекцию
            double tmp = result.get(i) + workDuration;
            while (tmpArray.get(j) < tmp) {
                tmpArray.remove(j);
                //j++ - изменяться не должно, так как каждый раз изменяемый элемент - нулевой
                if (tmpArray.size() == 0) { break; }
            }
            i++;
            if (tmpArray.size() == 1) { break; }
        }
        return result;
    }
}
