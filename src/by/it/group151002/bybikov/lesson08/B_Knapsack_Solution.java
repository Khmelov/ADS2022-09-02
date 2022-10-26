package by.it.group151002.bybikov.lesson08;

public class B_Knapsack_Solution {
    private int rowCount;
    private int colCount;
    private int[][] dynamicMatrix;

    private void setRowCount (int rowCount) {
        this.rowCount = rowCount;
    }

    private void setColCount (int colCount) {
        this.colCount = colCount;
    }

    private void setDynamicMatrix(int[][] dynamicMatrix) {
        this.dynamicMatrix = dynamicMatrix;
    }

    B_Knapsack_Solution() {
        this.setColCount(0);
        this.setRowCount(0);
        this.setDynamicMatrix(null);
    }

    private int getRowCount () {
        return this.rowCount;
    }

    private int getColCount () {
        return this.colCount;
    }

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

    private void createKnapsackDynamicMatrix (int knapsackSize, int[] barsWeightArray) {
        this.setColCount(knapsackSize + 1);
        this.setRowCount(barsWeightArray.length + 1);
        int[][] dynamicMatrix = new int[this.getRowCount()][this.getColCount()];
        for (int i = 0; i < this.getRowCount(); i++) {
            dynamicMatrix[i][0] = 0;
        }
        for (int i = 0; i < this.getColCount(); i++) {
            dynamicMatrix[0][i] = 0;
        }
        for (int rowIndex = 1; rowIndex < this.getRowCount(); rowIndex++) {
            for (int colIndex = 1; colIndex < this.getColCount(); colIndex++) {
                dynamicMatrix[rowIndex][colIndex] = dynamicMatrix[rowIndex - 1][colIndex];
                if (colIndex - barsWeightArray[rowIndex - 1] >= 0) {
                    dynamicMatrix[rowIndex][colIndex] = Math.max(dynamicMatrix[rowIndex][colIndex], barsWeightArray[rowIndex - 1] + dynamicMatrix[rowIndex - 1][colIndex - barsWeightArray[rowIndex - 1]]);
                }
            }
        }
        this.setDynamicMatrix(dynamicMatrix);
    }

    int getMaxKnapsackWeight (int knapsackSize, int[] goldBarsWeightArray) {
        if (!isValidParameters(knapsackSize, goldBarsWeightArray)) {
            return -1;
        }
        createKnapsackDynamicMatrix(knapsackSize, goldBarsWeightArray);
        return this.dynamicMatrix[this.getRowCount() - 1][this.getColCount() - 1];
    }
}
