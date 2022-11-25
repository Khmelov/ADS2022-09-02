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
        return resultArray;
    }

    int getMaxStairSum (int[] stairsNumbersArray) {
        if (!this.isValidParameters(stairsNumbersArray)) {
            return 0;
        }
        int[] stairSumArray = createStairSumArray(stairsNumbersArray);
        return stairSumArray[stairSumArray.length - 1];
    }
    
    private int instanceRecurrentMethod (int[] stairsNumbersArray, int currentStairIndex) {
        if (currentStairIndex < 0)
            return 0;
        int result = instanceRecurrentMethod(stairsNumbersArray, currentStairIndex - 1);
        result = Math.max(result, instanceRecurrentMethod(stairsNumbersArray, currentStairIndex - 2) );
        return result + stairsNumbersArray[currentStairIndex];
    }
    
    int instanceMaxStairSum (int[] stairsNumbersArray) {
        if (!this.isValidParameters(stairsNumbersArray)) {
            return 0;
        }
        return instanceRecurrentMethod(stairsNumbersArray, stairsNumbersArray.length -1);
    }
}
