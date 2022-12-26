package by.it.group151003.romanko.lesson05;

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
    private class Segment implements Comparable<Segment> {
        int start;
        int stop;

        Segment(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Segment o) {
            //подумайте, что должен возвращать компаратор отрезков
            return stop < o.stop ? -1 : (stop == o.stop ? (Integer.compare(start, o.start)) : 1);
        }
    }


    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int numOfSegments = scanner.nextInt();
        Segment[] segments = new Segment[numOfSegments];
        //число точек
        int numOfPoints = scanner.nextInt();
        int[] points = new int[numOfPoints];
        int[] result = new int[numOfPoints];

        //читаем сами отрезки
        for (int i = 0; i < numOfSegments; i++) {
            //читаем начало и конец каждого отрезка
            segments[i] = new Segment(scanner.nextInt(), scanner.nextInt());
        }
        //читаем точки
        for (int i = 0; i < numOfPoints; i++) {
            points[i] = scanner.nextInt();
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор
        quickSort(segments, 0, numOfSegments - 1);
        for (int i = 0; i < numOfPoints; i++) {
            int lSide = 0;
            int rSide = numOfSegments - 1;
            int middle = 0;
            while (lSide <= rSide) {
                middle = (lSide + rSide) / 2;
                if (points[i] > segments[middle].stop) {
                    lSide = middle + 1;
                } else if (points[i] < segments[middle].start)
                    rSide = middle - 1;
                else {
                    result[i] = 1;
                    break;
                }
            }
            lSide = middle - 1;
            while (lSide >= 0 && points[i] <= segments[lSide].stop) {
                lSide--;
                result[i]++;
            }
            lSide = middle + 1;
            while (lSide < numOfSegments && points[i] >= segments[lSide].start) {
                lSide++;
                result[i]++;
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    void quickSort(Segment[] arr, int lSide, int rSide) {
        while (lSide < rSide) {
            int i;
            int j;
            if (rSide - lSide == 1) {
                if (arr[lSide].compareTo(arr[rSide]) > 0)
                    swap(arr, lSide, rSide);
                i = lSide;
                j = rSide;
            } else {
                int middle = lSide;
                int low = lSide;
                int high = rSide;
                Segment pivot = arr[rSide];
                while (middle <= high) {
                    if (arr[middle].compareTo(pivot) < 0)
                        swap(arr, middle++, low++);
                    else if (arr[middle].compareTo(pivot) == 0)
                        middle++;
                    else
                        swap(arr, middle, high--);
                }
                i = low - 1;
                j = middle;
            }
            quickSort(arr, lSide, i);
            lSide = j;
        }
    }

    void swap(Segment[] arr, int i, int j) {
        Segment temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result = instance.getAccessory2(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

}
