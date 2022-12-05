package by.it.group151001.shlyk.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
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

    final int START_LENTH = 1;
    int[] restoreSequence(int[] arrLens, int[] arrSource){
        int iMax = arrLens.length - 1;
        for(int i = iMax - 1; i >= 0; i--){
            if(arrLens[i] > arrLens[iMax])
                iMax = i;
        }
        int oldLength = arrLens[iMax] + 1;
        int[] result = new int[oldLength - 1];
        int iResult = result.length - 1;
        for(int i = iMax; i >= 0 && oldLength != START_LENTH; i--){
            if(arrLens[i] == oldLength - 1){
                result[iResult] = arrSource[i];
                iResult--;
                oldLength--;
            }
        }
        return result;
    }
    int[] getResultSeq(int[] source){
        int[] arrLens = new int[source.length];
        Arrays.fill(arrLens, START_LENTH);
        int nSegLen, nOldValue;
        for(int i = 0; i < source.length; i++){
            nOldValue = source[i];
            nSegLen = arrLens[i];
            for(int j = i + 1; j < source.length; j++){
                if((arrLens[j] < nSegLen + 1) && (source[j] % nOldValue == 0) )
                    arrLens[j] = nSegLen + 1;
            }
        }
        return restoreSequence(arrLens, source);
    }

    int getDivSeqSize(InputStream stream) {
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
        int result = getResultSeq(m).length;


        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151001/shlyk/lesson06/dataB.txt");
        B_LongDivComSubSeq instance = new B_LongDivComSubSeq();
        int result = instance.getDivSeqSize(stream);
        System.out.print(result);
    }

}