package by.it.group151001.kononova.lesson05;

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
            if(start <= stop) {
                this.start = start;
                this.stop = stop;
            } else{
                this.start = stop;
                this.stop = start;
            }
        }

        @Override
        public int compareTo(Segment o) {
            //подумайте, что должен возвращать компаратор отрезков
            return Integer.compare(this.start, o.start);
        }
    }

    public static int BinSearch(Segment[] arr, int search, int left,int right){
        boolean found = false;
        while (left <= right && found != true){
            int middle = (left + right)/2;
            if (arr[middle].start > search)
                right = middle - 1;
            else{
                if (arr[middle].stop < search)
                    left = middle + 1;
                else{
                    found = true;
                    return middle;
                }
            }
        }
        return -1;
    }

    public Segment[] quickSort(Segment[] arr, int l, int r){
        while (l < r) {
            Segment middle = partition(arr, l, r);
            quickSort(arr, l, middle.start - 1);
            l = middle.stop + 1;
        }
        return arr;
    }

    /*public Segment partition(Segment[] arr, int left, int right){
        int l = left;
        int current = left;
        int r = right;
        Segment value = arr[left];
        while(current <= r){
            if (arr[current].compareTo(value) < 0){
                Segment temp = arr[current];
                arr[current] = arr[l];
                arr[l] = temp;
                l++;
                current++;}
            else{
                if (arr[current].compareTo(value) == 0)
                    current++;
                else{
                    Segment temp = arr[current];
                    arr[current] = arr[r];
                    arr[r] = temp;
                    r--;
                }

            }
        }
        Segment res = new Segment(l,r);
        return res;
    }*/

    private Segment partition(Segment[] arr, int l, int r) {
        int curr = l;
        Segment x = arr[l];
        while (curr <= r) {
            if (arr[curr].compareTo(x) < 0) {
                swap(arr, curr, l);
                l++;
                curr++;
            } else {
                if (arr[curr].compareTo(x) == 0)
                    curr++;
                else {
                    swap(arr, curr, r);
                    r--;
                }

            }
        }
        Segment m = new Segment(l,r);
        return m;
    }

    private void swap(Segment[] arr, int x, int y) {
        Segment temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }


    public int[] getAccessory2(InputStream stream) throws FileNotFoundException {
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
        quickSort(segments,0, n-1);
        for (int i = 0; i < n; i++) {
            System.out.println(segments[i].start+" "+segments[i].stop);
        }
        for (int i = 0; i < m; i++) {
            System.out.println(points[i]);
        }
        for (int i = 0; i < m; i++){
            int binRes = BinSearch(segments,points[i],0, n - 1);
            System.out.println(binRes);
            if (binRes > -1){
                int count = 1;
                int j = binRes + 1;
                while (j < n && points[i] <= segments[j].stop) {
                    if (segments[j].start <= points[i]) {
                        count++;
                    }
                    j++;
                }
                j = binRes - 1;
                while (j >= 0 && points[i] <= segments[j].stop) {
                    if (segments[j].start <= points[i]) {
                        count++;
                    }
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


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151001/kononova/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
