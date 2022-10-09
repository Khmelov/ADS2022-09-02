package by.it.group151002.zavaliuk.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Реализуйте сортировку слиянием для одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо отсортировать полученный массив.

Sample Input:
5
2 3 9 2 9
Sample Output:
2 2 3 9 9
*/
public class B_MergeSort {

    void merge(int[] arr, int leftSide, int m, int rightSide) {
        int rightSideSize = rightSide - m;
        int leftSideSize = m - leftSide + 1;

        int[] rightSubArray = new int[rightSideSize];
        int[] leftSubArray = new int[leftSideSize];

        System.arraycopy(arr, leftSide, leftSubArray, 0, leftSideSize);
        for (int j = 0; j < rightSideSize; ++j)
            rightSubArray[j] = arr[m + 1 + j];

        int i = 0, j = 0;

        int k = leftSide;
        while (i < leftSideSize && j < rightSideSize) {
            if (leftSubArray[i] <= rightSubArray[j]) {
                arr[k] = leftSubArray[i];
                i++;
            } else {
                arr[k] = rightSubArray[j];
                j++;
            }
            k++;
        }

        while (j < rightSideSize) {
            arr[k] = rightSubArray[j];
            j++;
            k++;
        }

        while (i < leftSideSize) {
            arr[k] = leftSubArray[i];
            i++;
            k++;
        }
    }

    void sort(int[] arr, int leftSide, int rightSide) {
        if (leftSide < rightSide) {
            int middlePoint = (leftSide + rightSide) / 2;

            sort(arr, leftSide, middlePoint);
            sort(arr, middlePoint + 1, rightSide);

            merge(arr, leftSide, middlePoint, rightSide);
        }
    }

    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
            System.out.println(arr[i]);
        }

        B_MergeSort ob = new B_MergeSort();
        ob.sort(arr, 0, arr.length - 1);

        // тут ваше решение (реализ
        // уйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием


        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return arr;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result = instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index : result) {
            System.out.print(index + " ");
        }
    }


}
