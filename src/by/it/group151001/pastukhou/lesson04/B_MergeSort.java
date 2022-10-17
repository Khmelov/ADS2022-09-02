package by.it.group151001.pastukhou.lesson04;

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

    int[] mergeSort(int[] a, int n) {
        if (n == 1) return a;
        int mid = n / 2;
        int b[] = new int[mid];
        for (int i = 0; i < mid; i++) {
            b[i] = a[i];
        }
        int c[] = new int[n - mid];
        for (int i = mid; i < n; i++) {
            c[i - mid] = a[i];
        }
        b = mergeSort(b, mid);
        c = mergeSort(c, n - mid);
        int[] res = new int[n];
        int i = 0, bi = 0, ci = 0;
        while (bi < mid && ci < n - mid) {
            if (b[bi] <= c[ci]) {
                res[i] = b[bi];
                bi++;
            } else {
                res[i] = c[ci];
                ci++;
            }
            i++;
        }
        if (bi == mid) {
            while (ci < n - mid) {
                res[i] = c[ci];
                ci++;
                i++;
            }
        } else {
            while (bi < mid) {
                res[i] = b[bi];
                bi++;
                i++;
            }
        }
        return res;
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
        a = mergeSort(a, n);
        // тут ваше решение (реализуйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием






        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151001/pastukhou/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }


}
