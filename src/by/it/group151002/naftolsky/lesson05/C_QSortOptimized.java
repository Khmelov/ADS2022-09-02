package by.it.group151002.naftolsky.lesson05;

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
            if (stop < o.stop) {
                return -1;
            }
            else if (stop == o.stop) {
                if (start < o.start) {
                    return -1;
                }
                else if (start == o.start) {
                    return 0;
                }
                else {
                    return 1;
                }
            }
            else {
                return 1;
            }
        }
    }

    void swapSegment(Segment[] arr, int i, int j)
    {
        Segment tempSegment = arr[i];
        arr[i] = arr[j];
        arr[j] = tempSegment;
    }

    void quickSort(Segment[] arrSegment, int leftIndex, int rightIndex){
        while (leftIndex < rightIndex) {
            int i, j;
            //partition
            if (rightIndex - leftIndex == 1){
                if (arrSegment[leftIndex].compareTo(arrSegment[rightIndex]) > 0)
                    swapSegment(arrSegment, leftIndex, rightIndex);
                i = leftIndex;
                j = rightIndex;
            }
            else{
                int partition = leftIndex;
                int lowIndex = leftIndex;
                int highIndex = rightIndex;

                Segment pivotSegment = arrSegment[rightIndex];

                while(partition <= highIndex){
                    if (arrSegment[partition].compareTo(pivotSegment) < 0) {
                        swapSegment(arrSegment, partition, lowIndex);
                        partition++;
                        lowIndex++;
                    }
                    else if (arrSegment[partition].compareTo(pivotSegment) == 0) {
                        partition++;
                    }
                    else {
                        swapSegment(arrSegment, partition, highIndex);
                        highIndex--;
                    }
                }

                i = lowIndex - 1;
                j = partition;
            }

            quickSort(arrSegment, leftIndex, i);

            leftIndex = j;
        }
    }

    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);

        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        //число точек
        int m = scanner.nextInt();
        int[] points = new int[m];
        int[] result = new int[m];

        //читаем сами отрезки
        for (int i = 0; i < n; i++) {
            segments[i] = new Segment(scanner.nextInt(),scanner.nextInt());
        }
        //читаем точки
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор
        quickSort(segments, 0, n - 1);

        int leftIndex;
        int rightIndex;
        int partition;

        for (int i = 0; i < m; i++) {
            leftIndex = 0;
            rightIndex = n - 1;
            partition = 0;

            while (leftIndex <= rightIndex) {
                partition = (leftIndex + rightIndex) / 2;
                if (points[i] > segments[partition].stop) {
                    leftIndex = partition + 1;
                } else if (points[i] < segments[partition].start) {
                    rightIndex = partition - 1;
                } else {
                    result[i] = 1;
                    break;
                }
            }

            leftIndex = partition - 1;
            while(leftIndex >= 0 && points[i] <= segments[leftIndex].stop) {
                result[i]++;
                leftIndex--;
            }

            leftIndex = partition + 1;
            while(leftIndex < n && points[i] >= segments[leftIndex].start) {
                result[i]++;
                leftIndex++;
            }

        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/naftolsky/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }
}
