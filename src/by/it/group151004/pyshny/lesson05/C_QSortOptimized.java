package by.it.group151004.pyshny.lesson05;

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
            if (start <= stop) {
                this.start = start;
                this.stop = stop;
            } else {
                this.start = stop;
                this.stop = start;
            }
            //тут вообще-то лучше доделать конструктор на случай если
            //концы отрезков придут в обратном порядке
        }

        @Override
        public int compareTo(Segment o) {
            //подумайте, что должен возвращать компаратор отрезков
            return Integer.compare(start, o.start);
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
        qs(segments,0,segments.length-1);
        for (int i = 0; i < points.length; i++) {
            int index = bins(segments, points[i]);
            if (index == -1) {
                result[i] = 0;
            } else {
                int count = 1;
                int j = index - 1;
                while (j >= 0 && segments[j].start == segments[index].start) {
                    count++;
                    j--;
                }
                j = index + 1;
                while (j < segments.length && segments[j].start == segments[index].start) {
                    count++;
                    j++;
                }
                result[i] = count;
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }
    int bins(Segment[] segments,int point){
        int l = 0;
        int r = segments.length - 1;
        int mid = 0;
        while (l <= r) {
            mid = (l + r) / 2;
            if (segments[mid].start <= point && segments[mid].stop >= point) {
                return mid;
            }
            if (segments[mid].start > point) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }

    void qs(Segment segments[], int l, int r){
        while (l<r){
            int[] x = divide(segments,l,r);
            if (x[0]-l<r-x[1]){
                qs(segments,l,x[0]-1);
                l=x[1]+1;
            } else {
                qs(segments,x[1]+1,r);
                r=x[0]-1;
            }
        }
    }
    int[] divide(Segment[] segments, int l, int r){
        int num = l + (int)(Math.random()*(r-l+1));
        int numval = segments[num].start;
        int i=l,j=r,m=l;
        while (m<=j){
            if (segments[m].start<numval){
                Segment tmp=segments[i];
                segments[i]=segments[m];
                segments[m]=tmp;
                i++;
                m++;
            } else if (segments[m].start>numval) {
                Segment tmp = segments[m];
                segments[m] = segments[j];
                segments[j] = tmp;
                j--;
            } else {m++;}
        }
        return new int[] {i,j};
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
