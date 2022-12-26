package by.it.group151002.ryabov.lesson05;

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
            if (start < stop){
                this.start = start;
                this.stop = stop;
            }
            else{
                this.stop = start;
                this.start = stop;
            }
        }

        @Override
        public int compareTo(Segment o) {
            //подумайте, что должен возвращать компаратор отрезков
            return Integer.compare(this.start, o.start);
        }
    }
    void swap(Segment[] arr, int i, int j) {
        Segment t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    int binaryFind(Segment[] arr, int value) {
        int left = 0;
        int right = arr.length - 1;
        int result = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (value < arr[mid].start)
                right = mid - 1;
            else if (value > arr[mid].stop)
                left = mid + 1;
            else {
                result = mid;
                break;
            }
        }
        return result;
    }

    void qSort(Segment[] segments, int left, int right) {
        while (left < right) {
            int[] cores = partition(segments, left, right);
            qSort(segments, left, cores[0]);
            left = cores[1];
        }
    }

    int[] partition(Segment[] arr, int left, int right) {
        int i = 0, j = 0;
        int[] res = new int[2];
        if (right - left == 1){
            if (arr[left].compareTo(arr[right]) > 0)
                swap(arr, left, right);
            i = left;
            j = right;
        }
        else {
            Segment pivot = arr[(left + right) / 2];
            i = left;
            j = right;
            int k = left;
            while (k <= j) {
                if (arr[k].compareTo(pivot) < 0)
                    swap(arr, i++, k++);
                else if (arr[k].compareTo(pivot) > 0)
                    swap(arr, k, j--);
                else
                    k++;
            }
        }
        res[0] = i;
        res[1] = j;
        return res;
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
        qSort(segments, 0, segments.length - 1);
        for (int i = 0; i < m; i++) {
            int count = 0;
            int f = binaryFind(segments, points[i]);
            if (f != -1)
                count++;
            for (int j = 0; j < f && points[i] >= segments[j].start; j++)
                if (points[i] <= segments[j].stop)
                    count++;
            for (int j = f + 1; j < segments.length && points[i] >= segments[j].start; j++)
                if (points[i] <= segments[j].stop)
                    count++;
            result[i] = count;
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/ryabov/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
