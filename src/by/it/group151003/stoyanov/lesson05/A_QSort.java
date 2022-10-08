package by.it.group151003.stoyanov.lesson05;

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

    //отрезок
    private class Segment  implements Comparable<Segment>{
        int start;
        int stop;

        Segment(int start, int stop){
            this.start = start;
            this.stop = stop;
            //тут вообще-то лучше доделать конструктор на случай если
            //концы отрезков придут в обратном порядке

            if (stop < start) {
                start ^= stop;
                stop ^= start;
                start ^= stop;
            }
        }

        @Override
        public int compareTo(Segment o) {
            return Integer.compare(start, o.start);
        }
    }

    private <T extends Comparable<T>> void swap(T[] arr, int a, int b) {
        T temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private <T extends Comparable<T>> int partition(T[] arr, int l, int r) {
        while (l < r) {
            if (arr[l].compareTo(arr[r]) <= 0) {
                r--;
            }
            else if (arr[l].compareTo(arr[l + 1]) >= 0) {
                swap(arr, l, l + 1);
                l++;
            } else {
                swap(arr, l + 1, r);
            }
        }

        return l;
    }

    private <T extends Comparable<T>> void sort(T[] arr, int l, int r) {
        while (l < r) {
            int pivot = partition(arr, l, r);
            sort(arr, l, pivot - 1);
            l = pivot + 1;
            //sort(arr, pivot + 1, r);
        }
    }


    private <T extends Comparable<T>> void qs(T[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private boolean belongsToSegment(Segment s, int point) {
        return s.start <= point && s.stop >= point;
    }
    private int getRecordCount(Segment[] segments, int point) {
        int count = 0;
        for (Segment s : segments)
            if (belongsToSegment(s, point)) count++;

        return count;
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
            points[i] = scanner.nextInt();
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор
        qs(segments);
        for (int i = 0; i < m; i++)
            result[i] = getRecordCount(segments, points[i]);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
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
