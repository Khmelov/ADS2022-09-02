package by.it.group151001.hlebanova.lesson05;

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
        for (int i = 0; i < m; i++){
            int found = BinarySearch(segments,points[i],0, n - 1);
            if (found > -1){
                int count = 1;
                int k = found + 1;
                while(k<n && segments[k].stop > points[i]){
                    if(segments[k].start <= points[i]){
                        count++;
                    }
                    k++;
                }
                k = found - 1;
                while (k >= 0 && segments[k].stop > points[i]){
                    if (segments[k].start <= points[i])
                        count++;
                    k--;
                }
                result[i] = count;
            }
            else
                result[i] = 0;
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }
    int[] Partition(Segment[] A, int l, int r){
        Segment x = A[l];
        int[] res = new  int[2];
        int j = l;
        res[0] = l;
        res[1] = l;
        for(int i = l+1; i<=r;i++){
            if(A[i].compareTo(x)==0){
                j++;
                res[1]++; // подсчет количества равных опорному(индексы отн начала)
                Segment temp = A[res[1]];
                A[res[1]] = A[i];
                A[i] = temp;

                temp = A[j];
                A[j] = A[i];
                A[i] = temp;
            } else
            if(A[i].compareTo(x)<0){
                j++;
                Segment temp = A[j];
                A[j] = A[i];
                A[i] = temp;
            }
        } // расставили опорный, равные, меньше, больше j - последний меньше
        int size = res[1] - res[0] + 1; // кол-во равных
        res[0] = j + 1 - size;   // будущая левая позиц равных
        res[1] = j;             // правая
        int q = size - 1;  // позиция левого равного
        for (int i = res[1]; i >= res[0]; i--){
            Segment tmp = A[l+q];
            A[l+q] = A[i];
            A[i] = tmp;
            q--;
        }
        return res;
    }
    void QuickSort(Segment[] A, int l, int r){
        while(l<r){
            int [] m = Partition(A,l,r);
            QuickSort(A,l,m[0]-1);
            l = m[1] + 1;
        }
    }
    int BinarySearch(Segment[] A, int x, int l,int r){
        boolean found = false;
        while (l <= r && found != true){
            int mid = (l + r)/2;
            if (A[mid].start > x)
                r = mid - 1;
            else{
                if (A[mid].stop < x)
                    l = mid + 1;
                else{
                    found = true;
                    return mid;
                }
            }
        }
        return -1;
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
