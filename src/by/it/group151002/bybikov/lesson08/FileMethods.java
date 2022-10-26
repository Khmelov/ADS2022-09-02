package by.it.group151002.bybikov.lesson08;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileMethods {

     void set_A_B_Tasks_Data_File(String outputFilePath) throws IOException {
        FileWriter fileWriter = new FileWriter(outputFilePath);
        RandomValuesMethods randomValues = new RandomValuesMethods();
        int knapsackSize = randomValues.getIntRandomValueInRange(1, 100000);
        int goldBarsAmount = randomValues.getIntRandomValueInRange(1, 20);
        fileWriter.write(knapsackSize + " ");
        fileWriter.write(goldBarsAmount + "\n");
        for (int i = 0; i < goldBarsAmount; i++) {
            int goldBarWeight = randomValues. getIntRandomValueInRange(0, 100000);
            fileWriter.write(goldBarWeight + " ");
        }
        fileWriter.close();
    }

    void set_C_Tasks_Data_File(String outputFilePath) throws IOException {
        FileWriter fileWriter = new FileWriter(outputFilePath);
        RandomValuesMethods randomValues = new RandomValuesMethods();
        int stairsAmount = randomValues.getIntRandomValueInRange(1, 20);
        fileWriter.write(stairsAmount + "\n");
        for (int i = 0; i < stairsAmount; i++) {
            int currentStairNumber = randomValues. getIntRandomValueInRange(-10000, 10000);
            fileWriter.write(currentStairNumber + " ");
        }
        fileWriter.close();
    }

    int[] getStairsNumbersArray (String inputFilePath) throws IOException {
        FileReader fileReader = new FileReader(inputFilePath);
        Scanner scanner = new Scanner(fileReader);
        int stairsAmount = scanner.nextInt();
        int[] stairsNumbersArray = new int[stairsAmount];
        for (int i = 0; i < stairsNumbersArray.length; i++) {
            stairsNumbersArray[i] = scanner.nextInt();
        }
        fileReader.close();
        return stairsNumbersArray;
    }

    int getKnapsackSize (String inputFilePath) throws IOException{
        FileReader fileReader = new FileReader(inputFilePath);
        Scanner scanner = new Scanner(fileReader);
        int knapsackSize = scanner.nextInt();
        fileReader.close();
        return knapsackSize;
    }

    int[] getGoldBarsWeightArray (String inputFilePath) throws IOException {
        FileReader fileReader = new FileReader(inputFilePath);
        Scanner scanner = new Scanner(fileReader);
        int knapsackSize = scanner.nextInt();
        int goldBarsAmount = scanner.nextInt();
        int[] goldBarsWeightArray = new int[goldBarsAmount];
        for (int i = 0; i < goldBarsWeightArray.length; i++) {
            goldBarsWeightArray[i] = scanner.nextInt();
        }
        fileReader.close();
        return goldBarsWeightArray;
    }

}

