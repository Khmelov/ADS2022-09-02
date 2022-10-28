package by.it.group151003.polovoy.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class C_QSortOptimized {

    //отрезок
    private static class Segment  implements Comparable<Segment>{
        int start;
        int stop;

        Segment(int start, int stop){
            if (start > stop){
                this.start = stop;
                this.stop = start;
            }
            else {
                this.start = start;
                this.stop = stop;
            }
        }

        @Override
        public int compareTo(Segment o) {
            return Integer.compare(start, o.start);
        }
    }


    int[] getAccessory2(InputStream stream) {

        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];

        int m = scanner.nextInt();
        int[] points = new int[m];
        int[] result = new int[m];

        for (int i = 0; i < n; i++) {

            segments[i]= new Segment(scanner.nextInt(), scanner.nextInt());
        }

        for (int i = 0; i < m; i++) {
            points[i]=scanner.nextInt();
        }

        QSort(segments, 0, segments.length-1);
        for (int i = 0; i < points.length; i++) {
            int ind = binarysearch(segments, points[i]);
            int counter = 0;
            int j = ind;
            while (j >= 0 && segments[j].start <= points[i]){
                if (segments[j].stop >= points[i]){
                    counter++;
                }
                j--;
            }
            result[i] = counter;
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    public int binarysearch(Segment[] Segments, int point){
        int l = 0;
        int r = Segments.length-1;

        while (l < r){
            if (Segments[(l+r+1) / 2].start > point){

                r -= (r - l + 1) / 2;
            }
            else {

                l += (r - l + 1) / 2;
            }
        }

        return l;
    }
    public static void QSort (Segment[] A, int left, int right){

        if (left >= right) return;

        int i = left, j = right;
        Segment mid = A[left + (right - left) / 2];

        while (i <= j){
            while (A[i].compareTo(mid) < 0){
                i++;
            }
            while (A[j].compareTo(mid) > 0){
                j--;
            }
            if(i <= j){
                swap(A[i], A[j]);
                i++;
                j--;
            }
        }

        if (left < j){
            QSort(A, left, j);
        }
        if (right > i){
            QSort(A, i, right);
        }
    }

    public static void swap(Segment a, Segment b){

        Segment temp = a;
        a = b;
        b = temp;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151003/polovoy/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
