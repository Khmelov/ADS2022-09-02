package by.it.group151002.kragel.lesson04;

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

    int[] merge(int[] a1, int[] a2){
        int[] res = new int[a1.length + a2.length];
        int lim = a1.length <= a2.length ? a1.length : a2.length;
        int i = 0, j = 0;
        while(i < lim && j < lim){
            if (a1[i] <= a2[j])
                res[i + j] = a1[i++];
            else
                res[i + j] = a2[j++];
        }
        while(i < a1.length)
            res[i+j] = a1[i++];
        while(j < a2.length)
            res[i+j] = a2[j++];
        return res;
    }
    int[] mergeSort(int[] arr, int l, int r){
        if (l < r){
            int m = (l + r) / 2;
            return merge(mergeSort(arr, l, m), mergeSort(arr, m + 1, r));
        }
        return new int[]{arr[l]};
    }
    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] a=new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            System.out.println(a[i]);
        }
        return mergeSort(a, 0, n - 1);
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/kragel/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }


}
