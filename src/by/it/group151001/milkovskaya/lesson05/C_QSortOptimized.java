package by.it.group151001.milkovskaya.lesson05;

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

    int BinSearch(Segment[] arr, int point){
        int l = 0, r = arr.length - 1;
        while (l <= r){
            int mid = (l+r)/2;
            if(arr[mid].start<=point && arr[mid].stop>=point){
                return mid;
            } else if(arr[mid].start > point){
                r = mid - 1;
            } else l = mid + 1;
        }
        return -1;
    }

    public static void swap(Segment[] a, int pos1, int pos2) {
        if (pos1 != pos2) {
            Segment temp = a[pos1];
            a[pos1] = a[pos2];
            a[pos2] = temp;
        }
    }
    public static int[] partition(Segment[] arr, int left, int right) {
        int i = left, curr = left, j = right;
        Segment value = arr[left];
        while (curr <= j) {
            if(arr[curr].compareTo(value) < 0){
                swap(arr, curr++, i++);
            } else if(arr[curr].compareTo(value) == 0) {
                curr++;
            } else {
                swap(arr, curr, j--);
            }
        }
        return new int[] {i, j};
    }
    public static void Quick(Segment[] arr, int left, int right){
        while (left<right){
            int[] piv = partition(arr, left, right);
            if (piv[0] - left < right - piv[1]) {
                Quick(arr, left, piv[0] - 1);
                left = piv[1] + 1;
            } else {
                Quick(arr, piv[1] + 1, right);
                right = piv[0] - 1;
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
        Quick(segments, 0, n-1);
        for(int i=0; i<m; i++){
            result[i] = 0;
            int bin = BinSearch(segments,points[i]);
                if(bin == -1){
                    break;
                } else {
                    result[i] += 1;
                    while (segments[bin + 1].start < segments[bin].stop) {
                        result[i] += 1;
                        bin +=1;
                    }
                }
            }
            return result;
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!




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
