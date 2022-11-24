package by.it.group151003.mityulya.lesson04;

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

        // тут ваше решение (реализуйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием

        a = sortArray(a);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }

    public int [] sortArray(int[] arrA){

        if (arrA == null) {
            return null;
        }

        if (arrA.length < 2) {
            return arrA;
        }

        int [] arrB = new int[arrA.length / 2];
        System.arraycopy(arrA, 0, arrB, 0, arrA.length / 2);

        int [] arrC = new int[arrA.length - arrA.length / 2];
        System.arraycopy(arrA, arrA.length / 2, arrC, 0, arrA.length - arrA.length / 2);

        arrB = sortArray(arrB);
        arrC = sortArray(arrC);
        return mergeArrays(arrB, arrC);
    }

    public static int[] mergeArrays(int[] a1, int[] a2) {
        int[] res = new int[a1.length + a2.length];
        int n = a1.length;
        int m = a2.length;
        int i = 0, j = 0, k = 0;
        while (i < n && j < m) {
            if (a1[i] <= a2[j]) {
                res[k] = a1[i];
                i++; }
            else {
                res[k] = a2[j];
                j++; }
            k++; }
        while (i < n) {
            res[k] = a1[i];
            i++;
            k++; }
        while (j < m) {
            res[k] = a2[j];
            j++;
            k++; }
        return res;
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
