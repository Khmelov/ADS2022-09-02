package by.it.group151004.ermolovich.lesson04;

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
    private static void sort(int[] arr)
    {
        int n = arr.length;
        if (n == 1) return;
        int mid = n/2 ;
        int[] arr1 = new int[mid];
        int[] arr2 = new int[n - mid];

        for(int i = 0; i < mid; i++)
            arr1[i] = arr[i];
        for(int i = mid; i < n; i++)
            arr2[i - mid] = arr[i];
        sort(arr1);
        sort(arr2);
        ArrMerge(arr1, arr2, arr);

    }

    private static void ArrMerge(int[] arr1,int[] arr2, int[] arr)
    {
        int len1 = arr1.length;
        int len2 = arr2.length;
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < len1 && j < len2)
        {
            if(arr1[i] < arr2[j])
            {
                arr[k] = arr1[i];
                k++;
                i++;
            } else
            {
                arr[k] = arr2[j];
                k++;
                j++;
            }
        }

        for(; i < len1; i++)
        {
            arr[k] = arr1[i];
        }
        for(; j < len2; j++)
        {
            arr[k] = arr2[j];
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
        sort(a);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
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