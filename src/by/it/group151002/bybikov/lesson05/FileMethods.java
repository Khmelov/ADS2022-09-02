package by.it.group151002.bybikov.lesson05;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileMethods {

    int[] setEventArrayDataFile(String fullWritePath, int arrayLength, int lowerBound, int upperBound) throws IOException {
        FileWriter fileWriter = new FileWriter(fullWritePath);
        RandomValuesMethods randomValues = new RandomValuesMethods();
        int[] writeArray = new int[arrayLength];
        fileWriter.write(arrayLength + "\n");
        for (int i = 0; i < arrayLength; i++) {
            int value = randomValues.getIntRandomValueInRange(lowerBound, upperBound);
            fileWriter.write(value + " ");
            writeArray[i] = value;
        }
        fileWriter.close();
        return writeArray;
    }

    void setObservationArrayDataFile(String fullWritePath, int arrayLength, int lowerBound, int upperBound) throws IOException {
        FileWriter fileWriter = new FileWriter(fullWritePath);
        RandomValuesMethods randomValues = new RandomValuesMethods();
        fileWriter.write(arrayLength + "\n");
        for (int i = 0; i < arrayLength; i++) {
            int value = randomValues.getIntRandomValueInRange(lowerBound, upperBound);
            fileWriter.write(value + " ");
            value = randomValues.getIntRandomValueInRange(lowerBound, upperBound);
            fileWriter.write(value + " ");
        }
        fileWriter.close();
    }

    int[] getEventsArrayDataFile (String fullWritePath) throws IOException{
        FileReader fileReader = new FileReader(fullWritePath);
        Scanner scanner = new Scanner(fileReader);
        int arrayLength = scanner.nextInt();
        int[] resultArray = new int[arrayLength];
        for (int i = 0 ; i < resultArray.length; i++) {
            resultArray[i] = scanner.nextInt();
        }
        fileReader.close();
        return resultArray;
    }
}
