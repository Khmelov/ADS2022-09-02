package by.it.group151003.harlap.lesson05;

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
        public int compareTo(Segment o) {return this.stop - o.stop;
            //подумайте, что должен возвращать компаратор отрезков

        }
    }

    public static void Swap(Segment[]s,int i,int j){
        Segment temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }

    public static int BinaryFind(Segment[] Arr, int value, int l, int r) {

        while ((l <= r)) {
            int m = (l + r) / 2;
            if ((Arr[m].start > value)) {
                r = m - 1;
            } else if (Arr[m].stop < value) l = m + 1;
            else return m;
        }
        return -1;
    }

    public Segment Patrition(Segment[] array, int start, int end){
        int curr=start,i=start,j=end;
        Segment value =array[start];

        while(curr<= j) {

            if(array[curr].compareTo(value) > 0)
                Swap(array,curr,j--);
            else if(array[curr].compareTo(value) < 0)
                Swap(array,curr++,i++);
            else curr++;
        }
        return new Segment(i,j);
    }

    public Segment[] quickSort(Segment[] array, int l, int r) {
        while(l<r){
            Segment center = Patrition(array,l,r);
            quickSort(array,l,center.start-1);
            l=center.stop+1;
        }
        return array;
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
        quickSort(segments,0, n-1);
        for (int i = 0; i < m; i++) {
            result[i]=0;
            int temp = BinaryFind(segments, points[i], 0, n-1);
            if(temp==-1) break;
            else{
                result[i]+=1;
                while(segments[temp+1].start < segments[temp].stop){
                    result[i]+=1;
                    temp+=1;
                }
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