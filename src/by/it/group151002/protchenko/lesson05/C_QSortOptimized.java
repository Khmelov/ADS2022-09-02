package by.it.group151002.protchenko.lesson05;

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
            this.start = Math.min(start, stop);
            this.stop = this.start == start ? stop : start;
            //тут вообще-то лучше доделать конструктор на случай если
            //концы отрезков придут в обратном порядке
        }

        @Override
        public int compareTo(Segment o) {
            //подумайте, что должен возвращать компаратор отрезков
            return stop < o.stop ? -1 : (stop == o.stop ? (start < o.start ? -1 : (start == o.start ? 0 : 1)) : 1);
        }
    }
    void swap(Segment[] arr, int i, int j)
    {
        Segment temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    void quickSort(Segment[] arr, int left, int right) {
        while (left < right) {
            int i, j;

            //partition
            if (right - left == 1) {
                if (arr[left].compareTo(arr[right]) > 0)
                    swap(arr, left, right);
                i = left;
                j = right;
            }
            else {
                int mid = left;
                int low = left;
                int high = right;
                Segment pivot = arr[right];
                while(mid <= high){
                    if (arr[mid].compareTo(pivot) < 0)
                        swap(arr, mid++, low++);
                    else if (arr[mid].compareTo(pivot) == 0)
                        mid++;
                    else
                        swap(arr, mid, high--);
                }
                i = low - 1;
                j = mid;
            }
            quickSort(arr, left, i);
            left = j;
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
        quickSort(segments, 0, n - 1);
        for (int i = 0; i < m; i++) {
            int left = 0, right = n - 1, mid = 0;
            while (left <= right) {
                mid = (left + right) / 2;
                if (points[i] > segments[mid].stop) {
                    left = mid + 1;
                } else if (points[i] < segments[mid].start)
                    right = mid - 1;
                else {
                    result[i] = 1;
                    break;
                }
            }
            left = mid - 1;
            while (left >= 0 && points[i] <= segments[left--].stop)
                ++result[i];
            left = mid + 1;
            while (left < n && points[i] >= segments[left++].start)
                ++result[i];

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
