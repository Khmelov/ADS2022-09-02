package by.it.group151002.ravodin.lesson04;

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


    void MergeSort(int arr[]){
        Divide(arr,0 ,arr.length - 1);
    }
    void Divide(int arr[], int leftIndex, int rightIndex)
    {
        if (leftIndex < rightIndex) {
            int midIndex = leftIndex + (rightIndex - leftIndex) / 2;
            Divide(arr, leftIndex, midIndex);
            Divide(arr, midIndex + 1, rightIndex);
            merge(arr, leftIndex, midIndex, rightIndex);
        }
    }

    void merge(int arr[], int leftIndex, int midIndex, int rightIndex)
    {
        int leftLength = midIndex - leftIndex + 1;
        int rightLength = rightIndex - midIndex;
        int leftPart[] = new int[leftLength];
        int rightPart[] = new int[rightLength];
        for (int i = 0; i < leftLength; ++i)
            leftPart[i] = arr[leftIndex + i];
        for (int j = 0; j < rightLength; ++j)
            rightPart[j] = arr[midIndex + 1 + j];
        int i = 0, j = 0;
        int k = leftIndex;
        while (i < leftLength && j < rightLength) {
            if (leftPart[i] <= rightPart[j]) {
                arr[k] = leftPart[i];
                i++;
            }
            else {
                arr[k] = rightPart[j];
                j++;
            }
            k++;
        }
        while (i < leftLength) {
            arr[k] = leftPart[i];
            i++;
            k++;
        }
        while (j < rightLength) {
            arr[k] = rightPart[j];
            j++;
            k++;
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
        MergeSort(a);

        // тут ваше решение (реализуйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием


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
