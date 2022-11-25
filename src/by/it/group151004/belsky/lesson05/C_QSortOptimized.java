package by.it.group151004.belsky.lesson05;

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
            return this.stop - o.stop;
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
        QuickSort3(segments, 0, segments.length-1);

        for (int i = 0;i < points.length;i++) {
            int startSeg = BinSearch(segments, points[i]);
            if (startSeg >= 0) {
                for (int j = startSeg;j < segments.length;j++) {
                    if (points[i] >= segments[j].start && points[i] <= segments[j].stop) {
                        result[i]+=1;
                    } else if (points[i] < segments[j].stop) {
                        break;
                    }
                }
            }
        }


        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    public int BinSearch(Segment[] arr, int el) {
        int index = -2;
        int l = 0, r = arr.length-1, mid;
        while (l <= r) {
            mid = (r+l) / 2;
            if (arr[mid].stop < el) {
                l = mid+1;
            } else if (arr[mid].start > el) {
                r = mid-1;
            } else {
                index = mid;
                break;
            }
        }
        return  index;
    }

    public void QuickSort3(Segment[] arr, int l, int r) {
        if (r <= l) return;
        Segment v = arr[r];

        int i = l, j = r-1, p = l-1, q = r;
        while (i <= j) {
            while (arr[i].compareTo(v) < 0) i++;
            while (arr[j].compareTo(v) > 0) j--;

            if (i >= j) break;
            swap(arr, i, j);

            if (arr[i].compareTo(v) == 0) {
                p+=1;
                swap(arr, p, i);
            }
            i+=1;

            if (arr[j].compareTo(v) == 0) {
                q-=1;
                swap(arr, q, j);
            }
            j-=1;
        }
        swap(arr, i, r);
        j = i - 1;
        i+=1;

        for (int k = l;k <= p;k++, j--) {
            swap(arr, k, j);
        }

        for (int k = r-1;k >= q;k--, i++) {
            swap(arr, k, i);
        }

        QuickSort3(arr, l, j);
        QuickSort3(arr, i, r);
    }

    public void swap(Segment[] arr, int a, int b) {
        Segment buf = arr[a];
        arr[a] = arr[b];
        arr[b] = buf;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151004/belsky/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
