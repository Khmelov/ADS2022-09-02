package by.it.group151003.shafarenko.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.lang.Math;

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
            if (start > stop) {
                this.start = stop;
                this.stop = start;
            } else {
                this.start = start;
                this.stop = stop;
            }
            //тут вообще-то лучше доделать конструктор на случай если
            //концы отрезков придут в обратном порядке
        }

        @Override
        public int compareTo(Segment o) {
            int thisLength = this.stop - this.start;
            int oLength = o.stop - o.start;

            if (thisLength > oLength)
                return 1;
            else if (thisLength < oLength)
                return -1;
            else if (this.start < o.start)
                return 1;
            else if (this.start > o.start)
                return -1;
            else
                return 0;
        }
    }


    int[] getAccessory(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        //число точек
        int m = scanner.nextInt();
        int[] points = new int[m];
        int[] result = new int[m];

        //читаем сами отрезки
        for (int i = 0; i < n; i++) {
            //читаем начало и конец каждого отрезка
            segments[i] = new Segment(scanner.nextInt(), scanner.nextInt());
        }
        //читаем точки
        for (int i = 0; i < m; i++) {
            points[i]=scanner.nextInt();
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор

        for (int i = 0; i < m; i++) {
            for (Segment segment: segments) {
                if (points[i] <= segment.stop && points[i] >= segment.start) {
                    result[i]++;
                }
            }
        }

        /*int[] arr = new int[] {0, 3, 7, 6, 2, 5, 1, 4};
        QuickSort(arr, 0, arr.length - 1);
        for (int i: arr) {
            System.out.println(i);
        }*/
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    private void QuickSort(Segment[] arr, int left, int right) {
        if (left >= right)
            return;

        int middle = Partition(arr, left, right);
        QuickSort(arr, left, middle);
        QuickSort(arr, middle + 1, right);
    }

    private int Partition(Segment[] arr, int left, int right) {
        Segment pivot = arr[(int)(Math.random() * (right - left) + left)];
        /*int pivot = arr[(left + right) / 2];*/
        int i = left, j = right;
        while (i <= j) {
            while (arr[i].compareTo(pivot) < 0) {
                i++;
            }
            while (arr[j].compareTo(pivot) > 0) {
                j--;
            }
            if (i >= j) {
                return j;
            }
            Swap(arr, i++, j++);
        }
        return j;
    }

    private void Swap(Segment[] arr, int i, int j) {
        Segment tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
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
