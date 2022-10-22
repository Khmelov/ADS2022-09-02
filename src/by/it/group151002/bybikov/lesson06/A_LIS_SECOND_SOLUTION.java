package by.it.group151002.bybikov.lesson06;

import java.util.HashSet;

public class A_LIS_SECOND_SOLUTION {

    int[] createArrayWithUniqueValues (int[] inputArray) {
        if(inputArray == null)
            return null;
        if(inputArray.length < 2)
            return inputArray;
        HashSet<Integer> arrayContainsSet = new HashSet<Integer>();
        int[] resultArray = new int[inputArray.length];
        int secondCounter = 0;
        for (int firstCounter = 0; firstCounter < inputArray.length; firstCounter++) {
            if(!arrayContainsSet.contains(inputArray[firstCounter])){
                arrayContainsSet.add(inputArray[firstCounter]);
                resultArray[secondCounter++] = inputArray[firstCounter];
            }
        }
        int[] newArray = new int[secondCounter];
        System.arraycopy(resultArray, 0, newArray, 0, newArray.length);
        return newArray;
    }

    private void exchangeValuesByIndex (int[] inputArray, int firstIndex, int secondIndex) {
        if(inputArray == null)
            return;
        if(firstIndex > inputArray.length || secondIndex > inputArray.length || firstIndex < 0 || secondIndex < 0)
            return;
        int tmp = inputArray[firstIndex];
        inputArray[firstIndex] = inputArray[secondIndex];
        inputArray[secondIndex] = tmp;
    }

    private int partition (int[] inputArray, int beginIndex, int endIndex) {
        RandomValuesMethods randomValuesMethods = new RandomValuesMethods();
        int pivotIndex = randomValuesMethods.getIntRandomValueInRange(beginIndex, endIndex);
        exchangeValuesByIndex(inputArray, pivotIndex, endIndex);
        pivotIndex = endIndex;
        int exchangeAmount = 0;
        for (int i = 0; i < pivotIndex; i++) {
            if (inputArray[i] < inputArray[pivotIndex]) {
                exchangeValuesByIndex(inputArray, i, exchangeAmount++);
            }
        }
        exchangeValuesByIndex(inputArray, exchangeAmount, pivotIndex);
        pivotIndex = exchangeAmount;
        return pivotIndex;
    }

    private void quickSortMethod (int[] inputArray, int beginIndex, int endIndex) {
        if(endIndex <= beginIndex)
            return;
        int dividedIndex = partition(inputArray, beginIndex, endIndex);
        quickSortMethod(inputArray, beginIndex, dividedIndex);
        quickSortMethod(inputArray, dividedIndex + 1, endIndex);
    }

    void quickSortArray (int[] inputArray) {
        if(inputArray == null)
            return;
        quickSortMethod(inputArray, 0, inputArray.length - 1);
    }

    boolean isSortedArray (int[] array) {
        if (array == null)
            return true;
        boolean isSorted = true;
        for (int i = 1; i < array.length && isSorted; i++) {
            if(array[i - 1] >= array[i]) {
                isSorted = false;
            }
        }
        return isSorted;
    }

    void fullMatrixMethod () {}

    int getLargestIncreaseSequenceLength(int[] inputArray) {
        if(inputArray == null)
            return 0;
        int[] uniqueArray = createArrayWithUniqueValues(inputArray);
        if (uniqueArray == null)
            return 0;
        quickSortArray(uniqueArray);
        ///////////////////////////
        int[][] dynamicMatrix = new int[inputArray.length + 1][uniqueArray.length + 1];
        for (int uniqueCounter = 1; uniqueCounter <= uniqueArray.length; uniqueCounter++) {
            for (int inputCounter = 1; inputCounter <= inputArray.length; inputCounter++) {
                if(inputArray[inputCounter - 1] == uniqueArray[uniqueCounter - 1]) {
                    dynamicMatrix[inputCounter][uniqueCounter] = 1 + dynamicMatrix[inputCounter - 1][uniqueCounter - 1];
                }
                else {
                    dynamicMatrix[inputCounter][uniqueCounter] = Math.max(dynamicMatrix[inputCounter - 1][uniqueCounter],
                                                                          dynamicMatrix[inputCounter][uniqueCounter - 1]);
                }
            }
        }
        /////////////////////////////////////
        int result = dynamicMatrix[inputArray.length][uniqueArray.length];
        return result;
    }
}
