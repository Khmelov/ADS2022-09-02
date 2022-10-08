package by.it.group151002.haiduk.lesson04;

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
    public void merge(int[] a, int left, int middle, int right) {
        int lLength = middle - left + 1;
        int rLength = right - middle;
        int[] l = new int[lLength];
        int[] r = new int[rLength];
        for (int i = 0; i < lLength; i++) {
            l[i] = a[left + i];
        }
        for (int j = 0; j < rLength; j++) {
            r[j] = a[middle + 1 + j];
        }
        int i = 0;
        int j = 0;
        int k = left;
        while (i < lLength && j < rLength)
            if (l[i] < r[j])
                a[k++] = l[i++];
            else
                a[k++] = r[j++];
        while (i < lLength)
            a[k++] = l[i++];
        while (j < rLength)
            a[k++] = r[j++];
    }

    public void mergeSort(int[] a, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(a, left, mid);
            mergeSort(a, mid + 1, right);
            merge(a, left, mid, right);
        }
    }

    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            System.out.println(a[i]);
        }
        mergeSort(a, 0, n -1);
        return a;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        int[] result=instance.getMergeSort(stream);
        for (int index:result)
            System.out.print(index+" ");
    }
}
