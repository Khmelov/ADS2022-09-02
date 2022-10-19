package by.it.group151003.polovoy.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Arrays;

public class B_MergeSort {

    int[] getMergeSort(InputStream stream) {

        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            System.out.print(a[i] + " ");
        }

        System.out.println();

        mergeSort(a, a.length);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }

    public static void mergeSort(int[] Array, int n){

        if (n < 2)
            return;

        int mid = n / 2;
        int[] left = Arrays.copyOf(Array, mid);
        int[] right = Arrays.copyOfRange(Array, mid, n);

        mergeSort(left, mid);
        mergeSort(right, n - mid);
        merge(Array, left, right, mid, n - mid);

    }

    public static void merge(int[] Arr, int[] l, int[] r, int left, int right){

        int i=0, j=0, k=0;

        while (i < left && j < right){

            Arr[k++] = l[i] <= r[j] ? l[i++]: r[j++];
        }

        while (i < left){
            Arr[k++] = l[i++];
        }

        while (j < right){
            Arr[k++] = r[j++];
        }

    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151003/polovoy/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
        System.out.println();
        System.out.println(finishTime - startTime);
    }


}
