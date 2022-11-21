package by.it.group151004.kozyr.lesson05;

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
        public int compareTo(Segment o){
            return this.stop - o.stop;
        }
    }

    public static int BinarySearch(Segment[] A, int value, int left,int right){
        boolean found = false;
        while (left <= right && found != true){
            int mid = (left + right)/2;
            if (A[mid].start > value)
                right = mid - 1;
            else{
                if (A[mid].stop < value)
                    left = mid + 1;
                else{
                    found = true;
                    return mid;
                }
            }
        }
        return -1;
    }

    public Segment[] QSort(Segment[] A, int left, int right){
        while (left < right) {
            Segment middlePart = partition(A, left, right);
            QSort(A, left, middlePart.start - 1);
            left = middlePart.stop + 1;

        }
        return A;
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

        QSort(segments,0, segments.length - 1);

        for (int i = 0; i < m; i++){
            int res = BinarySearch(segments,points[i],0, segments.length - 1);
            if (res > -1){
                int count = 1;
                int j = res + 1;
                while (j < n && points[i] <= segments[j].stop){
                    if (segments[j].start <= points[i])
                        count++;
                    j++;
                }
                j = res - 1;
                while (j >= 0 && points[i] <= segments[j].stop){
                    if (segments[j].start <= points[i])
                        count++;
                    j--;
                }
                result[i] = count;
            }
            else
                result[i] = 0;
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    public Segment partition(Segment[] A, int left, int right){
        int l = left;
        int current = left;
        int r = right;
        Segment value = A[left];
        while(current <= r){
            if (A[current].compareTo(value) < 0){
                Segment temp = A[current];
                A[current] = A[l];
                A[l] = temp;
                l++;
                current++;}
            else{
                if (A[current].compareTo(value) == 0)
                    current++;
                else{
                    Segment temp = A[current];
                    A[current] = A[r];
                    A[r] = temp;
                    r--;
                }

            }
        }
        Segment res = new Segment(l,r);
        return res;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151004/kozyr/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
