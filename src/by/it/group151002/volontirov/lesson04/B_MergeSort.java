package by.it.group151002.volontirov.lesson04;

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
    int[] merge_sort(int[] arr){
        if(arr.length == 1) return arr;
        int[] arr1 = merge_sort(Arrays.copyOfRange(arr, 0, arr.length / 2));
        int[] arr2 = merge_sort(Arrays.copyOfRange(arr, arr.length / 2, arr.length));
        int[] arr3 = new int[arr1.length + arr2.length];
        int l1 = 0;
        int l2 = 0;
        int index = 0;
        while(l1 < arr1.length || l2 < arr2.length){
            if(l1 < arr1.length && l2 < arr2.length && arr1[l1] <= arr2[l2]){
                arr3[index] = arr1[l1];
                l1++;
            } else {
                if(l1 == arr1.length) {
                    arr3[index] = arr2[l2];
                    l2++;
                } else {
                    if(l2 == arr2.length) {
                        arr3[index] = arr1[l1];
                        l1++;
                    } else {
                        arr3[index] = arr2[l2];
                        l2++;
                    }
                }
            }
            index++;
        }
        return arr3;
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
        a = merge_sort(a);
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
