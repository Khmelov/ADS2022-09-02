package by.it.group151002.mozol.lesson04;

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
        a = sortArr(a);

        // тут ваше решение (реализуйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }

    static int findMin(int fir, int sec) {
        if (fir < sec) {
            return fir;
        } else {
            return sec;
        }
    }
    static int[] sortArr(int[] arr) {
        for (int i = 1; i < arr.length; i *= 2) {
            for (int j = 0; j < arr.length - i; j += i * 2) {
                arr = mergeArr(j, j + i, findMin(j + 2 * i, arr.length), arr);
            }
        }
        return arr;
    }
    static int[] mergeArr(int left, int mid, int right,  int[] arr) {
        int iter1 = 0;
        int iter2 = 0;
        int[] res = new int[right - left];
        while ((iter1 + left < mid) && (iter2 + mid < right)) {
            if (arr[left + iter1] < arr[mid + iter2]) {
                res[iter1 + iter2] = arr[left + iter1];
                iter1++;
            } else {
                res[iter1 + iter2] = arr[mid + iter2];
                iter2++;
            }
        }
        while (left + iter1 < mid) {
            res[iter1 + iter2] = arr[left + iter1];
            iter1++;
        }
        while (mid + iter2 < right) {
            res[iter1 + iter2] = arr[mid + iter2];
            iter2++;
        }
        for (int i = 0; i < iter1 + iter2; i++) {
            arr[left + i] = res[i];
        }
        return arr;
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
