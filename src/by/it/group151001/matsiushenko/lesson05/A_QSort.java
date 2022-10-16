package by.it.group151001.matsiushenko.lesson05;

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

    public static void QSort(Segment[] periods, int MinI, int MaxI){
        int i = MinI;
        int j = MaxI;
        Segment x = periods[(MinI + MaxI) / 2];
        do{
            while(periods[i].compareTo(x) < 0) {
                i++;
            }
            while(periods[j].compareTo(x) > 0) {
                j--;
            }
            if(i <= j){
                Segment temp = periods[i];
                periods[i] = periods[j];
                periods[j] = temp;
                j--;
                i++;
            }
        }while(i < j);
        if(i < MaxI){
            QSort(periods, i, MaxI);
            if(j > MinI) {
                QSort(periods, MinI, j);
            }
        }
    }
    public static int[] getDuplicates(Segment[] periods, int[] events){
        int[] res = new int[events.length];
        QSort(periods, 0, periods.length - 1);
        for(int i = 0; i < events.length; i++){
            for (Segment period : periods) {
                if (period.stop >= events[i] && period.start <= events[i]) {
                    res[i]++;
                } else {
                    if (period.start > events[i]) {
                        break;
                    }
                }
            }
        }
        return res;
    }

    //отрезок
    private class Segment  implements Comparable<Segment>{
        int start;
        int stop;

        Segment(int start, int stop){
            if (start <= stop) {
                this.start = start;
                this.stop = stop;
            }else{
                this.start = stop;
                this.stop = start;
            }
            //тут вообще-то лучше доделать конструктор на случай если
            //концы отрезков придут в обратном порядке
        }

        @Override
        public int compareTo(Segment o) {
            //подумайте, что должен возвращать компаратор отрезков
            int res = this.start - o.start;

            if (res != 0){
                res = res > 0 ? 1 : -1;
            }
            return res;
        }
    }


    int[] getAccessory(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        Segment[] segments=new Segment[n];
        //число точек
        int m = scanner.nextInt();
        int[] points=new int[m];
        int[] result=new int[m];

        //читаем сами отрезки
        for (int i = 0; i < n; i++) {
            //читаем начало и конец каждого отрезка
            segments[i]=new Segment(scanner.nextInt(),scanner.nextInt());
        }
        //читаем точки
        for (int i = 0; i < m; i++) {
            points[i]=scanner.nextInt();
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор
        int[] nDuplicates = getDuplicates(segments, points);
        for (int nDuplicate : nDuplicates) {
            System.out.println(nDuplicate);
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return nDuplicates;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result=instance.getAccessory(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
