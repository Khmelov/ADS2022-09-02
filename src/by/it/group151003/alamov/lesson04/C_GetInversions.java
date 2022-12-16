package by.it.group151003.alamov.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class C_GetInversions {

    public static int mergeAndCount(int[] arr, int leftSide,
                                    int m, int rightSide) {

        int[] leftArr = Arrays.copyOfRange(arr, leftSide, m + 1);
        int[] rightArr = Arrays.copyOfRange(arr, m + 1, rightSide + 1);

        int i = 0, j = 0;
        int k = leftSide;
        int swaps = 0;

        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] <= rightArr[j])
                arr[k++] = leftArr[i++];
            else {
                arr[k++] = rightArr[j++];
                swaps += (m + 1) - (leftSide + i);
            }
        }

        while (i < leftArr.length)
            arr[k++] = leftArr[i++];

        while (j < rightArr.length)
            arr[k++] = rightArr[j++];

        return swaps;
    }


    public static int mergeSortAndCount(int[] arr, int leftSide, int rightSide) {
        int i = 0;
        if (leftSide < rightSide) {
            int m = (leftSide + rightSide) / 2;
            i += mergeSortAndCount(arr, leftSide, m);
            i += mergeSortAndCount(arr, m + 1, rightSide);
            i += mergeAndCount(arr, leftSide, m, rightSide);
        }
        return i;
    }

    int calc(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        int result = 0;
        result = mergeSortAndCount(arr, 0, arr.length - 1);
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        //long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        //long finishTime = System.currentTimeMillis();
        System.out.print(result);
    }
}
