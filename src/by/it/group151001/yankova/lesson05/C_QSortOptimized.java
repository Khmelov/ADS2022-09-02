package by.it.group151001.yankova.lesson05;

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
        -- исключаем?


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
            return Integer.compare(start, o.start);
        }
    }


    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);

        int n = scanner.nextInt();
        Segment[] segments=new Segment[n];

        int m = scanner.nextInt();
        int[] points=new int[m];
        int[] result=new int[m];

        for (int i = 0; i < n; i++) {
            segments[i]=new Segment(scanner.nextInt(),scanner.nextInt());
        }
        for (int i = 0; i < m; i++) {
            points[i]=scanner.nextInt();
        }

        QuickSort(segments, n);
        for(int i = 0; i < m; i++){
            int l = 0, r = n-1, mid = 0;
            while(l <= r){
                mid = (l + r)/2;
                if(points[i] > segments[mid].stop)
                    l = mid + 1;
                else if(points[i] < segments[mid].start)
                    r = mid - 1;
                else {
                    result[i] = 1;
                    break;
                }
            }
            for(l = mid + 1; l < n; l++)
                if(points[i] >= segments[l].start) result[i]++;
            for(l = mid - 1; l >= 0; l--)
                if(points[i] <= segments[l].stop) result[i]++;
        }
        return result;
    }

    public void QuickSort(Segment[] a, int n){
        int maxStack = 2 << 10;
        int i, j, l, r;
        Segment x, temp;
        int[] rReqStack = new int[maxStack];
        int[] lReqStack = new int[maxStack];
        int posStack = 0;
        lReqStack[0] = 0;
        rReqStack[0] = n-1;
        do {
            l = lReqStack[posStack];
            r = rReqStack[posStack];
            posStack--;
            do {
                i = l;
                j = r;
                x = a[(l + r) / 2];
                do{
                    while (x.compareTo(a[i]) > 0) i++;
                    while (a[j].compareTo(x) > 0) j--;
                    if (i <= j) {
                        temp = a[i];
                        a[i] = a[j];
                        a[j] = temp;
                        i++;
                        j--;
                    }
                } while(i <= j);
                if (i < (l + r) / 2) {
                    if (i < r) {
                        posStack++;
                        lReqStack[posStack] = i;
                        rReqStack[posStack] = r;
                    }
                    r = j;
                } else {
                    if (j > l) {
                        posStack++;
                        lReqStack[posStack] = l;
                        rReqStack[posStack] = j;
                    }
                    l = i;
                }
            } while(l < r);
        } while(posStack != -1);
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151001/yankova/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
