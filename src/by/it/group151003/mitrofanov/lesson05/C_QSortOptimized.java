package by.it.group151003.mitrofanov.lesson05;

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
            return Integer.compare(start, o.start);
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
            segments[i] = new Segment(scanner.nextInt(), scanner.nextInt());
        }

        //читаем точки
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }

        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор
        quicksort(segments, 0, segments.length - 1);
        for (int i = 0; i < points.length; i++) {
            int index = binarySearch(segments, points[i]);
            int count = 0;
            int j = index;
            while (j >= 0 && segments[j].start <= points[i]) {
                if (segments[j].stop >= points[i])
                    count++;
                j--;
            }
            result[i] = count;
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        return result;
    }
    public int binarySearch(Segment[] segments, int point) {
        int leftBorder = 0, rightBorder = segments.length - 1;
        while (rightBorder > leftBorder)
        {
            if (segments[(leftBorder + rightBorder + 1) / 2].start > point)
                rightBorder -= (rightBorder - leftBorder + 1) / 2;
            else
                leftBorder += (rightBorder - leftBorder + 1) / 2;
        }
        return leftBorder;
    }

    private void quicksort(Segment[] segments, int left, int right) {
        if (left >= right)
            return;
        int i = left, j = right;
       Segment pivot = segments[left + (right - left) / 2];

        while (i <= j) {
            while (segments[i].compareTo(pivot) == -1) {
                i++;
            }
            while (segments[j].compareTo(pivot) == 1) {
                j--;
            }
            if (i <= j) {
                swap(segments, i, j);
                i++;
                j--;
            }
        }
        if (left < j)
            quicksort(segments, left, j);
        if (i < right)
            quicksort(segments, i, right);
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
