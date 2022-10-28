package by.it.group151001.beryozkin.lesson04;

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

    public static void MergeSort(int[] a, int n) {
        if (n < 2) return;
        int mid = n / 2;
        int[] leftarr = new int[mid];
        int[] rightarr = new int[n - mid];

        for (int i = 0; i < mid; i++) leftarr[i] = a[i];
        for (int i = mid; i < n; i++) rightarr[i - mid] = a[i];

        MergeSort(leftarr, mid);
        MergeSort(rightarr, n - mid);
        Merge(a, leftarr, rightarr, mid, n - mid);
    }

    public static void Merge(int[] a, int[] leftarr, int[] rightarr, int left, int right) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (leftarr[i] <= rightarr[j]) {
                a[k] = leftarr[i];
                k++;
                i++;
            }
            else {
                a[k] = rightarr[j];
                k++;
                j++;
            }
        }
        while (i < left) {
            a[k] = leftarr[i];
            k++;
            i++;
        }
        while (j < right) {
            a[k] = rightarr[j];
            k++;
            j++;
        }
    }

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

        MergeSort(a,a.length);

        // тут ваше решение (реализуйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием






        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151001/beryozkin/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }


}
