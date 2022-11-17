package by.it.group151002.haiduk.lesson05;

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
            if (start < stop) {
                this.start = start;
                this.stop = stop;
            } else {
                this.stop = start;
                this.start = stop;
            }
        }

        @Override
        public int compareTo(Segment o) {
            return Integer.compare(this.start, o.start);
        }
    }

    public void quickSort(Segment[] segments, int left, int right) {
        if (left < right) {
            int q = partition(segments, left, right);
            quickSort(segments, left, q - 1);
            quickSort(segments, q + 1, right);
        }
    }

    public void swap(Segment[] segments, int i, int j){
        Segment temp = segments[j];
        segments[j] = segments[i];
        segments[i] = temp;
    }

    public int partition(Segment[] segments, int left, int right) {
        Segment x = segments[right];
        int j = left - 1;
        for (int k = left; k < right; k++) {
            if (segments[k].compareTo(x) <= 0) {
                j++;
                swap(segments, j, k);
            }
        }
        swap(segments, j + 1, right);
        return j + 1;
    }

    int[] getAccessory(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        int m = scanner.nextInt();
        int[] points = new int[m];
        int[] result = new int[m];
        for (int i = 0; i < n; i++)
            segments[i]=new Segment(scanner.nextInt(),scanner.nextInt());
        for (int i = 0; i < m; i++)
            points[i]=scanner.nextInt();
        quickSort(segments, 0, segments.length - 1);
        for (int i = 0; i < points.length; i++){
            int j = 0;
            while (j < segments.length && segments[j].start <= points[i]){
                if (points[i] <= segments[j].stop)
                    result[i]++;
                j++;
            }
        }
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result=instance.getAccessory(stream);
        for (int index:result){
            System.out.print(index + " ");
        }
    }

}
