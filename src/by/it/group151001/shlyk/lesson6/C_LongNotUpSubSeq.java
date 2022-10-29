package by.it.group151001.shlyk.lesson6;

import java.io.*;
import java.util.Arrays;
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

    final int START_LEN = 1;
    final int INDEX_OFFSET = 1;

    private int[] arrSequence;
    int[] restoreSeq(int[] arrSource, int[] arrLens){
        // find index with max length
        int iMax = arrLens.length - 1;
        for(int i = iMax - 1; i >= 0; i--){
            if(arrLens[i] > arrLens[iMax])
                iMax = i;
        }
        int oldLen = arrLens[iMax] + 1;
        int[] result = new int[oldLen - 1];
        int iResult = result.length - 1;
        for(int i = iMax; i >= 0 && oldLen != START_LEN; i--){
            if(arrLens[i] == oldLen - 1){
                result[iResult] = i + INDEX_OFFSET;
                iResult--;
                oldLen--;
            }
        }
        return result;
    }
    int[] getResultSeq(int[] arrSource){
        int[] arrLens = new int[arrSource.length];
        Arrays.fill(arrLens, START_LEN);
        int nSegLen, nLastValue;
        for(int i = 0; i < arrSource.length; i++){
            nSegLen = arrLens[i];
            nLastValue = arrSource[i];
            for(int j = i + 1; j < arrSource.length; j++){
                if(arrLens[j] < nSegLen + 1 && arrSource[j] <= nLastValue){
                    arrLens[j] = nSegLen + 1;
                }
            }
        }
        return restoreSeq(arrSource, arrLens);
    }
    int[] getSequence(){
        return arrSequence;
    }
    int getSeqLen(){
        return arrSequence.length;
    }
    void findSequence(int[] arrSource){
        this.arrSequence = getResultSeq(arrSource);
    }
    void fileSearch(String fName) throws IOException {
        //подготовка к чтению данных
        InputStream stream = new FileInputStream(fName);
        Scanner input = new Scanner(stream);
        int lenArr = input.nextInt();
        int[] arrNumbers = new int[lenArr];
        for (int i = 0; i < lenArr; i++) {
            arrNumbers[i] = input.nextInt();
        }
        findSequence(arrNumbers);
        input.close();
        stream.close();
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson06/dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        //instance.fileSearch(stream);
        //int result = instance.getSeqLen();
       // System.out.print(result);
    }

}