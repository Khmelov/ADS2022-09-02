package by.it.group151002.bybikov.lesson06;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class Lesson6Test {
    @Test
    public void A() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        String fullFilePath = root + "by/it/group151002/bybikov/lesson06/dataA.txt";
        FileMethods fileMethods = new FileMethods();
        boolean isCorrect = true;
        for (int i = 0; i < 1000 && isCorrect; i++) {
            int[] inputArray = fileMethods.getRandomArray(fullFilePath);
            A_LIS instance = new A_LIS();
            int firstResult = instance.getSeqSize(inputArray);
            A_LIS_SECOND_SOLUTION a_lis_second_solution = new A_LIS_SECOND_SOLUTION();
            int secondResult = a_lis_second_solution.getLargestIncreaseSequenceLength(inputArray);
            isCorrect = firstResult == secondResult;
        }
        assertTrue("A failed", isCorrect);
    }

    @Test
    public void QuickSortTest () throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        String fullFilePath = root + "by/it/group151002/bybikov/lesson06/dataA.txt";
        FileMethods fileMethods =new FileMethods();
        boolean isCorrect = true;
        for (int i = 0; i < 1000 && isCorrect; i++) {
            int[] inputArray = fileMethods.getRandomArray(fullFilePath);
            A_LIS_SECOND_SOLUTION a_lis_second_solution = new A_LIS_SECOND_SOLUTION();
            int[] uniqueArray = a_lis_second_solution.createArrayWithUniqueValues(inputArray);
            a_lis_second_solution.quickSortArray(uniqueArray);
            a_lis_second_solution.getLargestIncreaseSequenceLength(inputArray);
            isCorrect = a_lis_second_solution.isSortedArray(uniqueArray);
        }
        assertTrue("QuickSort Failed", isCorrect);

    }

    @Test
    public void B() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        String fullFilePath = root + "by/it/group151002/bybikov/lesson06/dataB.txt";
        B_LongDivComSubSeq instance=new B_LongDivComSubSeq();
        FileMethods fileMethods = new FileMethods();
        for(int i  = 0; i < 1000; i++) {
            int[] inputArray = fileMethods.getRandomArray(fullFilePath);
            //System.out.println(Arrays.toString(inputArray));
            int[] divSequenceSizeArray = instance.getDivSequenceSizeArray(inputArray);
            int largestSequenceLength = instance.getLargestDivSequenceLength(divSequenceSizeArray);
            //System.out.println(largestSequenceLength);
        }

    }

    private int[] getArrayBePositions (int[] inputArray, int[] positionsArray) {
        if (inputArray == null || positionsArray == null)
            return null;
        int[] resultArray = new int[positionsArray.length];
        for (int i = 0; i < positionsArray.length; i++) {
            resultArray[i] = inputArray[positionsArray[i]];
        }
        return resultArray;
    }

    private boolean isNotUpSortArray (int[] array) {
        if (array == null)
            return true;
        boolean isSorted = true;
        for (int i = 1; i < array.length && isSorted; i++) {
            if(array[i - 1] < array[i]) {
                isSorted = false;
            }
        }
        return isSorted;
    }

    @Test
    public void C() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        String fullFilePath = root + "by/it/group151002/bybikov/lesson06/dataC.txt";
        FileMethods fileMethods = new FileMethods();
        C_LongNotUpSubSeq_Dynamic_Array dynamic_Array = new C_LongNotUpSubSeq_Dynamic_Array();
        C_LongNotUpSubSeq_Longest_Common_SubSequence longest_Common_SubSequence = new C_LongNotUpSubSeq_Longest_Common_SubSequence();
        boolean isCorrect = true;
        for (int i = 0; i < 1000 && isCorrect; i++) {
            int[] inputArray = fileMethods.getRandomArray(fullFilePath);
            int[] dynamicProgArray = dynamic_Array.getNotUpSequenceLengthArray(inputArray);
            int dynamicArrayResultLength = dynamic_Array.getLargestNotUpSequenceLength(dynamicProgArray);
            int[][] dynamicProgMatrix = longest_Common_SubSequence.getLongestCommonSubSequenceMatrix(inputArray);
            int dynamicMatrixResultLength = longest_Common_SubSequence.getLargestNotUpSequenceLength(dynamicProgMatrix);
            isCorrect = dynamicArrayResultLength == dynamicMatrixResultLength;
            if(isCorrect) {
                int[] dynamicPositionsArray = dynamic_Array.getLargestNotUpSequencePositions(inputArray, dynamicProgArray);
                int[] dynamicPositionsMatrix = longest_Common_SubSequence.getLargestNotUpSequencePositions(inputArray, dynamicProgMatrix);
                if (dynamicPositionsArray != null && dynamicPositionsMatrix != null)
                    isCorrect = dynamicPositionsArray.length == dynamicArrayResultLength && dynamicPositionsMatrix.length == dynamicMatrixResultLength;
                int[] dynamicNotUpSequenceByArray = getArrayBePositions(inputArray, dynamicPositionsArray);
                int[] dynamicNotUpSequenceByMatrix = getArrayBePositions(inputArray, dynamicPositionsMatrix);
                isCorrect = isCorrect && isNotUpSortArray(dynamicNotUpSequenceByArray) && isNotUpSortArray(dynamicNotUpSequenceByMatrix);
            }
        }
        assertTrue("C failed", isCorrect);
    }

}
