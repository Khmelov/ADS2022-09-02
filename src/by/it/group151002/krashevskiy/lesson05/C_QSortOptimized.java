package by.it.group151002.krashevskiy.lesson05;

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
            if (start > stop) {
                this.start = stop;
                this.stop = start;
            } else {
                this.start = start;
                this.stop = stop;
            }
        }

        @Override
        public int compareTo(Segment o) {
            int comparing = Integer.compare(this.start, o.start);
            if (comparing == 0)
                return Integer.compare(this.stop, o.stop);

            return comparing;
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
            points[i]=scanner.nextInt();
        }
        quickSort(segments, 0, segments.length - 1);

        int amount;
        for (int i = 0; i < m; i++) {
            amount = 0;
            for (Segment camera : segments){
                if (points[i] >= camera.start && points[i] <= camera.stop) {
                    amount++;
                } else {
                    break;
                }
            }
            result[i] = amount;
        }

        for (Segment seg: segments) {
            System.out.println(seg.start + " " + seg.stop);
        }
        return result;
    }

    void quickSort(Segment[] arr, final int low, final int high) {
        if (low < high) {
            int part = partition(arr, low, high);
            quickSort(arr, low, part);
            quickSort(arr, part + 1, high);
        }
    }

    void swap(Segment[] arr, final int i, final int j) {
        Segment temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    int partition(Segment[] arr, final int low, final int high) {
        Segment pivot = arr[(low + high) / 2];
        int i = low;
        int j = high;
        while (i < j) {
            while (arr[i].compareTo(pivot) < 0)
                i++;
            while (arr[j].compareTo(pivot) > 0)
                j--;
            if (i < j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }

        return j;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/krashevskiy/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
