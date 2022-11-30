package by.it.group151004.buhovets.lesson05;

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
    private class Segment  implements Comparable{
        int start;
        int stop;

        Segment(int start, int stop){
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Object o) {
            //подумайте, что должен возвращать компаратор отрезков
            return 0;
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

        qSort(segments,0, segments.length-1);
        for (int i = 0; i < m; i++) {
            for (int j=0;j<BinarySearch(segments,points[i]);j++) {
                if((points[i]>=segments[j].start)&&(points[i]<=segments[j].stop)){
                    result[i]++;
                }
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    public int BinarySearch(Segment a[], int value){
        int l=0,
                r=a.length-1,
                m ;
        while(l<=r){
            m=(l+r)/2;
            if ( a[m].start > value) {
                if (m==0 || a[m - 1].start <= value) {
                    return m;
                }
                else
                    r=m-1;
            }
            else
                l=m+1;
        }

        return -1;
    }

    public void swap(Segment[] a, int i, int j){
        Segment temp=a[i];
        a[i]=a[j];
        a[j]=temp;
    }
    public void qSort(Segment[] a, int left, int right){
        if(left>=right) return;
        int pivot=a[right].start;
        int lt=left;
        int rt=right;
        int i=left+1;
        while(i<=rt){
            if(a[i].start<pivot){
                swap(a, i++,lt++);
            }
            else if(a[i].start>pivot){
                swap(a,i,rt--);
            }
            else
                i++;
        }

        qSort(a, left, lt-1);
        qSort(a,rt+1,right);
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151004/buhovets/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}