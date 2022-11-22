package by.it.group151001.manchik.lesson05;

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

    void swap(Segment Array[], int a, int b){
        Segment temp = Array[a];
        Array[a] = Array[b];
        Array[b] = temp;
    }
    int[] partition(Segment Array[], int l, int r){
        int []res = new int[2];
        int left = l+1, right = r-1, pIndex = left;

        if (Array[l].compareTo(Array[r])>0){
            swap(Array, l ,r);
        }
        Segment tempL = Array[l];
        Segment tempR = Array[r];

        while (pIndex <= right){
            if (Array[pIndex].compareTo(tempL) < 0){
                swap(Array, left, pIndex);
                left++;
            } else {
                if (Array[pIndex].compareTo(tempR) >= 0){
                    while (Array[right].compareTo(tempR) >0 && pIndex<right) right --;
                    swap(Array,pIndex,left); right--;
                    if (Array[pIndex].compareTo(tempL)<0){
                        swap(Array, pIndex,left);
                        left++;
                    }
                }
            }
            pIndex++;
        }
        left--;
        right++;
        swap(Array,l, left);
        swap(Array,r,right);
        res[0]= left;
        res[1]= right;
        return res;
    }
    void QuickSort(Segment Array[], int left, int right){
        if (left < right){
            int []borders = partition(Array, left, right);
            QuickSort(Array, left, borders[0]-1);
            QuickSort(Array,borders[0]+1,borders[1]-1);
            QuickSort(Array, borders[1]+1, right);

        }
    }

    int BinarySearch(Segment A[], int value, int left, int right){
        int l_pos = left, r_pos = right;
        int mid;
        while (l_pos<=r_pos){
            mid = (l_pos+r_pos)/2;
            if (A[mid].start == value) return mid;
            else{
                if (A[mid].start>value) r_pos = mid -1; else l_pos = mid+1;
            }
        }
        return r_pos;
    }

    //отрезок
    private class Segment implements Comparable<Segment> {
        int start;
        int stop;

        Segment(int start, int stop) {
            if (start < stop) {
                this.start = start;
                this.stop = stop;
            } else {
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
        QuickSort(segments, 0, n-1);
        for (int i = 0; i<m; i++){
            for (int j = BinarySearch(segments, points[i],0,n-1); j<n; j++){
                if (segments[j].start <= points[i] && segments[j].stop>=points[i]){
                    result[i]++;
                } else if (segments[j].start > points[i]) break;
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataA.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
