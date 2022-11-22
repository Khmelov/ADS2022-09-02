package by.it.group151002.kuvshinov.lesson05;

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
            if (start > stop) {
                this.stop = start;
                this.start = stop;
            } else {
                this.start = start;
                this.stop = stop;
            }
        }

        @Override
        public int compareTo(Segment o) {
            //подумайте, что должен возвращать компаратор отрезков
            if (this.start > o.start){
                return 1;
            } else if (this.start < o.start){
                return -1;
            }
            return 0;
        }
    }

    public int partition(Segment[] segments, int left, int right) {
        Segment x = segments[right];
        int j = left - 1;
        int k = 0;
        while (k < right){
            if (segments[k].compareTo(x) < 1) {
                changePos(segments, ++j, k);
            }
            k++;
        }
        changePos(segments, j + 1, right);
        return j + 1;
    }

    public void quickSort(Segment[] segments, int left, int right) {
        if (left < right) {
            int mid = partition(segments, left, right);
            quickSort(segments, left, mid - 1);
            quickSort(segments, mid + 1, right);
        }
    }

    public void changePos(Segment[] segments, int i, int j){
        Segment buff = segments[i];
        segments[i] = segments[j];
        segments[j] = buff;
    }

    int[] getAccessory(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        int m = scanner.nextInt();
        int[] points = new int[m];
        int[] result = new int[m];
        for (int i = 0; i < n; i++)
            segments[i] = new Segment(scanner.nextInt(),scanner.nextInt());
        for (int i = 0; i < m; i++)
            points[i] = scanner.nextInt();
        quickSort(segments, 0, segments.length - 1);
        int i = 0;
        while (i < points.length){
            int j = 0;
            while (j < segments.length && segments[j].start <= points[i]){
                if (points[i] <= segments[j].stop)
                    result[i]++;
                j++;
            }
            i++;
        }
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/kuvshinov/lesson05/dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result = instance.getAccessory(stream);
        for (int index:result){
            System.out.print(index + " ");
        }
    }

}