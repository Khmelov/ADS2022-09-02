package by.it.group151001.skripskaya.lesson05;

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
          //  return Integer.compare(start, o.start);
            if (this.stop < o.stop) return -1;
            if (this.stop > o.stop) return 1;
            return 0;
        }
    }

    int quickSort (Segment a[], int n) {
        int max = 2 << 10;
        int i, j, left, right, pos = 0;;
        Segment x, tmp;
        int[] rightReqStack = new int[max];
        int[] leftReqStack  = new int[max];

        leftReqStack[0] = 0; rightReqStack[0] = n-1;
        do {
            left = leftReqStack[pos];
            right = rightReqStack[pos];
            pos--;
            do {
                i = left; j = right;
                x = a[ (left + right) / 2];
                do {
                    while (x.compareTo(a[i]) > 0) i++;
                    while (a[j].compareTo(x) > 0) j--;
                    if (i <= j) {
                        tmp = a[i]; a[i] = a[j]; a[j] = tmp;
                        i++; j--;
                    }
                } while (i <= j);
                if ( i < (left + right) / 2) {
                    if (i < right) {
                        pos++;
                        leftReqStack[pos] = i;
                        rightReqStack[pos] = right;
                    }
                    right = j;
                } else {
                    if (j > left) {
                        pos++;
                        leftReqStack[pos] = left;
                        rightReqStack[pos]= j;
                    }
                    left = i;
                }
            } while (left < right);
        } while (pos != -1);
    return 0;
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

        quickSort(segments, n);

        for (int i=0; i < m; i++){
            int left = 0, right = n - 1, mid = 0;
            while (left <= right){
                mid = (left + right)/2;
                if (points[i] > segments[mid].stop) left = mid + 1;
                else if (points[i] < segments[mid].start) right = mid - 1;
                else {result[i] = 1; break;}
            }
            for (left = mid + 1; left < n; left++)
                if (points[i] >= segments[left].start) result[i]++;
            for (left = mid - 1; left >= 0; left--)
                if (points[i] <= segments[left].stop) result[i]++;
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
