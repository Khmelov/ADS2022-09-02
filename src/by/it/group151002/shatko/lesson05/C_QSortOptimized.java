package by.it.group151002.shatko.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Видеорегистраторы и площадь 2.
Условие то же что и в задаче А.

        По сравнению с задачей A доработайте алгоритм так, чтобы
        1) он оптимально использовал время и память:
            - за стек отвечает элиминация хвостовой рекурсии,
            - за сам массив отрезков - сортировка на месте
            - рекурсивные вызовы должны проводиться на основе 3-разбиения

        2) при поиске подходящих отрезков для точки реализуйте метод бинарного поиска
        для первого отрезка решения, а затем найдите оставшуюся часть решения
        (т.е. отрезков, подходящих для точки, может быть много)

    Sample Input:
    2 3
    0 5
    7 10
    1 6 11
    Sample Output:
    1 0 0

*/


public class C_QSortOptimized {

    //отрезок
    private class Segment implements Comparable<Segment>{
        int start;
        int stop;

        Segment(int start, int stop){
            this.start = Math.min(start, stop);
            this.stop = this.start == start ? stop : start;
        }

        @Override
        public int compareTo(Segment o) {
            return stop < o.stop ? -1 : (stop == o.stop ? (Integer.compare(start, o.start)) : 1);
        }
    }
    void swap(Segment[] arr, int i, int j) {
        Segment temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    void quickSort(Segment[] arr, int l, int r) {
        while (l < r) {
            int i;
            int j;

            if (r - l == 1) {
                if (arr[l].compareTo(arr[r]) > 0)
                    swap(arr, l, r);
                i = l;
                j = r;
            } else {
                int mid = l, low = l, high = r;
                Segment pivot = arr[r];

                while (mid <= high) {

                    if (arr[mid].compareTo(pivot) < 0)
                        swap(arr, mid++, low++);
                    else if (arr[mid].compareTo(pivot) == 0)
                        mid++;
                    else
                        swap(arr, mid, high--);
                }

                i = low - 1;
                j = mid;
            }

            quickSort(arr, l, i);
            l = j;
        }
    }

    int[] binarySearch(Segment[] segments, int[] points) {
        int[] result = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            int l = 0;
            int r = segments.length - 1;
            int mid = 0;

            while (l <= r) {
                mid = (l + r) / 2;
                if (points[i] > segments[mid].stop) {
                    l = mid + 1;
                } else if (points[i] < segments[mid].start)
                    r = mid - 1;
                else {
                    result[i] = 1;
                    break;
                }
            }
            l = mid - 1;
            while (l >= 0 && points[i] <= segments[l--].stop)
                result[i]++;

            l = mid + 1;

            while (l < segments.length && points[i] >= segments[l++].start)
                result[i]++;

        }
        return result;
    }

    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        int m = scanner.nextInt();
        int[] points = new int[m];
        int[] result = new int[m];

        for (int i = 0; i < n; i++) {
            segments[i] = new Segment(scanner.nextInt(), scanner.nextInt());
        }

        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }

        quickSort(segments, 0, n - 1);

        result = binarySearch(segments, points);

        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/shatko/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result = instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
