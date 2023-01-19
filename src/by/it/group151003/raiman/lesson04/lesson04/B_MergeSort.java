package by.it.group151003.raiman.lesson04.lesson04;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class B_MergeSort {
    public int[] getMergeSort(InputStream stream) {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        mergeSort(a, a.length);

        return a;
    }

    public void mergeSort(int[] array, int n) {
        if (n < 2) return;

        int mid = n / 2;

        int[] left = Arrays.copyOf(array, mid);
        int[] right = Arrays.copyOfRange(array, mid, n);

        mergeSort(left, mid);
        mergeSort(right, n - mid);
        merge(array, left, right, mid, n - mid);
    }

    private void merge(int[] array, int[] l, int[] r, int left, int right) {
        int i = 0, j = 0, k = 0;

        while (i < left && j < right) {
            array[k++] = l[i] <= r[j] ? l[i++] : r[j++];
        }
        while (i < left) {
            array[k++] = l[i++];
        }
        while (j < right) {
            array[k++] = r[j++];
        }
    }
}