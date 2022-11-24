package by.it.group151003.shafarenko.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.lang.Math;

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
    private class Segment  implements Comparable{
        int start;
        int stop;

        Segment(int start, int stop){
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Object o) {

            return 0;
        }
    }


    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
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
            segments[i]=new Segment(scanner.nextInt(),scanner.nextInt());
        }
        //читаем точки
        for (int i = 0; i < m; i++) {
            points[i]=scanner.nextInt();
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор

//        int[] arr = new int[] {1, 2, 1, 3, 10, 12, 1, 1, 1, 5, 7, 4, 1};
//        ThreeWayQuickSort(arr, 0, arr.length - 1);
//        for (int i: arr) {
//            System.out.print(i + " ");
//        }
//        System.out.println();
        ThreeWayQuickSort(points, 0, points.length - 1);
        for (int i = 0; i < segments.length; i++) {
            int left = LeftBinaryFind(points, segments[i].start);
            int right = RightBinaryFind(points, segments[i].stop);
            for (int j = left; j <= right; j++) {
                result[j]++;
            }
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    public int LeftBinaryFind(int[] arr, int value) {
        int left = -1, right = arr.length, middle = -1;
        while (left < right - 1) {
            middle = left + (right - left) / 2;
            if (arr[middle] < value) {
                left = middle;
            } else {
                right = middle;
            }
        }
        return right;
    }

    public int RightBinaryFind(int[] arr, int value) {
        int left = -1, right = arr.length, middle = -1;
        while (left < right - 1) {
            middle = left + (right - left) / 2;
            if (arr[middle] <= value) {
                left = middle  ;
            } else {
                right = middle;
            }
        }
        return left;
    }

    public void ThreeWayQuickSort(int[] arr, int left, int right) {
        if (left >= right)
            return;

        int[] pq;;
        int p, q;

        while (left < right) {
            pq = Partition(arr, left, right);
            p = pq[0];
            q = pq[1];
            if ((p - left) <= (right - q)) {
                ThreeWayQuickSort(arr, left, p);
                left = q;
            } else {
                ThreeWayQuickSort(arr, q, right);
                right = p;
            }
        }
    }

    private int[] Partition(int[] arr, int left, int right) {
        Swap(arr, (int)(Math.random() * (right - left)) + left, right);
        int[] result = new int[2];
        int p = left - 1, q = right;
        int i = p + 1, j = q - 1;
        boolean flag = true;

        while (flag && i <= j) {
            while (i < right && arr[i] < arr[right]) {
                i++;
            }
            while (j > left && arr[j] > arr[right]) {
                j--;
            }
            if (i >= j) {
                flag = false;
            } else {
                Swap(arr, i, j);
                if (arr[i] == arr[right]) {
                    Swap(arr, i, ++p);
                }
                if (arr[j] == arr[right]) {
                    Swap(arr, j, --q);
                }
                i++;
                j--;
            }
        }
        Swap(arr, i, right);
        j = i++ - 1;
//        while (p >= left) {
//            Swap(arr, p--, j--);
//        }
//        while (q < right) {
//            Swap(arr, q++, i++);
//        }
        for (int k = left; k <= p; k++, j--)
            Swap(arr, k, j);
        for (int k = right - 1; k >= q; k--, i++)
            Swap(arr, k, i);
        result[0] = j;
        result[1] = i;
        return result;
    }

    private void Swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "";
        InputStream stream = new FileInputStream(root + "/by/it/a_khmelev/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
