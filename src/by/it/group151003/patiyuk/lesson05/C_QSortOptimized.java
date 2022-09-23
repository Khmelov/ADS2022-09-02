package by.it.group151003.patiyuk.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Random;
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
    private class Segment  implements Comparable<Segment>{
        int start;
        int stop;

        Segment(int start, int stop){
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Segment o) {
            //подумайте, что должен возвращать компаратор отрезков
            return Integer.compare(this.start, o.start);
        }
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
            points[i] = scanner.nextInt();
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор

        quickSort(segments, 0, segments.length - 1);

        for (int i = 0; i < points.length; i++) {
            int index = binarySearch(segments, points[i]);
            if (index == -1) {
                result[i] = 0;
            } else {
                int count = 1;
                int j = index - 1;
                while (j >= 0 && segments[j].start == segments[index].start) {
                    count++;
                    j--;
                }
                j = index + 1;
                while (j < segments.length && segments[j].start == segments[index].start) {
                    count++;
                    j++;
                }
                result[i] = count;
            }
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    private int binarySearch(Segment[] segments, int point) {
        int left = 0;
        int right = segments.length - 1;
        int middle = 0;
        while (left <= right) {
            middle = (left + right) / 2;
            if (segments[middle].start <= point && segments[middle].stop >= point) {
                return middle;
            } else if (segments[middle].start > point) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return -1;
    }

    private void quickSort(Segment[] segments, int left, int right) {
        while (left < right) {
            int[] pivots = partition(segments, left, right);
            if (pivots[0] - left < right - pivots[1]) {
                quickSort(segments, left, pivots[0] - 1);
                left = pivots[1] + 1;
            } else {
                quickSort(segments, pivots[1] + 1, right);
                right = pivots[0] - 1;
            }
        }
    }

    private int[] partition(Segment[] segments, int left, int right) {
        int pivot = left + (int) (Math.random() * (right - left + 1));
        int pivotValue = segments[pivot].start;
        int i = left;
        int j = right;
        int k = left;

        while (k <= j) {
            if (segments[k].start < pivotValue) {
                swap(segments, i, k);
                i++;
                k++;
            } else if (segments[k].start > pivotValue) {
                swap(segments, k, j);
                j--;
            } else {
                k++;
            }
        }

        return new int[]{i, j};
    }

    private void swap(Segment[] segments, int i, int j) {
        Segment tmp = segments[i];
        segments[i] = segments[j];
        segments[j] = tmp;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
