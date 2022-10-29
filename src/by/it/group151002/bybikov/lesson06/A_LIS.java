package by.it.group151002.bybikov.lesson06;

import java.io.IOException;
import java.util.Arrays;

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

    int getSeqSize(int[] inputArray) throws IOException {

        //тут реализуйте логику задачи методами динамического программирования (!!!)
        if(inputArray == null)
            return 0;
        //System.err.println(Arrays.toString(inputArray));
        if(inputArray.length < 2)
            return inputArray.length;
        int[] sequenceLengthArray = new int[inputArray.length];
        Arrays.fill(sequenceLengthArray, 1);
        for (int firstCounter = 0; firstCounter < inputArray.length; firstCounter++) {
            for (int secondCounter = firstCounter + 1; secondCounter < inputArray.length; secondCounter++) {
                if(inputArray[secondCounter] > inputArray[firstCounter] && sequenceLengthArray[secondCounter] < sequenceLengthArray[firstCounter] + 1)
                    sequenceLengthArray[secondCounter] = sequenceLengthArray[firstCounter] + 1;
            }
        }
        int i = 0;
        int maxSequenceLength = sequenceLengthArray[i++];
        for ( ; i < sequenceLengthArray.length; i++){
            if(sequenceLengthArray[i] > maxSequenceLength)
                maxSequenceLength = sequenceLengthArray[i];
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        // remind to change return statement
        return maxSequenceLength;
    }


    public static void main(String[] args) throws IOException {
        String root = System.getProperty("user.dir") + "/src/";
        String fullFilePath = root + "by/it/group151002/bybikov/lesson06/dataA.txt";
        A_LIS instance = new A_LIS();
        FileMethods fileMethods = new FileMethods();
        //int[] inputArray = fileMethods.getRandomArray(fullFilePath);
        int[] array = {-95, -100, -98, -94, -100, -94};
        int result = instance.getSeqSize(array);
        A_LIS_SECOND_SOLUTION a_lis_second_solution = new A_LIS_SECOND_SOLUTION();
        int secondResult = a_lis_second_solution.getLargestIncreaseSequenceLength(array);
        System.out.println(result);
        System.err.println(secondResult);
    }
}
