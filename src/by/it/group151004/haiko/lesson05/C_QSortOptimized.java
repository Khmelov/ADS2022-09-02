package by.it.group151004.haiko.lesson05;

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
    private class Segment  implements Comparable<C_QSortOptimized.Segment>{
        int start;
        int stop;

        Segment(int start, int stop){
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Segment o) {
            //подумайте, что должен возвращать компаратор отрезков
            return Integer.compare(this.stop, o.stop);
        }
    }

    int bS(Segment[] array, int value) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {

            int mid = (left+right) / 2;
            if ( (array[mid].start <= value) && (value <=array[mid].stop) ) {
                return mid;
            } else if (array[mid].start > value) {
                right = mid - 1;
            } else left = mid + 1;
        }

        return -1;
    }

    public static void swap (Segment[] segs, int ind1, int ind2) {
        if (ind1 != ind2) {
            Segment tmp = segs[ind1];
            segs[ind1] = segs[ind2];
            segs[ind2] = tmp;
        }
    }

    public static int[] div (Segment[] segs, int l, int r) {
        int i = l;
        int curr = l;
        int j = r;
        Segment value = segs[l];
        while (curr <= j) {
            if (segs[curr].compareTo(value) < 0)
                swap(segs, curr++, i++);
            else if (segs[curr].compareTo(value) == 0)
                curr++;
            else
                swap(segs, curr, j--);
        }
        return new int[] {i, j};
    }

    public static void QuickSort (Segment[] segs, int l, int r) {
        while (l < r) {
            int[] tmp = div(segs, l, r);
            if (tmp[0] - l < r - tmp[1]) {
                QuickSort(segs, l, tmp[0] - 1);
                l = tmp[1] + 1;
            } else {
                QuickSort(segs, tmp[1] + 1, r);
                r = tmp[0] + 1;
            }
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
        for (int i = 0; i < n; i++) {
            points[i]=scanner.nextInt();
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор

        QuickSort(segments, 0, n-1);
        for (int i = 0; i < m; i++) {
            result[i] = 0;
            int bin = bS(segments, points[i]);
            if (bin == -1)   break;
            else {
                result[i]++;
                while (segments[bin + 1].start < segments[bin].stop) {

                    result[i]++;
                    bin++;

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
