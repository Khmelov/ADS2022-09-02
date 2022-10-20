package by.it.group151001.novik.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
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
        return Integer.compare(this.stop,o.stop);
        }
    }
    void swap(Segment a, Segment b){
        Segment tmp;
        tmp = a;
        a = b;
        b = tmp;
    }
    Segment[] quicksort(Segment arr[], int l, int r){
    int v = arr[r].stop;
     if (r <= l)
            return arr;
    int i = l;
    int j = r - 1;
    int p = l - 1;
    int q = r;
     while (i <= j){
         while (arr[i].stop < v)
             i++;
         while (arr[j].stop > v)
             j--;
         if (i >= j)
             break;
         swap(arr[i], arr[j]);
         if (arr[i].stop == v)
             p++;
         swap(arr[p], arr[i]);
         i++;
         if (arr[j].stop == v)
             q--;
        swap(arr[q], arr[j]);
        j--;
     }

    swap(arr[i], arr[r]);
    j = i - 1;
    i++;
    for (int k = l; k <= p; k++, j--)
        swap(arr[k], arr[j]);
     for (int k = r - 1; k >= q; k--, i++)
        swap(arr[k], arr[i]);
    quicksort(arr, l, j);
    quicksort(arr, i, r);
    return arr;
    }
    int[]  binaryfind(Segment arr[], int value, int l, int r){
        int out [] = new int[2];
        boolean f1 = false;
        int mid = (l + r) / 2;
        while ((l <= r)&&(!f1)){
            mid = (l + r) / 2;
            if ((arr[mid].stop >= value)&&(arr[mid].start <= value)){
                f1 = true;
            } else if (arr[mid].stop > value) {
                r = mid - 1;
            }else l = mid + 1;
        }
        if (f1) {
            out[0] = 1;
        }
        else{
            out[0] = 0;
        }
        out[1] = mid;
        return out;
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
        Arrays.sort(points);
        segments = quicksort(segments,0,segments.length - 1);
        int l = 0;
        int r = segments.length - 1;

        for(int i = 0; i < points.length; i++) {
            int[] tmp = new int[2];
            tmp = binaryfind(segments, points[i], l, r);
            result[i] = tmp[0];
            boolean f1 = true;
            if ((result[i] == 1)&&(i + 1 < points.length)) {
                while ((f1)) {
                    if (points[i + 1] > points[i]) {
                        l = tmp[1];
                        r = segments.length - 1;
                        f1 = false;
                    } else if (points[i + 1] < points[i]) {
                        r = tmp[1];
                        l = 0;
                        f1 = false;
                        } else {
                            i++;
                            result[i] = tmp[0];
                        }


                }

            }else{
                l = 0;
                r = segments.length - 1;
            }
        }



        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор


        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151001/novik/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
