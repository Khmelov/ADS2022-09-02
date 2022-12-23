package by.it.group151001.baran.lesson04;

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

    private static void merge(int arr[], int left,int middle, int right){

        int lenL = middle - left + 1;
        int lenR = right - middle;

        int[]R = new int[lenR];
        int[]L = new int[lenL];

        for (int i = 0; i < lenL; ++i)
            L[i] = arr[left + i];
        for (int j = 0; j < lenR; ++j)
            R[j] = arr[middle + 1 + j];

        int i = 0, j = 0, idx = left;
        while(i < lenL && j < lenR){
            if(L[i] <= R[j]) {
                arr[idx] = L[i];
                i++;
            }
            else {
                arr[idx] = R[j];
                j++;
            }
            idx++;
        }
        while(i < lenL){
            arr[idx] = L[i];
            idx++;
            i++;
        }
        while(j < lenR){
            arr[idx] = R[j];
            idx++;
            j++;
        }
    }
    private static void mergeSort(int arr[],int left ,int right){
        if(left < right) {
            int middle = left + (right - left) / 2;

            mergeSort(arr, left, middle);
            mergeSort(arr, middle + 1, right);

            merge(arr, left, middle, right);
        }

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

        mergeSort(a, 0, a.length - 1);
        return a;
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }


}
