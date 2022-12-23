package by.it.group151003.alamov.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;


public class A_QSort {

    private class Segment implements Comparable<Segment> {
        int start;
        int stop;

        Segment(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Segment o) {
            return 0;
        }
    }

    void quickSort(Segment[] arr, int leftSide, int rightSide){
        if (leftSide < rightSide) {
            Segment x = arr[leftSide], temp;
            int j = leftSide;
            for (int i = leftSide + 1; i < rightSide; i++) {
                if (arr[i].compareTo(x) < 0) {
                    j++;
                    temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
            temp = arr[j];
            arr[j] = arr[leftSide];
            arr[leftSide] = temp;
            quickSort(arr, leftSide, j - 1);
            quickSort(arr, j + 1, rightSide);
        }
    }


    int[] getAccessory(InputStream stream) throws FileNotFoundException {
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
            int k = 0;
            while(k < n && arrOfPoints[i] > segments[k].stop) k++;
            res[i] = k;
            while(k < n && arrOfPoints[i] >= segments[k].start) k++;
            res[i] = k - res[i];
        }
        return res;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result = instance.getAccessory(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

}
