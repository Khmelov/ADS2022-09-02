package by.it.group151003.stoyanov.lesson05;

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
    private class Segment implements Comparable<Segment> {
        int start;
        int stop;

        Segment(int start, int stop){
            this.start = start;
            this.stop = stop;

            if (stop < start) {
                start ^= stop;
                stop ^= start;
                start ^= stop;
            }
        }

        public int compareTo(Segment o) {
            return Integer.compare(start, o.start);
        }
    }


    private <T extends Comparable<T>> void swap(T[] arr, int a, int b) {
        T temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private <T extends Comparable<T>> int[] partition(T[] arr, int l, int r) {
        T pivot = arr[l];
        for (int i = l + 1; i <= r; i++) {
            if (arr[i].compareTo(pivot) < 0) {
                swap(arr, i, l);
                l++;
            } else if (arr[i].compareTo(pivot) > 0) {
                swap(arr, i, r);
                r--;
                i--;
            }
        }
        return new int[] { l, r };
    }

    private <T extends Comparable<T>> void sort(T[] arr, int l, int r) {
        while (l < r) {
            int[] pivots = partition(arr, l, r);
            sort(arr, l, (pivots[0]) - 1);
            l = (pivots[1]) + 1;
        }
    }

    private <T extends Comparable<T>> void qs(T[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private int binarySearch(Segment[] arr, int point) {
        int left = 0, right = arr.length - 1;
        int res = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid].start <= point) {
                left = mid + 1;
            } else {
                res = mid;
                right = mid - 1;
            }
        }

        return res;
    }

    private boolean belongsToSegment(Segment s, int point) {
        return s.start <= point && s.stop >= point;
    }

    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
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
        qs(segments);

        for (int i = 0; i < m; i++) {
            int index = binarySearch(segments, points[i]);
            for (int j = index; j >= 0; j--)
                if (belongsToSegment(segments[j], points[i]))
                    result[i]++;
            }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151003/stoyanov/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
