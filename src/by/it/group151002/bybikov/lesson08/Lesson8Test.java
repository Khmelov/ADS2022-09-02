package by.it.group151002.bybikov.lesson08;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Lesson8Test {
    @Test
    public void A() throws Exception {
        A_Knapsack_Solution A_Solution = new A_Knapsack_Solution();
        FileMethods fileMethods = new FileMethods();
        String root = System.getProperty("user.dir") + "/src/";
        String fullFilePath =root + "by/it/group151002/bybikov/lesson08/dataA.txt";
        int knapsackSize, resultKnapsackWeight = 0;
        int[] barsWeightArray;
        boolean isCorrect = true;
        for (int i = 0; i < 100 && isCorrect; i++) {
            fileMethods.set_A_B_Tasks_Data_File(fullFilePath);
            knapsackSize = fileMethods.getKnapsackSize(fullFilePath);
            barsWeightArray = fileMethods.getGoldBarsWeightArray(fullFilePath);
            resultKnapsackWeight = A_Solution.getMaxKnapsackWeight(knapsackSize, barsWeightArray);
            if (resultKnapsackWeight == -1 || resultKnapsackWeight > knapsackSize) {
                isCorrect = false;
            }

        }
        assertTrue("A failed", isCorrect);
    }

    @Test
    public void B() throws Exception {
        B_Knapsack_Solution B_Solution = new B_Knapsack_Solution();
        FileMethods fileMethods = new FileMethods();
        String root = System.getProperty("user.dir") + "/src/";
        String fullFilePath =root + "by/it/group151002/bybikov/lesson08/dataB.txt";
        int knapsackSize, resultKnapsackWeight = 0;
        int[] barsWeightArray;
        boolean isCorrect = true;
        for (int i = 0; i < 100 && isCorrect; i++) {
            //fileMethods.set_A_B_Tasks_Data_File(fullFilePath);
            knapsackSize = fileMethods.getKnapsackSize(fullFilePath);
            barsWeightArray = fileMethods.getGoldBarsWeightArray(fullFilePath);
            resultKnapsackWeight = B_Solution.getMaxKnapsackWeight(knapsackSize, barsWeightArray);
            if (resultKnapsackWeight > knapsackSize || resultKnapsackWeight != 9) {
                isCorrect = false;
            }


        }
        assertTrue("A failed", isCorrect);
    }

    @Test
    public void C() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        String fullFilePath =root + "by/it/group151002/bybikov/lesson08/dataC.txt";
        //assertEquals("C failed", res, 3);
    }

}
