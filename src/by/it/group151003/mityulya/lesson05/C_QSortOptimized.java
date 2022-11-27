package by.it.group151003.mityulya.lesson05;

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
            return start - o.start;
        }
    }

    void QuickSort(Segment[] arr, int left, int right){
        while(left < right) {
            Pair m = Partition(arr, left, right);
            if(m.x - left < right - m.y){
                QuickSort(arr,left,m.x);
                left = m.getY();
            }
            else{
                QuickSort(arr,m.getY(),right);
                right = m.getX();
            }
        }

    }

    int BinarySearch(Segment[] segments,int point){
        int left = 0;
        int right = segments.length - 1;
        int middle;
        while(left <= right){
            middle = (left + right) / 2;
            if(segments[middle].start <= point && point <= segments[middle].stop){
                return middle;
            }
            else if(segments[middle].start < point){
                left = middle + 1;
            }
            else{
                right = middle - 1;
            }
        }
        return -1;
    }

    Pair Partition(Segment[] arr, int left, int right){
        int mid = left;
        Segment x = arr[right];

        while (mid <= right)
        {
            if (arr[mid].compareTo(x) < 0)
            {
                swap(arr, left, mid);
                ++left;
                ++mid;
            }
            else if (arr[mid].compareTo(x) > 0)
            {
                swap(arr, mid, right);
                --right;
            }
            else {
                ++mid;
            }
        }
        return new Pair(left - 1, mid);
    }

    private void swap(Segment[] arr, int i, int j) {
        Segment temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    record Pair(int x, int y) {

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
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
        QuickSort(segments,0,n - 1);
        int j;
        for(int i = 0; i < m;i++){
            int ind = BinarySearch(segments,points[i]);
            result[i] = 0;
            if(ind >= 0) {
                result[i] = 1;
                j = ind + 1;
                while (j < n && segments[j].start <= points[i]) {
                    if(segments[j].stop >= points[i]) {
                        result[i]++;
                        j++;
                    }
                }
                j = ind - 1;
                while (j >= 0) {
                    if(segments[j].stop >= points[j] && segments[j].start <= points[j]) {
                        result[i]++;
                        j--;
                    }
                }
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151003/mityulya/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
