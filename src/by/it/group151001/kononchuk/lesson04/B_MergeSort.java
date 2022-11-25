package by.it.group151001.kononchuk.lesson04;

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

    void mergeSort(int[] arr, int left, int right) {
        if(left < right){
            int middle = (left + right) / 2;
            mergeSort(arr, left, middle);
            mergeSort(arr, middle + 1, right);

            int[] lArr = new int[middle + 1 - left];
            int[] rArr = new int[right - middle];

            System.arraycopy(arr, left, lArr, 0, lArr.length);
            System.arraycopy(arr, middle + 1, rArr, 0, rArr.length);

            int l = 0, r = 0, temp = 0;

            for(int i = left; i <= right; i++)
            {
                if (l < lArr.length && r < rArr.length){
                    if (lArr[l] <= rArr[r]){
                        temp = lArr[l];
                        l++;
                    }
                    else {
                        temp = rArr[r];
                        r++;
                    }
                }
                else {
                    if (l >= lArr.length)
                    {
                        temp = rArr[r];
                        r++;
                    }
                    else if (r >= rArr.length){
                        temp = lArr[l];
                        l++;
                    }
                }

                arr[i] = temp;
            }
        }
    }

    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
            //System.out.println(arr[i]);
        }
        mergeSort(arr, 0, n - 1);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return arr;
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151001/kononchuk/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }


}
