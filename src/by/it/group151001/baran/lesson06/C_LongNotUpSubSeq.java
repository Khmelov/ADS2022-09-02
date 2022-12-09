package by.it.group151001.baran.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.max;

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

    int getNotUpSeqSize(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //общая длина последовательности
        int n = scanner.nextInt();
        int[] m = new int[n];
        //читаем всю последовательность
        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();
        }

        int result = 0;
        int[] d = new int[n];
        int[] prev = new int[n];
        for(int i = 0; i < n; i++) prev[i] = -1;
        for(int i = 0; i < n; i++){
            d[i] = 1;
            for(int j = 0; j < i; j++){
                if(m[i] <= m[j] && d[j] + 1 > d[i]){
                    d[i] = d[j] + 1;
                    prev[i] = j;
                }
            }
        }
        int idxMax = 0;
        for(int i = 0; i < n; i++){
            if(result < d[i]){
                result = d[i];
                idxMax = i;
            }
        }
        // Вывод последовательности индексов
        /*

        int i = idxMax;
        int[] b = new int[result];
        b[0] = i + 1;
        int j = 1;
        while(prev[i] != -1){
            b[j] = prev[i] + 1;
            j++;
            i = prev[i];
        }
        Arrays.sort(b);
        for(i = 0; i < result; i++){
            System.out.print(b[i]);
            System.out.print(' ');
        }

        System.out.print('\n');

         */

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