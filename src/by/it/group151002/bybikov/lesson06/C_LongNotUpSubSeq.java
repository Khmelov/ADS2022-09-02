package by.it.group151002.bybikov.lesson06;

import java.io.IOException;
import java.util.Arrays;

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

    public static void main(String[] args) throws IOException {
        String root = System.getProperty("user.dir") + "/src/";
        String fullFilePath = root + "by/it/group151002/bybikov/lesson06/dataC.txt";
        C_LongNotUpSubSeq_Dynamic_Array dynamicArrayRealization = new C_LongNotUpSubSeq_Dynamic_Array();
        FileMethods fileMethods = new FileMethods();
        for (int counter = 0; counter < 100; counter++) {
            int[] inputArray = fileMethods.getRandomArray(fullFilePath);
            int[] sequenceLengthArray = dynamicArrayRealization.getNotUpSequenceLengthArray(inputArray);
            for (int i = 0; i < inputArray.length; i++) {
                System.out.printf("%d(%d) ", inputArray[i], sequenceLengthArray[i]);
            }
            int largestNotUpSequenceLength = dynamicArrayRealization.getLargestNotUpSequenceLength(sequenceLengthArray);
            System.out.printf("\nLength %d: ", largestNotUpSequenceLength);
            int[] largestNotIpSequence = dynamicArrayRealization.getNotUpLargestSequence(inputArray, sequenceLengthArray);
            System.out.println(Arrays.toString(largestNotIpSequence));
        }
    }

}