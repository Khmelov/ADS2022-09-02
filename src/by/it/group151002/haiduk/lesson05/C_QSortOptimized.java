package by.it.group151002.haiduk.lesson05;

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
    public class Segment implements Comparable<Segment> {
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

    public int[] getAccessory2(InputStream stream) {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        int m = scanner.nextInt();
        int[] points = new int[m];
        int[] result = new int[m];
        for (int i = 0; i < n; i++)
            segments[i] = new Segment(scanner.nextInt(), scanner.nextInt());
        for (int i = 0; i < m; i++)
            points[i] = scanner.nextInt();
        quickSort(segments, 0, segments.length - 1);
        for (int i = 0; i < points.length; i++) {
            int index = binarySearch(segments, points[i]);
            if (index == -1)
                result[i] = 0;
            else {
                int count = 1, j = index - 1;
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
        return result;
    }

    public int binarySearch(Segment[] segments, int point) {
        int left = 0, right = segments.length - 1, middle;
        while (left <= right) {
            middle = (left + right) / 2;
            if (segments[middle].start <= point && segments[middle].stop >= point)
                return middle;
            if (segments[middle].start > point)
                right = middle - 1;
            else
                left = middle + 1;
        }
        return -1;
    }

    public void quickSort(Segment[] segments, int left, int right) {
        while (left < right) {
            int[] cores = partition(segments, left, right);
            if (cores[0] - left < right - cores[1]) {
                quickSort(segments, left, cores[0] - 1);
                left = cores[1] + 1;
            } else {
                quickSort(segments, cores[1] + 1, right);
                right = cores[0] - 1;
            }
        }
    }

    public void swap(Segment[] segments, int i, int j) {
        Segment temp = segments[i];
        segments[i] = segments[j];
        segments[j] = temp;
    }

    public int[] partition(Segment[] segments, int left, int right) {
        int core = left + (int) (Math.random() * (right - left + 1));
        int coreStart = segments[core].start;
        int i = left, j = right, k = left;
        while (k <= j) {
            if (segments[k].start < coreStart) {
                swap(segments, i, k);
                i++;
                k++;
            } else if (segments[k].start > coreStart) {
                swap(segments, k, j);
                j--;
            } else
                k++;
        }
        return new int[] { i, j };
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
