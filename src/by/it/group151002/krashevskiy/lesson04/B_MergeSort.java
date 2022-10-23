package by.it.group151002.krashevskiy.lesson04;

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

    private static int[] mergeSort(int[] arr, int[] buffer, final int left, final int right) {
        if (left == right) {
            buffer[left] = arr[left];
            return buffer;
        }
        int middle = (left + right) / 2;

        int[] leftBuff = mergeSort(arr, buffer, left, middle);
        int[] rightBuff = mergeSort(arr, buffer, middle + 1, right);

        int[] merged;
        if (leftBuff.equals(arr))
            merged = buffer;
        else
            merged = arr;

        int leftCur = left;
        int rightCur = middle + 1;

        for (int i = left; i <= right; i++) {
            if (leftCur <= middle && rightCur <= right) {
                if (leftBuff[leftCur] < rightBuff[rightCur]) {
                    merged[i] = leftBuff[leftCur];
                    leftCur++;
                } else {
                    merged[i] = rightBuff[rightCur];
                    rightCur++;
                }
            } else if (leftCur <= middle) {
                merged[i] = leftBuff[leftCur];
                leftCur++;
            } else {
                merged[i] = rightBuff[rightCur];
                rightCur++;
            }
        }

        return merged;
    }
    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            System.out.println(a[i]);
        }

        a = mergeSort(a, new int[a.length],0, a.length - 1);

        return a;
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/krashevskiy/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }


}
