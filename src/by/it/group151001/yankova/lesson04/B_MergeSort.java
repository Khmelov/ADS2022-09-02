package by.it.group151001.yankova.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.Inet4Address;
import java.util.ArrayList;
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

        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            System.out.println(a[i]);
        }
        MergeSort(a, 0, n);
        return a;
    }

    private void MergeSort(int[] a, int l, int r){
        if(l + 1 >= r)
            return;
        int mid = (l + r)/2;
        MergeSort(a, l, mid);
        MergeSort(a, mid, r);
        MergeArrays(a, l, mid, r);
    }

    private void MergeArrays(int[] a, int l, int mid, int r){
        int[] buffer = new int[r - l];
        int b1 = 0, b2 = 0;
        while(b1 + b2 != r - l){
            if((b1>=mid-l) || (b2<r-mid && a[b2+mid]<=a[b1+l])){
                buffer[b1+b2] = a[b2+mid];
                b2++;
            }else{
                buffer[b1+b2] = a[b1+l];
                b1++;
            }
        }
        if (r - l >= 0) System.arraycopy(buffer, 0, a, l, r - l);
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151001/yankova/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }


}
