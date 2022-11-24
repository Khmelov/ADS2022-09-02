package by.it.group151002.bybikov.lesson06;

import java.io.IOException;
import java.util.Arrays;

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

    private int getLargestDivSequenceEndIndex (int[] sequenceArray) {
        if(sequenceArray == null)
            return -1;
        if(sequenceArray.length == 0)
            return -1;
        int largestSequenceIndex = 0;
        for (int i = 1; i < sequenceArray.length; i++) {
            if(sequenceArray[i] > sequenceArray[largestSequenceIndex])
                largestSequenceIndex = i;
        }
        return largestSequenceIndex;
    }

    int[] getDivSequenceSizeArray(int[] inputArray) {
        //подготовка к чтению данных
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //общая длина последовательности
        //тут реализуйте логику задачи методами динамического программирования (!!!)
        if (inputArray == null)
            return null;
        if(inputArray.length == 0)
            return null;
        int[] resultArray = new int[inputArray.length];
        Arrays.fill(resultArray, 1);
        for (int firstCounter = 1; firstCounter < resultArray.length; firstCounter++) {
            for (int secondCounter = firstCounter - 1; secondCounter >= 0; secondCounter--) {
                if(inputArray[secondCounter] == 0)
                    continue;
                if(inputArray[firstCounter] / inputArray[secondCounter] != 0 && inputArray[firstCounter] % inputArray[secondCounter] == 0) {
                    if(resultArray[firstCounter] < resultArray[secondCounter] + 1)
                        resultArray[firstCounter] = resultArray[secondCounter] + 1;
                }
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return resultArray;
    }

    int getLargestDivSequenceLength (int[] divSequenceSizeArray) {
        if(divSequenceSizeArray == null)
            return 0;
        int largestSequenceIndex = getLargestDivSequenceEndIndex(divSequenceSizeArray);
        if (largestSequenceIndex == -1)
            return 0;
        return divSequenceSizeArray[largestSequenceIndex];
    }

    public static void main(String[] args) throws IOException {
        String root = System.getProperty("user.dir") + "/src/";
        String fullFilePath = root + "by/it/group151002/bybikov/lesson06/dataB.txt";
        B_LongDivComSubSeq instance = new B_LongDivComSubSeq();
        FileMethods fileMethods = new FileMethods();
        //int[] inputArray = fileMethods.getRandomArray(fullFilePath);
        //int[] result = instance.getDivSeqSizeArray(inputArray);
        //System.out.print(-27 % 27);
    }

}