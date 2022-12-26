package by.it.group151004.prokopchuk.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

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

    int[] getNotUpSeqSize(InputStream stream) throws FileNotFoundException {
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
        int[] result = new int[n+1];
        int[] arr = new int[n];
        result[0] = 1;
        int[] seq = new int[n];
        for (int i = 0; i < m.length; i++) {
            arr[i] = 1;
            for (int j = 0; j < i; j++) {
                if((m[j] >= m[i]) && (arr[j] + 1 > arr[i])) {
                    arr[i] = arr[j] + 1;
                    seq[i] = j;
                }
            }
        }
        int k = 1;
        for (int i = 0; i < m.length; i++) {
            if (arr[i] > result[0]) {
                result[0] = arr[i];
                k = i;
            }
        }
//        result = Arrays.copyOfRange(result,0,k+1);
        do {
            result[arr[k]] = k+1;
            k = seq[k];
        } while (k > 0);
        result[arr[k]] = k+1;
        for (int i = 1; i < result.length; i++) {
            if (result[i] == 0) {
                result = Arrays.copyOfRange(result,0,i);
            }
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson06/dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int[] result = instance.getNotUpSeqSize(stream);
        System.out.println(result[0]);
        System.out.print(Arrays.toString(Arrays.copyOfRange(result,1,result.length)));
    }

}