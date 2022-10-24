package by.it.group151002.bybikov.lesson08;

public class C_Stairs_Solution {

    boolean isValidParameters(int[] stairsNumbersArray) {
        boolean isValid = true;
        if (stairsNumbersArray == null) {
            isValid = false;
        }
        else if (stairsNumbersArray.length < 1 || stairsNumbersArray.length > 100) {
            isValid = false;
        }
        else {
            for (int i = 0; i < stairsNumbersArray.length && isValid; i++) {
                if (stairsNumbersArray[i] < -10000 || stairsNumbersArray[i] > 10000) {
                    isValid = false;
                }
            }
        }
        return isValid;
    }

    private int[] createStairSumArray (int[] stairsNumbersArray) {
        int[] resultArray = new int[stairsNumbersArray.length + 1];
        resultArray[0] = 0;
        resultArray[1] = stairsNumbersArray[0];
        for (int resultCounter = 2; resultCounter < resultArray.length; resultCounter++) {
            resultArray[resultCounter] = stairsNumbersArray[resultCounter - 1] +
            Math.max(resultArray[resultCounter - 1], resultArray[resultCounter - 2]);
        }
        return stairsNumbersArray;
    }

    int getMaxStairSum (int[] stairsNumbersArray) {
        int[] stairSumArray = createStairSumArray(stairsNumbersArray);
        return stairSumArray[stairSumArray.length - 1];
    }
}
