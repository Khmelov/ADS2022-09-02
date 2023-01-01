package by.it.group151004.matsiushin.lesson04;

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


    public void Merge(int [] arr1, int [] arr2, int [] arr)
    {
        int n1 = arr1.length;
        int n2 = arr2.length;
        int i = 0, j = 0, k = 0;

        while (i < n1 && j < n2)
        {
            if (arr1 [i] < arr2 [j])
            {
                arr [k] = arr1 [i];
                i++;
                k++;
            }
            else
            {
                arr [k] = arr2 [j];
                j++;
                k++;
            }
        }

        for (; i < n1; i++)
        {
            arr [k] = arr1 [i];
            k++;
        }
        for (; j < n2; j++)
        {
            arr [k] = arr1 [j];
            k++;
        }
    }

    public void MergSort(int [] arr)
    {
        int n = arr.length;
        if (n == 1)
            return;
        int half = n / 2;
        int[] arr1 = new int [half];
        int[] arr2 = new int [n - half];

        for (int i = 1; i < half ; i++)
            arr1[i] = arr[i];
        for (int i = half; i < n ; i++)
            arr2[i - half] = arr[i];

        MergSort(arr1);
        MergSort(arr2);

        Merge(arr1, arr2, arr);



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



        MergSort(a);


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
