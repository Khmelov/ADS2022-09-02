package by.it.group151001.trybchik.lesson05;

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
            if (this.start == o.start) {
                if (this.stop > o.stop) return 1;
                if (this.stop == o.stop) return -1;
                if (this.stop < o.stop) return 0;
            } else if (this.start > o.start) return 1;
            return 0;
        }
    }
    void Swap (Segment[] mas, int a, int  b)
    {
        Segment tmp = mas[a];
        mas[a] = mas[b];
        mas[b]=tmp;
    }
    Segment[] qsort(Segment[] a, int left, int right)
    {
        Segment v = a[right];
        if(right<=left) return a;
        int i = left;
        int j = right -1;
        int p = left-1;
        int q = right;
        while(i<=j)
        {
            while (a[i].compareTo(v) == 0) i++;
            while (a[j].compareTo(v) == 1) j--;
            if(i>=j)
                break;
            Swap(a,i,j);
            if(a[i].compareTo(v) == -1)
            {
                p++;
                Swap(a,p,i);
            }
            i++;
            if(a[j].compareTo(v) == -1)
            {
                q--;
                Swap(a,q,j);
            }
            j--;
        }
        Swap(a,i,right);
        j = i-1;
        i++;
        for(int k = left;k<=p;k++,j--)
        {
            Swap(a,k,j);
        }
        for(int k = right-1;k>=q;k--,i++)
        {
            Swap(a,k,i);
        }
        qsort(a,left,j);
        qsort(a,i,right);
        return a;
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
        qsort(segments,0,n-1);
        for (int i = 0;i<n;i++)
        {
            int left = 0;
            int right = segments.length-1;
            int middle =(left+right)/2;
            while(points[i]>segments[middle].start && left<=right)
            {
                right = middle-1;
                middle= (left+right)/2;
            }
            for(int j = 0;j<=middle;j++)
            {
                if(points[i]>=segments[j].start && points[i]<=segments[j].stop) result[i]++;
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
