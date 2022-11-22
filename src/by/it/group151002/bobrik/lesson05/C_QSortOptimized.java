package by.it.group151002.bobrik.lesson05;

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

    int[] partition(Segment[] arr, int l, int r) {
        int[] points = new int[2];
        int x = l + (int)(Math.random() * (r - l + 1));
        int xStart = arr[x].start;
        int i = l;
        int j = r;
        int k = l;
        while (k <= j) {
            if (arr[k].start < xStart) {
                Segment temp = arr[i];
                arr[i] = arr[k];
                arr[k] = temp;
                i++;
                k++;
            }
            else if (arr[k].start > xStart) {
                Segment temp = arr[j];
                arr[j] = arr[k];
                arr[k] = temp;
                j--;
            }
            else {
                k++;
            }
        }
        points[0] = i;
        points[1] = j;
        return points;
    }

    void quickSort(Segment[] arr, int l, int r) {
        while (l < r) {
            int[] points = partition(arr, l, r);
            if (points[0] - l < r - points[1]) {
                quickSort(arr, l, points[0] - 1);
                l = points[1] + 1;
            } else {
                quickSort(arr, points[1] + 1, r);
                r = points[0] - 1;
            }
        }
    }

    public int binarySearch(Segment[] arr, int x) {
        int l = 0;
        int r = arr.length - 1;
        int m;
        while (l <= r) {
            m = (l + r) / 2;
            if (arr[m].start <= x && arr[m].stop >= x) {
                return m;
            }
            if (arr[m].start > x) {
                r = m - 1;
            }
            else if (arr[m].start < x) {
                l = m + 1;
            }
        }
        return -1;
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
        quickSort(segments, 0, n - 1);

        for (int i = 0; i < m; i++) {
            int index = binarySearch(segments, points[i]);
            if (index == -1)
                result[i] = 0;
            else {
                result[i]++;
                int j = index - 1;
                while (j >= 0 && segments[j].start <= points[i]) {
                    if (points[i] <= segments[j].stop) {
                        result[i]++;
                    }
                    j--;
                }
                j = index + 1;
                while (j < n && segments[j].start <= points[i]) {
                    if (points[i] <= segments[j].stop) {
                        result[i]++;
                    }
                    j++;
                }
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
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
