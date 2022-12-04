package by.it.group151002.bybikov.lesson07;

public class C_Edit_Distance_Solution {

    private int rowCount;
    private int colCount;
    private int[][] levenshteinMatrix;

    static final int EQUAL = 0;
    static final int REPLACE = 1;
    static final int REMOVE = 2;
    static final int INSERT = 3;

    C_Edit_Distance_Solution () {
        this.levenshteinMatrix = null;
        this.colCount = 0;
        this.rowCount = 0;
    }

    private void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    private void setColCount(int colCount) {
        this.colCount = colCount;
    }

    private int getRowCount() {
        return  this.rowCount;
    }

    private int getColCount () {
        return this.colCount;
    }

    private int[][] getLevenshteinMatrix () {
        return this.levenshteinMatrix;
    }

    private boolean isCorrectParameters (String sourceString, String destString) {
        if (sourceString == null || destString == null) {
            return false;
        }
        boolean isCorrect = true;
        if (sourceString.length() == 0 || sourceString.length() > 100 || destString.length() == 0 || destString.length() > 100) {
            isCorrect = false;
        }
        return isCorrect;
    }

    private void generateLevenshteinMatrix(String sourceString, String destString){
        this.setColCount(sourceString.length() + 1);
        this.setRowCount(destString.length() + 1);
        this.levenshteinMatrix = new int[this.getRowCount()][this.getColCount()];
        for (int i = 0; i < this.getColCount(); i++) {
            this.levenshteinMatrix[0][i] = i;
        }
        for (int i = 0; i < this.getRowCount(); i++) {
            this.levenshteinMatrix[i][0] = i;
        }
        // col == sourceString
        // row == destString
        for (int rowIndex = 1; rowIndex < this.getRowCount(); rowIndex++) {
            for (int colIndex = 1; colIndex < this.getColCount(); colIndex++) {
                this.levenshteinMatrix[rowIndex][colIndex] = this.levenshteinMatrix[rowIndex - 1][colIndex - 1] + 1;
                if (sourceString.charAt(colIndex - 1) == destString.charAt(rowIndex - 1)) {
                    this.levenshteinMatrix[rowIndex][colIndex] = this.levenshteinMatrix[rowIndex - 1][colIndex - 1];
                }
                int tmp = Math.min(this.levenshteinMatrix[rowIndex - 1][colIndex], this.levenshteinMatrix[rowIndex][colIndex - 1]) + 1;
                this.levenshteinMatrix[rowIndex][colIndex] = Math.min(this.levenshteinMatrix[rowIndex][colIndex], tmp);
            }
        }
    }

    void setLevenshteinMatrix (String sourceString, String destString) {
        if (!isCorrectParameters(sourceString, destString)) {
            this.levenshteinMatrix = null;
            return;
        }
        this.generateLevenshteinMatrix(sourceString, destString);
    }

    int getLowerEditDistanceFromMatrix () {
        if (this.levenshteinMatrix == null){
            return -1;
        }
        return this.levenshteinMatrix[getRowCount() - 1][getColCount() - 1];
    }

    private void addCurrentStepStringBuilder (StringBuilder stringBuilder, int state, char sourceChar, char destChar) {
        stringBuilder.append(",");
        if (state == EQUAL){
            stringBuilder.append("#");
        }
        if (state == REPLACE) {
            stringBuilder.append(destChar);
            stringBuilder.append("~");
        }
        if (state == REMOVE) {
            stringBuilder.append(sourceChar);
            stringBuilder.append("-");
        }
        if (state == INSERT) {
            stringBuilder.append(destChar);
            stringBuilder.append("+");
        }
    }

    String getEditSequence (String sourceString, String destString) {
        if (this.levenshteinMatrix == null) {
            return null;
        }
        // col == sourceString
        // row == destString
        StringBuilder stringBuilder = new StringBuilder();
        int rowIndex = this.getRowCount() - 1;
        int colIndex = this.getColCount() - 1;
        int state;
        while (rowIndex > 0 && colIndex > 0) {
            if (sourceString.charAt(colIndex - 1) == destString.charAt(rowIndex - 1)) {
                state = EQUAL;
            }
            else {
                state = REPLACE;
                if (this.levenshteinMatrix[rowIndex - 1][colIndex] <= this.levenshteinMatrix[rowIndex][colIndex - 1]){
                    if (this.levenshteinMatrix[rowIndex - 1][colIndex] < this.levenshteinMatrix[rowIndex - 1][colIndex - 1]) {
                        state = INSERT;
                    }
                }
                else {
                    if (this.levenshteinMatrix[rowIndex][colIndex - 1] < this.levenshteinMatrix[rowIndex - 1][colIndex - 1]) {
                        state = REMOVE;
                    }
                }
            }
            addCurrentStepStringBuilder(stringBuilder, state, sourceString.charAt(colIndex - 1), destString.charAt(rowIndex - 1));
            if (state == EQUAL || state == REPLACE) {
                rowIndex--;
                colIndex--;
            }
            else if (state == REMOVE) {
                colIndex--;
            }
            else {
                rowIndex--;
            }
        }
        while (rowIndex > 0) {
            state = INSERT;
            addCurrentStepStringBuilder(stringBuilder, state, destString.charAt(rowIndex - 1), destString.charAt(rowIndex - 1));
            rowIndex--;
        }
        while (colIndex > 0) {
            state = REMOVE;
            addCurrentStepStringBuilder(stringBuilder, state, sourceString.charAt(colIndex - 1), sourceString.charAt(colIndex -1));
            colIndex--;
        }
        return stringBuilder.reverse().toString();

    }

}
