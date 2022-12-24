package by.it.group151003.alamov.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class C_QSortOptimized {

    private class Segment implements Comparable<Segment> {
        int start;
        int stop;

        Segment(int start, int stop) {
            this.start = Math.min(start, stop);
            this.stop = this.start == start ? stop : start;
        }

        @Override
        public int compareTo(Segment o) {
            return stop < o.stop ? -1 : (stop == o.stop ? (Integer.compare(start, o.start)) : 1);
        }
    }

    void swap(Segment[] arr, int i, int j) {
        Segment temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    void quickSort(Segment[] arr, int leftSide, int rightSide) {
        while (leftSide < rightSide) {
            int i;
            int j;

            if (rightSide - leftSide == 1) {
                if (arr[leftSide].compareTo(arr[rightSide]) > 0)
                    swap(arr, leftSide, rightSide);
                i = leftSide;
                j = rightSide;
            } else {
                int middlePoint = leftSide, low = leftSide, high = rightSide;
                Segment pivot = arr[rightSide];

                while (middlePoint <= high) {

                    if (arr[middlePoint].compareTo(pivot) < 0)
                        swap(arr, middlePoint++, low++);
                    else if (arr[middlePoint].compareTo(pivot) == 0)
                        middlePoint++;
                    else
                        swap(arr, middlePoint, high--);
                }

                i = low - 1;
                j = middlePoint;
            }

            quickSort(arr, leftSide, i);
            leftSide = j;
        }
    }

    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        int m = scanner.nextInt();
        int[] arrOfPoints = new int[m];
        int[] res = new int[m];

        for (int i = 0; i < n; i++) {
            segments[i] = new Segment(scanner.nextInt(), scanner.nextInt());
        }
        for (int i = 0; i < m; i++) {
            arrOfPoints[i] = scanner.nextInt();
        }
        quickSort(segments, 0, n - 1);
        for (int i = 0; i < m; i++) {
            int leftSide = 0;
            int rightSide = n - 1;
            int middlePoint = 0;

            while (leftSide <= rightSide) {
                middlePoint = (leftSide + rightSide) / 2;
                if (arrOfPoints[i] > segments[middlePoint].stop) {
                    leftSide = middlePoint + 1;
                } else if (arrOfPoints[i] < segments[middlePoint].start)
                    rightSide = middlePoint - 1;
                else {
                    res[i] = 1;
                    break;
                }
            }

            leftSide = middlePoint - 1;

            while (leftSide >= 0 && arrOfPoints[i] <= segments[leftSide--].stop) ++res[i];

            leftSide = middlePoint + 1;

            while (leftSide < n && arrOfPoints[i] >= segments[leftSide++].start) ++res[i];

        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return res;
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
