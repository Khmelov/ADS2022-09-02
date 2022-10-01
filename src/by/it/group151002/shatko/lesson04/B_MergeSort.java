package by.it.group151002.shatko.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class B_MergeSort {

    void merge(int[] arr, int[] l, int[] r, int left, int right) {
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                arr[k++] = l[i++];
            } else {
                arr[k++] = r[j++];
            }
        }

        while (i < left) {
            arr[k++] = l[i++];
        }

        while (j < right) {
            arr[k++] = r[j++];
        }
    }

    void mergeSort(int[] arr, int n) {
        if (n == 1) return;
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = arr[i];
        }

        for (int i = mid; i < n; i++) {
            r[i - mid] = arr[i];
        }

        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(arr, l, r, mid, n - mid);
    }

    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
            System.out.print(arr[i] + " ");
        }
        System.out.println("\n");
        mergeSort(arr, n);

        return arr;
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/shatko/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        int[] result = instance.getMergeSort(stream);
        for (int index:result){
            System.out.print(index + " ");
        }
    }


}
