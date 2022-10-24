package by.it.group151002.bybikov.lesson08;

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

}
