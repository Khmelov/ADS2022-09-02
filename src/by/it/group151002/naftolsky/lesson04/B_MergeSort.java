package by.it.group151002.naftolsky.lesson04;

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
    void merge(int[] arr, int[] leftArr, int[] rightArr) {
        int counter = 0;
        int i = 0;
        int j = 0;
        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] <= rightArr[j]) {
                arr[counter] = leftArr[i];
                counter++;
                i++;
            } else {
                arr[counter] = rightArr[j];
                counter++;
                j++;
            }
        }

        while (i < leftArr.length) {
            arr[counter] = leftArr[i];
            counter++;
            i++;
        }

        while (j < rightArr.length) {
            arr[counter++] = rightArr[j++];
            counter++;
            j++;
        }
    }


    void sort(int[] arr) {
        if(arr.length == 1) {
            return;
        } else {
            int leftSize = arr.length / 2;
            int rightSize = arr.length - leftSize;
            int[] leftArr = new int[leftSize];
            int[] rightArr = new int[rightSize];

            for (int i = 0; i < leftSize; i++) {
                leftArr[i] = arr[i];
            }

            for (int i = leftSize; i < arr.length; i++) {
                rightArr[i - leftSize] = arr[i];
            }

            sort(leftArr);
            sort(rightArr);

            merge(arr, leftArr, rightArr);
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
        InputStream stream = new FileInputStream(root + "by/it/group151002/naftolsky/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }
}