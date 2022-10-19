package by.it.group151002.bybikov.lesson06;

import java.util.Arrays;

public class C_LongNotUpSubSeq_Longest_Common_SubSequence {

    private int[] mergeTwoArrays (int[] firstArray, int[] secondArray) {
        if (firstArray == null || secondArray == null)
            return null;
        int firstCounter = 0;
        int secondCounter = 0;
        int[] resultArray = new int[firstArray.length + secondArray.length];
        while (firstCounter < firstArray.length && secondCounter < secondArray.length) {
            if (firstArray[firstCounter] > secondArray[secondCounter]) {
                resultArray[firstCounter + secondCounter] = firstArray[firstCounter++];
            }
            else  {
                resultArray[firstCounter + secondCounter] = secondArray[secondCounter++];
            }
        }
        for ( ; firstCounter < firstArray.length; firstCounter++) {
            resultArray[firstCounter + secondCounter] = firstArray[firstCounter];
        }
        for ( ; secondCounter < secondArray.length; secondCounter++) {
            resultArray[firstCounter + secondCounter] = secondArray[secondCounter];
        }
        return resultArray;
    }

    private int[] mergeSortMethod (int[] array) {
        if (array == null)
            return null;
        if (array.length < 2)
            return array;
        int dividerIndex = array.length / 2;
        int[] firstPartArray = mergeSortMethod(Arrays.copyOfRange(array, 0, dividerIndex));
        int[] secondPartArray = mergeSortMethod(Arrays.copyOfRange(array, dividerIndex, array.length));
        return mergeTwoArrays(firstPartArray, secondPartArray);
    }

    private int[] mergeSort (int[] array) {
        if (array == null)
            return null;
        if (array.length < 2)
            return array;
        return mergeSortMethod(array);
    }

    private int[][] getLongestCommonSubSequenceMatrixMethod (int[] inputArray, int[] notUpSortedArray) {
        if (inputArray == null || notUpSortedArray == null)
            return null;
        if (inputArray.length == 0 || notUpSortedArray.length == 0)
            return null;
        int[][] dynamicMatrix = new int[notUpSortedArray.length + 1][inputArray.length + 1];


        for (int notUpSortedCounter = 1; notUpSortedCounter <= notUpSortedArray.length; notUpSortedCounter++) {
            for (int inputCounter = 1; inputCounter <= inputArray.length; inputCounter++) {
                if(notUpSortedArray[notUpSortedCounter - 1] == inputArray[inputCounter - 1]) {
                    dynamicMatrix[notUpSortedCounter][inputCounter] = dynamicMatrix[notUpSortedCounter - 1][inputCounter - 1] + 1;
                }
                else {
                    dynamicMatrix[notUpSortedCounter][inputCounter] = Math.max(dynamicMatrix[notUpSortedCounter - 1][inputCounter], dynamicMatrix[notUpSortedCounter][inputCounter - 1]);
                }
            }
        }
        return dynamicMatrix;
    }

    int[][] getLongestCommonSubSequenceMatrix (int[] inputArray) {
        if (inputArray == null)
            return null;
        int[] notUpSortedArray = mergeSort(inputArray);
        return getLongestCommonSubSequenceMatrixMethod(inputArray, notUpSortedArray);
    }

    int getLargestNotUpSequenceLength (int[][] longestCommonSubSequenceMatrix) {
        if (longestCommonSubSequenceMatrix == null)
            return 0;
        int resultIndex = longestCommonSubSequenceMatrix.length - 1;
        return longestCommonSubSequenceMatrix[resultIndex][resultIndex];
    }

    int[] getLargestNotUpSequencePositions (int[] inputArray, int[][] dynamicProgMatrix) {
        if (dynamicProgMatrix == null || inputArray == null)
            return null;
        int[] notUpSortArray = mergeSort(inputArray);
        int inputIndex = dynamicProgMatrix.length - 1;
        int notUpSortIndex = dynamicProgMatrix.length - 1;
        int[] resultArray = new int[dynamicProgMatrix[inputIndex][notUpSortIndex]];
        int resultIndex = resultArray.length - 1;
        while (inputIndex > 0 && notUpSortIndex > 0) {
            if(inputArray[inputIndex - 1] == notUpSortArray[notUpSortIndex - 1]) {
                resultArray[resultIndex--] = inputIndex - 1;
                inputIndex--;
                notUpSortIndex--;
            }
            else if (dynamicProgMatrix[notUpSortIndex - 1][inputIndex] > dynamicProgMatrix[notUpSortIndex][inputIndex - 1]){
                notUpSortIndex--;
            }
            else {
                inputIndex--;
            }
        }
        return resultArray;
    }
}
