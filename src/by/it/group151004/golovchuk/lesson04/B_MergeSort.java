package by.it.group151004.golovchuk.lesson04;

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

    private static void mergeSort(int[] arr)
    {
        int n = arr.length;
        if (n == 1) return;

        int mid = n/2 ;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for(int i = 0; i < mid; i++)
            l[i] = arr[i];
        for(int i = mid; i < n; i++)
            r[i - mid] = arr[i];
        mergeSort(l);
        mergeSort(r);
        merge(arr, l, r);

    }

    private static void merge(int[] arr,int[] l,int[] r)
    {
        int left = l.length;
        int right = r.length;
        int i = 0;
        int j = 0;
        int index = 0;


        while (i < left && j < right)
        {
            if(l[i] < r[j])
            {
                arr[index] = l[i];
                index++;
                i++;
            } else
            {
                arr[index] = r[j];
                index++;
                j++;
            }
        }

        for(int ll = i; ll < left; ll++)
        {
            arr[index++] = l[ll];
        }
        for(int rr = j; rr < right; rr++)
        {
            arr[index++] = r[rr];
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

        mergeSort(a);
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
