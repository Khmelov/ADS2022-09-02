package by.it.group151003.raiman.lesson04.lesson04;

import java.io.InputStream;
import java.util.Scanner;

public class A_BinaryFind {
    public int[] findIndex(InputStream stream) {
        Scanner scanner = new Scanner(stream);

        int n = scanner.nextInt();

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        int k = scanner.nextInt();
        int[] result = new int[k];

        for (int i = 0; i < k; i++) {
            result[i] = binarySearch(a, 0, a.length - 1, scanner.nextInt());
        }

        return result;
    }

    private int binarySearch(int[] a, int from, int to, int value) {
        if (from > to) return -1;

        int mid = (from + to) / 2;
        if (a[mid] == value) {
            return mid + 1;
        } else if (a[mid] > value) {
            return binarySearch(a, from, mid - 1, value);
        } else {
            return binarySearch(a, mid + 1, to, value);
        }
    }
}