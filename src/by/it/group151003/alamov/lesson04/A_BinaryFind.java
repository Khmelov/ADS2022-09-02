package by.it.group151003.alamov.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;


public class A_BinaryFind {
    int binarySearch(int[] arr, int leftSide, int rightSide, int value) {
        if (rightSide >= leftSide) {
            int mid = leftSide + (rightSide - leftSide) / 2;

            if (arr[mid] == value)
                return mid;

            if (arr[mid] > value)
                return binarySearch(arr, leftSide, mid - 1, value);

            return binarySearch(arr, mid + 1, rightSide, value);
        }

        return -2;
    }

    int[] findIndex(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 1; i <= n; i++) {
            arr[i - 1] = scanner.nextInt();
        }

        int k = scanner.nextInt();
        int[] result = new int[k];
        int left = 0;
        int right = n - 1;
        for (int i = 0; i < k; i++) {
            int value = scanner.nextInt();
            result[i] = binarySearch(arr, left, right, value) + 1;
        }
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataA.txt");
        A_BinaryFind instance = new A_BinaryFind();
        int[] result = instance.findIndex(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

}
