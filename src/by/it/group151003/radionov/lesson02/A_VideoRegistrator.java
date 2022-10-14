package by.it.group151003.radionov.lesson02;

import java.util.ArrayList;
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

    public void insertSort(double[] events){
        for (int i = 1; i < events.length; i++) {
            for (int j = i; j > 0; j--) {
                if(events[j]<events[j-1]){
                    double temp2 = events[j];
                    events[j] = events[j-1];
                    events[j-1] = temp2;
                }else{
                    break;
                }
            }
        }
    }

    //модификаторы доступа опущены для возможности тестирования
    public List<Double> calcStartTimes(double[] events, double workDuration){
        //events - события которые нужно зарегистрировать
        //timeWorkDuration время работы видеокамеры после старта
        List<Double> result;
        result = new ArrayList<>();
        if (events.length > 0){
            insertSort(events);
        }
        int i=0;
        int j;
        while(i < events.length){
            result.add(events[i]);
            j = i;
            i++;
            while(i < events.length && events[i] <= workDuration + events[j]){
                i++;
            }
        }

        //i - это индекс события events[i]
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



        return result;                        //вернем итог
    }
}
