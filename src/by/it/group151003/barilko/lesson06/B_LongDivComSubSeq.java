package by.it.group151003.barilko.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: наибольшая кратная подпоследовательность

Дано:
    целое число 1≤n≤1000
    массив A[1…n] натуральных чисел, не превосходящих 2E9.

Необходимо:
    Выведите максимальное 1<=k<=n, для которого гарантированно найдётся
    подпоследовательность индексов i[1]<i[2]<…<i[k] <= длины k,
    для которой каждый элемент A[i[k]] делится на предыдущий
    т.е. для всех 1<=j<k, A[i[j+1]] делится на A[i[j]].

Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ

    Sample Input:
    4
    3 6 7 12

    Sample Output:
    3
*/

public class B_LongDivComSubSeq {


    int getDivSeqSize(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //общая длина последовательности
        int n = scanner.nextInt();
        int[] arr = new int[n];
        //читаем всю последовательность
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        //тут реализуйте логику задачи методами динамического программирования (!!!)



        int[] temp = new int[n];

        /*int[] iaPrev = new int[n];
        int[] iaMin = new int[n];
        iaMin[0] = -1;

        for(int i = 0; i < n; ++i)
        {
            int low = 1;
            int high = iMaxlength + 1;
            while(low < high)
            {
                int mid = low + (high - low) / 2;
                if(arr[i] % arr[iaMin[mid]] == 0)
                    low = mid + 1;    
                else
                    high = mid;
            }

            int iNewLength = low;

            iaPrev[i] = iaMin[iNewLength - 1];
            iaMin[iNewLength] = i;

            if(iNewLength > iMaxlength)
                iMaxlength = iNewLength;
        }*/

        for(int i = 0; i < n; ++i)
        {
            temp[i] = 1;
            for(int j = 0; j < i; ++j)
                if(arr[i] % arr[j] == 0 && temp[j] + 1 > temp[i])
                    temp[i] = temp[j] + 1;
        }

        int iMaxlength = 0;
        for(int i = 0; i < n; ++i)
            iMaxlength = iMaxlength > temp[i] ? iMaxlength : temp[i];

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return iMaxlength;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151003/barilko/lesson06/dataB.txt");
        B_LongDivComSubSeq instance = new B_LongDivComSubSeq();
        int result = instance.getDivSeqSize(stream);
        System.out.print(result);
    }

}