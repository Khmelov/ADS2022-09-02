package by.it.group151003.stoyanov.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
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

    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] a=new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            System.out.println(a[i]);
        }
        mergeSort(a);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }

    private void mergeSort(int[] a) {
        sort(a, 0, a.length - 1);
    }

    private void sort(int[] a, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            sort(a, l, m);
            sort(a, m + 1, r);
            merge(a, l, m, r);
        }
    }

    private void merge(int[] a, int l, int m, int r) {
        int leftLength = m - l + 1;
        int rightLength = r - m;
        int[] leftArray = new int[leftLength];
        int[] rightArray = new int[rightLength];

        for (int i = 0; i < leftLength; i++)
            leftArray[i] = a[i + l];
        for (int i = 0; i < rightLength; i++)
            rightArray[i] = a[i + m + 1];

        int rArrIndex = 0;
        int lArrIndex = 0;
        int arrIndex = l;
        while (lArrIndex < leftLength && rArrIndex < rightLength) {
            if (leftArray[lArrIndex] < rightArray[rArrIndex])
                a[arrIndex] = leftArray[lArrIndex++];
            else
                a[arrIndex] = rightArray[rArrIndex++];
            arrIndex++;
        }

        while (lArrIndex < leftLength)
            a[arrIndex++] = leftArray[lArrIndex++];
        while (rArrIndex < rightLength)
            a[arrIndex++] = rightArray[rArrIndex++];
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151003/stoyanov/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);

        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }


}
