package by.it.group151002.bybikov.lesson08;

import java.util.Arrays;

public class A_Knapsack_Solution {

    private boolean isValidParameters(int knapsackSize, int[] goldBarsWeightArray) {
        boolean isValid = true;
        if (goldBarsWeightArray == null || knapsackSize < 1 || knapsackSize > 100000) {
            isValid = false;
        }
        else if (goldBarsWeightArray.length < 1 || goldBarsWeightArray.length > 300) {
            isValid = false;
        }
        else {
            for (int i = 0; i < goldBarsWeightArray.length && isValid; i++) {
                if (goldBarsWeightArray[i] < 0 || goldBarsWeightArray[i] > 100000) {
                    isValid = false;
                }
            }
        }
        return isValid;
    }

    private int[] createKnapsackDynamicArray (int knapsackSize, int[] barsWeightArray) {
        int[] goldWeightArray = new int[knapsackSize + 1];
        for (int goldWeightCounter = 1; goldWeightCounter < goldWeightArray.length; goldWeightCounter++) {
            for (int barsWeightCounter = 0; barsWeightCounter < barsWeightArray.length; barsWeightCounter++) {
                int currentBarWeight = barsWeightArray[barsWeightCounter];
                if(goldWeightCounter - currentBarWeight >= 0) {
                    int goldWeight = currentBarWeight + goldWeightArray[goldWeightCounter - currentBarWeight];
                    if(goldWeight > goldWeightArray[goldWeightCounter]){
                        goldWeightArray[goldWeightCounter] = goldWeight;
                    }
                }
            }
        }
        return goldWeightArray;
    }

    int getMaxKnapsackWeight (int knapsackSize, int[] goldBarsWeightArray) {
        if (!isValidParameters(knapsackSize, goldBarsWeightArray)) {
            return -1;
        }
        int[] knapsackWeightArray = createKnapsackDynamicArray(knapsackSize, goldBarsWeightArray);
        return knapsackWeightArray[knapsackWeightArray.length - 1];
    }

    private int instanceRecurrentMethod (int knapsackSize, int[] barsWeightArray) {
        int knapsackFreeSpace = knapsackSize;
        for (int i = 0; i < barsWeightArray.length; i++) {
            if (barsWeightArray[i] == 0) {
                continue;
            }
            if (barsWeightArray[i] <= knapsackSize) {
                knapsackFreeSpace = Math.min(knapsackFreeSpace, instanceRecurrentMethod(knapsackSize - barsWeightArray[i], barsWeightArray) );
                if (knapsackFreeSpace == 0) {
                    return knapsackFreeSpace;
                }
            }
        }
        return knapsackFreeSpace;
    }

    private int[] mergeTwoArrays (int[] firstArray, int[] secondArray) {
        int[] result = new int[firstArray.length + secondArray.length];
        int i = 0, j = 0;
        while (i < firstArray.length && j < secondArray.length) {
            if(firstArray[i] > secondArray[j]) {
                result[i + j] = firstArray[i];
                i++;
            }
            else {
                result[i + j] = secondArray[j];
                j++;
            }

        }
        for ( ; i < firstArray.length; i++) {
            result[i + j] = firstArray[i];
        }
        for (; j < secondArray.length; j++) {
            result[i + j] = secondArray[j];
        }
        return result;
    }

    private int[] mergeSortRealization (int[] array) {
        if(array == null || array.length < 2)
            return array;
        int beginIndex = 0, endIndex = array.length;
        int dividerIndex = (beginIndex + endIndex) / 2;
        int[] firstPartArray = mergeSortRealization(Arrays.copyOfRange(array, beginIndex, dividerIndex) );
        int[] secondPartArray = mergeSortRealization(Arrays.copyOfRange(array, dividerIndex, endIndex) );
        array = mergeTwoArrays(firstPartArray, secondPartArray);
        return array;
    }

    int instanceMaxKnapsackWeight (int knapsackSize, int[] barsWeightArray) {
        if (!isValidParameters(knapsackSize, barsWeightArray)) {
            return -1;
        }
        int[] sortedBarsWeightArray  = mergeSortRealization(barsWeightArray);
        return knapsackSize - instanceRecurrentMethod(knapsackSize, sortedBarsWeightArray);
    }

}
