package by.it.group151004.buhovets.lesson02;

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



    //комментарии от проверочного решения сохранены для подсказки, но вы можете их удалить.
    //подготовка к жадному поглощению массива событий
    //hint: сортировка Arrays.sort обеспечит скорость алгоритма
    //C*(n log n) + C1*n = O(n log n)

    //пока есть незарегистрированные события
    //получим одно событие по левому краю
    //и запомним время старта видеокамеры
    //вычислим момент окончания работы видеокамеры
    //и теперь пропустим все покрываемые события
    //за время до конца работы, увеличивая индекс


    //модификаторы доступа опущены для возможности тестирования
    List<Double> calcStartTimes(double[] events, double workDuration){
        //events - события которые нужно зарегистрировать
        //timeWorkDuration время работы видеокамеры после старта
        Arrays.sort(events);
        List<Double> result;
        result = new ArrayList<>();
        double  record=events[0];
        result.add(record);

        for(int i=1;i< events.length;i++){
            if(events[i]-record-workDuration>0.00001){
                record=events[i];
                result.add(record);
            }
        }
        return result;                        //вернем итог
    }
}
