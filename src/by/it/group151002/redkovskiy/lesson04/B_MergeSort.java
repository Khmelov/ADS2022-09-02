package by.it.group151002.redkovskiy.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class B_MergeSort {

    private void mergingProcess(int[] arr, int[] leftArr, int[] rightArray, int left, int right) {
        int pos = 0, i = 0, j = 0;
        while (pos < left && i < right) {
            if (leftArr[pos] <= rightArray[i])
                arr[j++] = leftArr[pos++];
            else
                arr[j++] = rightArray[i++];
        }
        while (pos < left)
            arr[j++] = leftArr[pos++];
        while (i < right)
            arr[j++] = rightArray[i++];
    }

    private void splitArray(int[] arr, int n) {
        if (n == 1)
            return;
        int middleIndex = n / 2;
        int[] leftArr = new int[middleIndex];
        int[] rightArr = new int[n - middleIndex];

        //left array is from 0 to middleIndex - 1
        System.arraycopy(arr, 0, leftArr, 0, middleIndex);

        //right array is from middleIndex to n - 1
        System.arraycopy(arr, middleIndex, rightArr, 0, n - middleIndex);

        splitArray(leftArr, middleIndex);
        splitArray(rightArr, n - middleIndex);

        mergingProcess(arr, leftArr, rightArr, middleIndex, n - middleIndex);
    }

    //public for testing
    public int[] getMergeSort(InputStream stream) {
        Scanner scanner = new Scanner(stream);
        int size = scanner.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
            System.out.print(arr[i] + " ");
        }
        System.out.println("\n");
        splitArray(arr, size);

        return arr;
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/redkovskiy/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        int[] result = instance.getMergeSort(stream);
        for (int index:result)
            System.out.print(index + " ");
    }
}