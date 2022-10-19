package by.it.group151002.bybikov.lesson06;
import java.io.*;
import java.util.Scanner;

public class FileMethods {

    private void setArrayDataFile(String outputFilePath, int arrayLength, int lowerBound, int upperBound) throws IOException {
        FileWriter fileWriter = new FileWriter(outputFilePath);
        RandomValuesMethods randomValues = new RandomValuesMethods();
        fileWriter.write(arrayLength + "\n");
        for (int i = 0; i < arrayLength; i++) {
            int value = randomValues.getIntRandomValueInRange(lowerBound, upperBound);
            fileWriter.write(value + " ");
        }
        fileWriter.close();
    }

    private int[] getArrayDataFile (String inputFilePath) throws IOException{
        FileReader fileReader = new FileReader(inputFilePath);
        Scanner scanner = new Scanner(fileReader);
        int arrayLength = scanner.nextInt();
        int[] resultArray = new int[arrayLength];
        for (int i = 0 ; i < resultArray.length; i++) {
            resultArray[i] = scanner.nextInt();
        }
        fileReader.close();
        return resultArray;
    }

    int[] getRandomArray(String fullFilePath) throws IOException {
        RandomValuesMethods randomValues = new RandomValuesMethods();
        int arrayLength = randomValues.getIntRandomValueInRange(0, 1000);
        int lowerBound = randomValues.getIntRandomValueInRange(0, 10);
        int upperBound = randomValues.getIntRandomValueInRange(11, 100);
        FileMethods fileMethods = new FileMethods();
        fileMethods.setArrayDataFile(fullFilePath, arrayLength, lowerBound, upperBound);
        return fileMethods.getArrayDataFile(fullFilePath);
    }
}

