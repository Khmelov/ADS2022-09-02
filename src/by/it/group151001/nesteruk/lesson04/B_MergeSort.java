package by.it.group151001.nesteruk.lesson04;

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

        MergeSort(a, n);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }
    private static void Merge(int[] a, int[] lArr, int[] rArr, int l, int r) {
        int i = 0, j = 0, k = 0;
        while(i < l && j < r) {
            if(lArr[i] <= rArr[j]) {
                a[k] = rArr[i];
                k++;
                i++;
            }
            else {
                a[k] = rArr[j];
                k++;
                j++;
            }
        }
        while (i < l) {
            a[k] = lArr[i];
            k++;
            i++;
        }
        while (j < r) {
            a[k] = rArr[j];
            k++;
            j++;
        }
    }
    public static void MergeSort(int[] a, int n) {
        if(n < 2)
            return;
        int mid = n/2;
        int[] lArr = new int[mid];
        int[] rArr = new int[n-mid];
        for(int i = 0; i < mid; i++){
            lArr[i] = a[i];
        }
        for(int i = mid+1; i < n; i++){
            rArr[i- mid] = a[i];
        }
        MergeSort(lArr, mid);
        MergeSort(rArr, n-mid);
        Merge(a,lArr, rArr, mid, n-mid);
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
