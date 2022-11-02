package by.it.group151001.kononova.lesson04;

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

    public static void Merge(int[] A, int[] leftA, int[] rightA, int l, int r) {
        int i = 0, j = 0, k = 0;
        while (i < l && j < r) {
            if (leftA[i] <= rightA[j]) {
                A[k] = leftA[i];
                k++;
                i++;
            }
            else {
                A[k] = rightA[j];
                k++;
                j++;
            }
        }
        while (i < l) {
            A[k] = leftA[i];
            k++;
            i++;
        }
        while (j < r) {
            A[k] = rightA[j];
            k++;
            j++;
        }
    }

    public static void MergeSort(int[] A, int n) {
        if (n < 2)
            return;
        int middle = n / 2;
        int[] leftA = new int[middle];
        int[] rightA = new int[n - middle];

        for (int i = 0; i < middle; i++) {
            leftA[i] = A[i];
        }
        for (int i = middle; i < n; i++) {
            rightA[i - middle] = A[i];
        }

        MergeSort(leftA, middle);
        MergeSort(rightA, n - middle);
        Merge(A, leftA, rightA, middle, n - middle);
    }

    public int[] getMergeSort(InputStream stream) throws FileNotFoundException {
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

        MergeSort(a,n);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151001/kononova/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }


}
