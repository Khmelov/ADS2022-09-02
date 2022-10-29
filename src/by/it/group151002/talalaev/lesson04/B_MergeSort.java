package by.it.group151002.talalaev.lesson04;

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






        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return mergeSort(a);
    }

    int[] divArr(int[] arr){
        if (arr.length <= 1) {
            return arr;
        }
        int mid = arr.length / 2;
        int[] left = divArr(Arrays.copyOfRange(arr, 0, mid));
        int[] right = divArr(Arrays.copyOfRange(arr, mid, arr.length));

        return merge(left, right);
    }

    int[] merge(int[] lArr, int[] rArr) {
        int[] res = new int[lArr.length + rArr.length];
        int l = 0;
        int r = 0;
        int i = 0;
        while (i < res.length) {
            if (r >= rArr.length) {
                res[i] = lArr[l];
                l++;
            }else
            if (l >= lArr.length) {
                res[i] = rArr[r];
                r++;
            }else
            if (lArr[l] < rArr[r]){
                res[i] = lArr[l];
                l++;
            }else{
                res[i] = rArr[r];
                r++;
            }
            i++;
        }
        return res;
    }

    int[] mergeSort(int[] arr){
        return divArr(arr);
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
