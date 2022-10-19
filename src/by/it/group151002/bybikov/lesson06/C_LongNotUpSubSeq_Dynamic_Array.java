package by.it.group151002.bybikov.lesson06;

import java.util.Arrays;

public class C_LongNotUpSubSeq_Dynamic_Array {

    int[] getNotUpSequenceLengthArray(int[] inputArray) {
        //подготовка к чтению данных
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //общая длина последовательности
        if(inputArray == null)
            return null;
        if(inputArray.length == 0)
            return null;
        //тут реализуйте логику задачи методами динамического программирования (!!!)
        int[] result = new int[inputArray.length];
        Arrays.fill(result, 1);
        for (int firstCounter = 1; firstCounter < inputArray.length; firstCounter++) {
            for (int secondCounter = firstCounter - 1; secondCounter >= 0; secondCounter--) {
                if(inputArray[firstCounter] <= inputArray[secondCounter]) {
                    if(result[firstCounter] < result[secondCounter] + 1) {
                        result[firstCounter] = result[secondCounter] + 1;
                    }
                }
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        // remind to change return statement
        return result;
    }

    private int getLargestValueIndex (int[] array) {
        if(array == null)
            return -1;
        if(array.length == 0)
            return -1;
        int largestValueIndex = 0;
        for (int i = 1; i < array.length; i++) {
            if(array[i] > array[largestValueIndex]){
                largestValueIndex = i;
            }
        }
        return largestValueIndex;
    }

    int getLargestNotUpSequenceLength (int[] sequenceLengthArray) {
        int largestValueIndex = getLargestValueIndex(sequenceLengthArray);
        if (largestValueIndex == -1)
            return 0;
        return sequenceLengthArray[largestValueIndex];
    }

    int[] getNotUpLargestSequence (int[] valuesArray, int[] sequenceLengthArray) {
        if (valuesArray == null)
            return null;
        if (valuesArray.length == 0)
            return null;
        int largestSequenceEndIndex = getLargestValueIndex(sequenceLengthArray);
        if (largestSequenceEndIndex == -1)
            return null;
        int[] result = new int[sequenceLengthArray[largestSequenceEndIndex]];
        int resultCounter = result.length - 1;
        result[resultCounter--] = valuesArray[largestSequenceEndIndex];
        for (int i = largestSequenceEndIndex - 1; i >= 0; i--) {
            if(valuesArray[i] >= valuesArray[largestSequenceEndIndex] && sequenceLengthArray[i] == sequenceLengthArray[largestSequenceEndIndex] - 1) {
                result[resultCounter--] = valuesArray[i];
                largestSequenceEndIndex = i;
            }
        }
        return result;
    }
}
