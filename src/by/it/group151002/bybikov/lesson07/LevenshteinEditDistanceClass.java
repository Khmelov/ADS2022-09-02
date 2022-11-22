package by.it.group151002.bybikov.lesson07;

public class LevenshteinEditDistanceClass {

    private int destMatrixLength;
    private int sourceMatrixLength;
    private int[][] editDistanceMatrix;

    private void setEditDistanceMatrix(int[][] editDistanceMatrix) {
        this.editDistanceMatrix = editDistanceMatrix;
    }

    private int[][] getEditDistanceMatrix () {
        return this.editDistanceMatrix;
    }

    private void setDestMatrixLength(int destMatrixLength) {
        this.destMatrixLength = destMatrixLength;
    }

    private void setSourceMatrixLength(int sourceMatrixLength) {
        this.sourceMatrixLength = sourceMatrixLength;
    }

    private int getDestMatrixLength() {
        return this.destMatrixLength;
    }

    private int getSourceMatrixLength() {
        return this.sourceMatrixLength;
    }

    void setPrivateLevenshteinEditDistanceMatrix(String sourceStr, String destStr) {
        if (sourceStr == null || destStr == null)
            return;
        this.setDestMatrixLength(destStr.length() + 1);
        this.setSourceMatrixLength(sourceStr.length() + 1);
        int[][] editDistanceMatrix = new int[destStr.length() + 1][sourceStr.length() + 1];
        for (int i = 0; i < this.getSourceMatrixLength(); i++) {
            editDistanceMatrix[0][i] = i;
        }
        for (int i = 0; i < this.getDestMatrixLength(); i++) {
            editDistanceMatrix[i][0] = i;
        }
        for (int destCounter = 1; destCounter < this.getDestMatrixLength(); destCounter++) {
            for (int sourceCounter = 1; sourceCounter < this.getSourceMatrixLength(); sourceCounter++) {
                if(destStr.charAt(destCounter - 1) == sourceStr.charAt(sourceCounter - 1)) {
                    editDistanceMatrix[destCounter][sourceCounter] = editDistanceMatrix[destCounter - 1][sourceCounter - 1];
                }
                else {
                    editDistanceMatrix[destCounter][sourceCounter] = editDistanceMatrix[destCounter - 1][sourceCounter - 1] + 1;
                    if (Math.min(editDistanceMatrix[destCounter][sourceCounter - 1], editDistanceMatrix[destCounter - 1][sourceCounter]) + 1 < editDistanceMatrix[destCounter][sourceCounter]) {
                        editDistanceMatrix[destCounter][sourceCounter] = Math.min(editDistanceMatrix[destCounter][sourceCounter - 1], editDistanceMatrix[destCounter - 1][sourceCounter]) + 1;
                    }
                }
            }
        }
        this.editDistanceMatrix = editDistanceMatrix;
    }

    int getLowerEditDistanceFromMatrix () {
        if (this.editDistanceMatrix == null)
            return 0;
        return this.editDistanceMatrix[this.getDestMatrixLength() - 1][this.getSourceMatrixLength() - 1];
    }

    String getEditSequence (String sourceStr, String destStr) {
        StringBuilder currentStringBuilder = new StringBuilder();
        int editSequenceArrayLength = getLowerEditDistanceFromMatrix();
        String[] editArray = new String[editSequenceArrayLength];
        int editArrayIndex = editArray.length;
        int sourceIndex = this.getSourceMatrixLength() - 1;
        int destIndex = this.getDestMatrixLength() - 1;
        while (sourceIndex > 0 && destIndex > 0) {
            currentStringBuilder.setLength(0);
            if (sourceStr.charAt(sourceIndex) == destStr.charAt(destIndex)) {
                currentStringBuilder.append("#,");
            }
        }
        // remember change return statement
        return null;
    }

    private int recurrentFormulasMethod (String sourceStr, String destStr, int sourceLength, int destLength) {
        if (sourceLength == 0 || destLength == 0) {
            return sourceLength + destLength;
        }
        int result;
        if (sourceStr.charAt(sourceLength - 1) == destStr.charAt(destLength - 1)) {
            result = recurrentFormulasMethod(sourceStr, destStr, sourceLength - 1, destLength - 1);
        }
        else {
            result = recurrentFormulasMethod(sourceStr, destStr, sourceLength - 1, destLength - 1) + 1;
            int tmp = Math.min(recurrentFormulasMethod(sourceStr, destStr, sourceLength - 1, destLength), recurrentFormulasMethod(sourceStr, destStr, sourceLength, destLength - 1)) + 1;
            if (tmp < result) {
                result = tmp;
            }
        }
        return result;
    }

    int recurrentFormulas (String sourceStr, String destStr) {
        if (sourceStr == null || destStr == null)
            return 0;
        return recurrentFormulasMethod(sourceStr, destStr, sourceStr.length(), destStr.length());
    }

}
