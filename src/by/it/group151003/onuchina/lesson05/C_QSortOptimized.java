package by.it.group151003.onuchina.lesson05;

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
            points[i]=scanner.nextInt();
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор

        quickSort(segments, 0, n - 1);

        for (int i = 0; i < points.length; i++){
            int first_entry = binSearch(segments, points[i]);
            if (first_entry == -1) result[i] = 0;
            else {
                int j = first_entry + 1;
                int counter = 1;
                while (j < n && segments[j].start <= points[i]) {
                    if (segments[j].stop >= points[i]) counter++;
                    j++;
                }
                result[i] = counter;
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    private void quickSort(Segment[] segments, int from, int to){
        while (from < to) {
            int kernels[] = partition(segments, from, to);
            //Sort the shortest part
            if (kernels[0] - from < to - kernels[1]){
                quickSort(segments, from, kernels[0] - 1);
                from = kernels[1] + 1;
            }
            else {
                quickSort(segments, kernels[1] + 1, to);
                to = kernels[0] - 1;
            }
        }
    }

    private int[] partition(Segment[] segments, int from, int to) {
        int kernel = from + (int) (Math.random() * (to - from + 1));
        int kernel_value = segments[kernel].start;
        int i = from;
        int j = to;
        int k = from;
        Segment temp;
        while (k <= j) {
            if (segments[k].start < kernel_value) {
                temp = segments[i];
                segments[i] = segments[k];
                segments[k] = temp;
                i++;
                k++;
            } else if (segments[k].start > kernel_value) {
                temp = segments[k];
                segments[k] = segments[j];
                segments[j] = temp;
                j--;
            } else {
                k++;
            }
        }
        return new int[]{i, j};
    }

    private int binSearch(Segment[] segments, int point) {
        int from = 0;
        int to = segments.length - 1;
        int mid = 0;
        while (from <= to) {
            mid = (from + to) / 2;
            if (segments[mid].start <= point && segments[mid].stop >= point) {
                return mid;
            }
            else if (segments[mid].start > point) {
                to = mid - 1;
            }
            else {
                from = mid + 1;
            }
        }
        return -1;
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
