package by.it.group151001.skripskaya.lesson04;

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

    void merge(int[] arr, int left, int mid, int right) {
        int size1 = mid - left + 1;
        int size2 = right - mid;

        int[] a1 = new int[size1];
        int[] a2 = new int[size2];

        for (int i=0; i<size1; ++i)
            a1[i] = arr[left + i];
        for (int j=0; j<size2; ++j)
            a2[j] = arr[mid + 1+ j];

        int i = 0, j = 0;
        int k = left;
        while (i < size1 && j < size2) {
            if (a1[i] <= a2[j])
            {
                arr[k] = a1[i];
                i++;
            }
            else
            {
                arr[k] = a2[j];
                j++;
            }
            k++;
        }
        /* Copy remaining elements of L[] if any */
        while (i < size1) {
            arr[k] = a1[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < size2) {
            arr[k] = a2[j];
            j++;
            k++;
        }
    }
    void sort(int arr[], int l, int r) {
        if (l < r)
        {
            // Find the middle point
            int m = (l+r)/2;

            // Sort first and second halves
            sort(arr, l, m);
            sort(arr , m+1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
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

        // тут ваше решение (реализуйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием
        sort(a,0, n-1);





        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151001/skripskaya/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }


}
