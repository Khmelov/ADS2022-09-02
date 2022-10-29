package by.it.group151001.shlyk.lesson6;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
Задача на программирование: наибольшая возростающая подпоследовательность
см.     https://ru.wikipedia.org/wiki/Задача_поиска_наибольшей_увеличивающейся_подпоследовательности
        https://en.wikipedia.org/wiki/Longest_increasing_subsequence

Дано:
    целое число 1≤n≤1000
    массив A[1…n] натуральных чисел, не превосходящих 2E9.

Необходимо:
    Выведите максимальное 1<=k<=n, для которого гарантированно найдётся
    подпоследовательность индексов i[1]<i[2]<…<i[k] <= длины k,
    для которой каждый элемент A[i[k]]больше любого предыдущего
    т.е. для всех 1<=j<k, A[i[j]]<A[i[j+1]].

Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ

    Sample Input:
    5
    1 3 3 2 6

    Sample Output:
    3
*/

public class A_LIS {

    final int HUGE_VALUE = 1;
    int[] restoreSequence(int[] arrLens, int[] source){
        int iMax = arrLens.length - 1;
        for(int i = arrLens.length - 1; i >= 0; i--){
            if(arrLens[i] > arrLens[iMax])
                iMax = i;
        }
        int[] result = new int[arrLens[iMax]];
        int iResult = result.length - 1;
        int oldLen = arrLens[iMax] + 1;
        for(int i = iMax; i >= 0 && oldLen != HUGE_VALUE; i--){
            if(arrLens[i] == (oldLen - 1))
            {
                result[iResult] = source[i];
                oldLen--;
                iResult--;
            }
        }
        return result;
    }
    int[] getResult(int[] source){
        int[] sample = new int[source.length];
        Arrays.fill(sample, HUGE_VALUE);
        int nSegLen;
        int lastValue;
        sample[0] = 1; //initial length
        for(int i = 0; i < source.length; i++){
            lastValue = source[i];
            nSegLen = sample[i];
            for(int j = i + 1; j < source.length; j++){
                if( source[j] > lastValue && (sample[j] < nSegLen + 1) ){
                    sample[j] = nSegLen + 1;
                }
            }
        }
        return restoreSequence(sample, source);
    }
    int getSeqSize(InputStream stream) {
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
        int result = getResult(m).length;

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151001/shlyk/lesson06/dataA.txt");
        A_LIS instance = new A_LIS();
        int result = instance.getSeqSize(stream);
        System.out.print(result);
    }
}
