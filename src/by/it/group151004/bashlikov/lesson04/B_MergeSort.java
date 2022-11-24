package by.it.group151004.bashlikov.lesson04;

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

        int[] buffer = new int[n];
        int[] result = mergeSort(a, buffer, 0, n - 1);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    public int[] mergeSort(int[] array, int[] buffer, int left, int right) {
        if (left == right) {
            buffer[left] = array[left];
            return buffer;
        }

        int middle = left + (right - left) / 2;

        int[] lBuffer = mergeSort(array, buffer, left, middle);
        int[] rBuffer = mergeSort(array, buffer, middle + 1, right);

        int[] target = lBuffer == array ? buffer : array;

        int lCurr = left, rCur = middle + 1;
        for (int i = left; i <= right; i++) {
            if (lCurr <= middle && rCur <= right) {
                if (lBuffer[lCurr] < rBuffer[rCur]) {
                    target[i] = lBuffer[lCurr];
                    lCurr++;
                } else {
                    target[i] = rBuffer[rCur];
                    rCur++;
                }
            } else if (lCurr <= middle) {
                target[i] = lBuffer[lCurr];
                lCurr++;
            } else  {
                target[i] = rBuffer[rCur];
                rCur++;
            }
        }
        return  target;
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
