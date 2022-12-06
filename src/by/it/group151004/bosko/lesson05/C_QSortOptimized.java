package by.it.group151004.bosko.lesson05;

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
    private static class Segment implements Comparable<Segment> {
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

    void swap(Segment[] s, int a, int b){
        Segment tmp = s[a];
        s[a] = s[b];
        s[b] = tmp;

    }

    int[] partition(Segment[] a, int l, int r){
        Segment tmp = a[l];
        for (int i = l + 1; i < a.length; i++){
            if (a[i].compareTo(tmp) < 0){
                swap(a, i, l);
                l++;
            } else if (a[i].compareTo(tmp) > 0) {
                swap(a, i, r);
                i--;
                r--;
            }
        }
        return new int[] {l, r};
    }

    void qs(Segment[] a, int left, int right){
        while (left < right) {
            int[] pivots = partition(a, left, right);
            qs(a, left, (pivots[0]) - 1);
            left = (pivots[1]) + 1;
        }
    }

    int binarySearch(Segment[] s, int a){
        int l, res = l = 0, r = s.length - 1;

        while (l <= r){
            int mid = (l + r) / 2;
            if (a >= s[mid].start){
                l = mid + 1;
                res = mid;
            } else {
                r = mid - 1;
            }

        }
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
            segments[i]= new Segment(scanner.nextInt(), scanner.nextInt());
        }
        //читаем точки
        for (int i = 0; i < m; i++) {
            points[i]=scanner.nextInt();
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор

        for (int i = 0; i < m; i++) {
            for (int j = binarySearch(segments, points[i]); j < n; j++)
                if (points[i] >= segments[j].start && points[i] <= segments[j].stop)
                    result[i]++;
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
