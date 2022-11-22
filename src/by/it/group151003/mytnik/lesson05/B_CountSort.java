package by.it.group151003.mytnik.lesson05;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class B_CountSort {
    public int[] countSort(InputStream stream) {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int[] points = new int[n];

        for (int i = 0; i < n; i++) {
            points[i] = scanner.nextInt();
        }

        countSort(points, 1, 10);
        return points;
    }

    private void countSort(int[] array, int min, int max) {
        int[] count = new int[max - min + 1];
        Arrays.stream(array).forEach(value -> count[value - min]++);
        int index = 0;
        for (int i = 0; i < count.length; i++) {
            for (int j = 0; j < count[i]; j++) {
                array[index++] = i + min;
            }
        }
    }
}
