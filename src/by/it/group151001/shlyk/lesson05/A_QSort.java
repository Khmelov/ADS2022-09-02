package by.it.group151001.shlyk.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Видеорегистраторы и площадь.
На площади установлена одна или несколько камер.
Известны данные о том, когда каждая из них включалась и выключалась (отрезки работы)
Известен список событий на площади (время начала каждого события).
Вам необходимо определить для каждого события сколько камер его записали.

В первой строке задано два целых числа:
    число включений камер (отрезки) 1<=n<=50000
    число событий (точки) 1<=m<=50000.

Следующие n строк содержат по два целых числа ai и bi (ai<=bi) -
координаты концов отрезков (время работы одной какой-то камеры).
Последняя строка содержит m целых чисел - координаты точек.
Все координаты не превышают 10E8 по модулю (!).

Точка считается принадлежащей отрезку, если она находится внутри него или на границе.

Для каждой точки в порядке их появления во вводе выведите,
скольким отрезкам она принадлежит.
    Sample Input:
    2 3
    0 5
    7 10
    1 6 11
    Sample Output:
    1 0 0

*/

public class A_QSort {

    public static void quickSort(Segment[] periods, int iMin, int iMax){
        int i = iMin;
        int j = iMax;
        Segment x = periods[(iMin + iMax) / 2];
        do{
            while(periods[i].compareTo(x) < 0)
                i++;
            while(periods[j].compareTo(x) > 0)
                j--;
            if(i <= j){
                Segment temp = periods[i];
                periods[i] = periods[j];
                periods[j] = temp;
                j--;
                i++;
            }
        }while(i < j);
        if(i < iMax){
            quickSort(periods, i, iMax);
        if(j > iMin)
            quickSort(periods, iMin, j);
        }
    }
    public static int[] getDuplicates(Segment[] periods, int[] events){
        int[] result = new int[events.length]; //how many cams have seen event
        quickSort(periods, 0, periods.length - 1);
        for(int i = 0; i < events.length; i++){

            for (Segment period : periods) {
                if (period.stop >= events[i] && period.start <= events[i])
                    result[i]++;
                else
                    if(period.start > events[i])
                        break;
            }
        }
        return result;

    }
    //отрезок
    private class Segment  implements Comparable<Segment>{
        int start;
        int stop;

        Segment(int start, int stop){
            if(start > stop){
                this.start = stop;
                this.stop = start;
            } else {
                this.start = start;
                this.stop = stop;
            }

        }

        //compare with other by start value (i.e. left side)
        @Override
        public int compareTo(Segment o) {
            int result = (this.start - o.start);

            if (result != 0){
                result = result > 0 ? 1 : -1;
            }
            return result;
        }
    }


    int[] getAccessory(InputStream stream)  {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int nEntries = scanner.nextInt();
        Segment[] segments =new Segment[nEntries];
        //число точек
        int m = scanner.nextInt();
        int[] points=new int[m];

        //читаем сами отрезки
        for (int i = 0; i < nEntries; i++) {
            //читаем начало и конец каждого отрезка
            segments[i]=new Segment(scanner.nextInt(),scanner.nextInt());
        }
        //читаем точки
        for (int i = 0; i < m; i++) {
            points[i]=scanner.nextInt();
        }
       int[] nDuplicates = getDuplicates(segments, points);
        for (int nDuplicate : nDuplicates) {
            System.out.println(nDuplicate);
        }

        return nDuplicates;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151001/shlyk/lesson05/dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result=instance.getAccessory(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
