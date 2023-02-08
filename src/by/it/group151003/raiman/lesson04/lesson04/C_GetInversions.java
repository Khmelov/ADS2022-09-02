package by.it.group151003.raiman.lesson04.lesson04;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class C_GetInversions {
    public int calc(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        return getInversions(a, a.length);
    }

    private int getInversions(int[] array, int n) {
        if (n < 2) return 0;

        int inversions = 0;

        int mid = n / 2;

        int[] left = Arrays.copyOf(array, mid);
        int[] right = Arrays.copyOfRange(array, mid, n);

        inversions += getInversions(left, mid);
        inversions += getInversions(right, n - mid);
        inversions += merge(array, left, right, mid, n - mid);

        return inversions;
    }

    private int merge(int[] array, int[] l, int[] r, int left, int right) {
        int i = 0, j = 0, k = 0, inversions = 0;

        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                array[k++] = l[i++];
            } else {
                array[k++] = r[j++];
                inversions += left - i;
            }
        }
        while (i < left) {
            array[k++] = l[i++];
        }
        while (j < right) {
            array[k++] = r[j++];
        }

        return inversions;
    }
}