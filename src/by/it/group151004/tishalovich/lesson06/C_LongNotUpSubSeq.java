package by.it.group151004.tishalovich.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

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

    void outArr(int[] arr){
        System.out.print(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            System.out.print(" " + arr[i]);
        }
        System.out.println();
    }
    int binarySearch(int[] arr, int value){
        int left = -1;
        int right = arr.length;
        //according to the conditions -1 and arr.length cannot be returned

        while(right - left > 1) {
            int m = (right + left) / 2;
            if (arr[m] >= value)
                left = m;
            else
                right = m;
        }
        return  right;
    }

    void swap(int[] arr, int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    int[] getResults(int result, int[] prev, int[] lastsInd){
        int[] results = new int[result];
        int i = 0;
        int curr = lastsInd[result];
        while(curr != -1){
            results[i++] = curr;
            curr = prev[curr];
        }
        for (int j = 0; j < results.length / 2; j++) {
            swap(results, j, results.length - 1 - j);
        }
        return results;
    }

    int getNotUpSeqSize(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //общая длина последовательности
        int n = scanner.nextInt();
        int[] m = new int[n];
        //читаем всю последовательность
        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();
        }
        //тут реализуйте логику задачи методами динамического программирования (!!!)
        int[] lasts = new int[n + 1];
        int[] prev = new int[n + 1];
        int[] lastsInd = new int[n + 1];
        lastsInd[0] = -1;
        lasts[0] = Integer.MAX_VALUE;
        for (int i = 1; i < n+1; i++) {
            lasts[i] = Integer.MIN_VALUE;
        }

        int result = 0;

        for (int i = 1; i < n + 1; i++) {
            int value = m[i - 1];
            int index = binarySearch(lasts, value);
            lasts[index] = value;
            lastsInd[index] = i;
            prev[i] = lastsInd[index - 1];
            result = Math.max(result, index);
        }

        int[] results = getResults(result, prev, lastsInd);
        System.out.println(result);
        outArr(results);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson06/dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int result = instance.getNotUpSeqSize(stream);
        System.out.print(result);
    }
}