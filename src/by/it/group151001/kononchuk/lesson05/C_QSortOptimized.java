package by.it.group151001.kononchuk.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Видеорегистраторы и площадь 2.
Условие то же что и в задаче А.

        По сравнению с задачей A доработайте алгоритм так, чтобы
        1) он оптимально использовал время и память:
            - за стек отвечает элиминация хвостовой рекурсии,                +
            - за сам массив отрезков - сортировка на месте                   +
            - рекурсивные вызовы должны проводиться на основе 3-разбиения    +

        2) при поиске подходящих отрезков для точки реализуйте метод бинарного поиска
        для первого отрезка решения, а затем найдите оставшуюся часть решения
        (т.е. отрезков, подходящих для точки, может быть много)              +

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
            return this.start - o.start;
        }
    }

    private void seg_swap(Segment[] seg, int l, int r){
        Segment temp = seg[l];
        seg[l] = seg[r];
        seg[r] = temp;
    }

    private int[] Partition(Segment[] seg, int l, int r){
        Segment x = seg[l];
        int[] res = new  int[2];

        res[0] = l;
        res[1] = l;
        int j = l;

        for (int i = l + 1; i <= r; i++)
        {
            if (seg[i].compareTo(x) == 0)
            {
                j++;
                res[1]++;
                seg_swap(seg, i, res[1]);
                seg_swap(seg, i, j);
            }
            else if(seg[i].compareTo(x) < 0){
                j++;
                seg_swap(seg, i, j);
            }
        }

        int size = res[1] - res[0] + 1;
        res[0] = j + 1 - size;
        res[1] = j;
        int x_i = size - 1;
        for (int i = res[1]; i >= res[0]; i--){
            seg_swap(seg, l + x_i, i);
            x_i--;
        }

        return res;
    }


    private void QSort(Segment[] seg, int l, int r){
        while (l < r){
            int[] m = Partition(seg, l, r);
            QSort(seg, l, m[0] - 1);
            l = m[1] + 1;
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
        QSort(segments, 0, n - 1);


        for(int i = 0; i < m; i++)
        {
            int bin_find = -1;
            int l = 0, r = n - 1;
            while (l <= r){
                int mid = (l + r) / 2;
                if(segments[mid].start <= points[i]){
                    bin_find = mid;
                    l = r + 1;
                } else {
                    r = mid - 1;
                }
            }
            if(bin_find == -1)
            {
                result[i] = 0;
            }else{
                int j = bin_find;
                result[i] = 0;
                while(j < n && segments[j].start <= points[i])
                {
                    if (segments[j].stop >= points[i])
                    {
                        result[i]++;
                    }
                    j++;
                }

                j = bin_find - 1;
                while(j >= 0)
                {
                    if (segments[j].stop >= points[i])
                    {
                        result[i]++;
                    }
                    j--;
                }
            }
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151001/kononchuk/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
