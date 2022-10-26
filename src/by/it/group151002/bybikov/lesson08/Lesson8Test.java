package by.it.group151002.bybikov.lesson08;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Lesson8Test {
    @Test
    public void A() throws Exception {
        A_Knapsack_Solution A_Solution = new A_Knapsack_Solution();
        FileMethods fileMethods = new FileMethods();
        String root = System.getProperty("user.dir") + "/src/";
        String fullFilePath =root + "by/it/group151002/bybikov/lesson08/dataA.txt";
        int knapsackSize, resultKnapsackWeight, instanceKnapsackWeight;
        int[] barsWeightArray;
        boolean isCorrect = true;
        for (int i = 0; i < 10000 && isCorrect; i++) {
            fileMethods.set_A_B_Tasks_Data_File(fullFilePath);
            knapsackSize = fileMethods.getKnapsackSize(fullFilePath);
            barsWeightArray = fileMethods.getGoldBarsWeightArray(fullFilePath);
            System.out.println("Knapsack Size = " + knapsackSize);
            System.out.println("Array equal : " + Arrays.toString(barsWeightArray));
            resultKnapsackWeight = A_Solution.getMaxKnapsackWeight(knapsackSize, barsWeightArray);
            instanceKnapsackWeight = A_Solution.instanceMaxKnapsackWeight(knapsackSize, barsWeightArray);
            isCorrect = resultKnapsackWeight == instanceKnapsackWeight && resultKnapsackWeight != -1;
        }
        assertTrue("A failed", isCorrect);
    }

    @Test
    public void B() throws Exception {
        B_Knapsack_Solution B_Solution = new B_Knapsack_Solution();
        FileMethods fileMethods = new FileMethods();
        String root = System.getProperty("user.dir") + "/src/";
        String fullFilePath =root + "by/it/group151002/bybikov/lesson08/dataB.txt";
        int knapsackSize, resultKnapsackWeight, instanceKnapsackWeight;
        int[] barsWeightArray;
        boolean isCorrect = true;
        for (int i = 0; i < 10000 && isCorrect; i++) {
            fileMethods.set_A_B_Tasks_Data_File(fullFilePath);
            knapsackSize = fileMethods.getKnapsackSize(fullFilePath);
            barsWeightArray = fileMethods.getGoldBarsWeightArray(fullFilePath);
            System.out.println("Knapsack Size = " + knapsackSize);
            System.out.println("Array equal : " + Arrays.toString(barsWeightArray));
            resultKnapsackWeight = B_Solution.getMaxKnapsackWeight(knapsackSize, barsWeightArray);
            instanceKnapsackWeight = B_Solution.compareRecurrentMaxKnapsackWeight(knapsackSize, barsWeightArray);
            isCorrect = resultKnapsackWeight == instanceKnapsackWeight && resultKnapsackWeight != -1;

        }
        assertTrue("A failed", isCorrect);
    }

    @Test
    public void C() throws Exception {
        C_Stairs_Solution solution = new C_Stairs_Solution();
        FileMethods fileMethods = new FileMethods();
        String root = System.getProperty("user.dir") + "/src/";
        String fullFilePath =root + "by/it/group151002/bybikov/lesson08/dataC.txt";
        int [] stairsNumbersArray;
        int resultSum;
        int instanceSum;
        boolean isCorrect = true;
        for (int i = 0; i < 10000 && isCorrect; i++) {
            fileMethods.set_C_Tasks_Data_File(fullFilePath);
            stairsNumbersArray = fileMethods.getStairsNumbersArray(fullFilePath);
            System.out.println("Stairs numbers array equal : " + Arrays.toString(stairsNumbersArray));
            isCorrect = solution.isValidParameters(stairsNumbersArray);
            resultSum = solution.getMaxStairSum(stairsNumbersArray);
            instanceSum = solution.instanceMaxStairSum(stairsNumbersArray);
            isCorrect = isCorrect && resultSum == instanceSum;
        }
        assertTrue("C failed", isCorrect);
    }

}
