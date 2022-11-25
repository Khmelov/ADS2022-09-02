package by.it.group151004.kozyr.lesson04;

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

    void merge(int[] arr, int r, int m, int l){
        int n1 = m - r + 1;
        int n2 = l - m;

        int sArr1[] = new int[n1];
        int sArr2[] = new int[n2];

        for (int i = 0; i < n1; i++)
            sArr1[i] = arr[r + i];
        for (int j = 0; j < n2; j++)
            sArr2[j] = arr[m + 1 + j];

        int i, j, k;
        i = 0;
        j = 0;
        k = r;

        while (i < n1 && j < n2){
            if (sArr1[i] < sArr2[j]) {
                arr[k] = sArr1[i];
                i++;
            } else {
                arr[k] = sArr2[j];
                j++;
            }
            k++;
        }

        while (i < n1){
            arr[k] = sArr1[i];
            i++;
            k++;
        }

        while (j < n2){
            arr[k] = sArr2[j];
            j++;
            k++;
        }
    }
    void mergeSort(int[] arr, int left, int right){
        if (left < right){
            int mid = (left + right)/2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
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
        mergeSort(a, 0, a.length-1);





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
