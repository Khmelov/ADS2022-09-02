package by.it.group151003.harlap.lesson04;

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
    //сортировка слиянием
    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            System.out.println(a[i]);

        }
        mergeSort(a,n-1);
        return a;
    }
        public static void mergeSort(int[] arr,int n){
            int k=n/2;
            if (n < 2) return ;
            int[] leftarr = new int[k];
            int[] rightarr = new int[n-k];

            System.arraycopy(arr, 0, leftarr, 0, k);
            if (n - k >= 0) System.arraycopy(arr, k, rightarr, 0, n - k);
            mergeSort(leftarr,k);
            mergeSort(rightarr,n-k);
            merge(arr,leftarr,rightarr,k,n-k);

    }

        public static void merge(int[] arr,int[] leftarr,int[] rightarr,int left,int right){
        int i=0,j=0,k=0;
        while (i<left && j<right){
            if (leftarr[i]<=rightarr[j])
                arr[k++]=leftarr[i++];
            else arr[k++]=rightarr[j++];
        }
        while (i<left){
            arr[k++]=leftarr[i++];
        }
        while (j<right){
            arr[k++]=rightarr[j++];
        }
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
