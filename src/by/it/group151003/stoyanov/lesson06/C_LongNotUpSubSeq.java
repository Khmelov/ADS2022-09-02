package by.it.group151003.stoyanov.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

/*
Задача на программирование: наибольшая невозростающая подпоследовательность

Дано:
    целое число 1<=n<=1E5 ( ОБРАТИТЕ ВНИМАНИЕ НА РАЗМЕРНОСТЬ! )
    массив A[1…n] натуральных чисел, не превосходящих 2E9.

Необходимо:
    Выведите максимальное 1<=k<=n, для которого гарантированно найдётся
    подпоследовательность индексов i[1]<i[2]<…<i[k] <= длины k,
    для которой каждый элемент A[i[k]] не больше любого предыдущего
    т.е. для всех 1<=j<k, A[i[j]]>=A[i[j+1]].

    В первой строке выведите её длину k,
    во второй - её индексы i[1]<i[2]<…<i[k]
    соблюдая A[i[1]]>=A[i[2]]>= ... >=A[i[n]].

    (индекс начинается с 1)

Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ

    Sample Input:
    5
    5 3 4 4 2

    Sample Output:
    4
    1 3 4 5
*/


public class C_LongNotUpSubSeq {

    private void reverse(int[] a) {
        for(int i = 0; i < a.length / 2; i++)
        {
            int temp = a[i];
            a[i] = a[a.length - i - 1];
            a[a.length - i - 1] = temp;
        }
    }
    int getNotUpSeqSize(int[] m) {

        int n = m.length;
        reverse(m);

        int[] p = new int[n];
        int[] l = new int[n + 1];
        int length = 0;
        for (int i = 0; i < n; i++) {
            int left = 1, right = length;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (m[l[mid]] <= m[i])
                    left = mid + 1;
                else
                    right = mid - 1;

            }

            int newLength = left;
            p[i] = l[newLength - 1];
            if (newLength > length) {
                l[newLength] = i;
                length = newLength;
            } else if (m[i] < m[l[newLength]]) {
                l[newLength] = i;
            }
        }
        System.out.println(length);
        int k = l[length];
        for (int i = length - 1; i >= 0; i--) {
            System.out.print(n - k + " ");
            k = p[k];
        }

        return length;

    }


    public static void main(String[] args) {
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int[] m = {5, 3, 4, 4, 2};
        int result = instance.getNotUpSeqSize(m);
    }

}