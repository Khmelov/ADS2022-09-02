package by.it.group151003.alamov.lesson08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class B_Knapsack {

    int getMaxWeight(InputStream stream) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        Scanner scanner = new Scanner(stream);
        int weightKnapsack = scanner.nextInt();
        int amountParts = scanner.nextInt();
        int[] arrGold = new int[amountParts];

        for (int i = 0; i < amountParts; i++) {
            arrGold[i] = scanner.nextInt();
        }

        int [][] maxWeight = new int[amountParts + 1][weightKnapsack + 1];

        for(int i = 1; i < amountParts + 1; i++){
            for ( int j = 1; j < weightKnapsack + 1; j++){
                if (arrGold[i - 1] > j){
                    maxWeight[i][j] = maxWeight[i - 1][j];
                }else{
                    maxWeight[i][j] = Integer.max(maxWeight[i - 1][j],maxWeight[i - 1][j - arrGold[i - 1]] + arrGold[i - 1]);
                }
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return maxWeight[amountParts][weightKnapsack];
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson08/dataB.txt");
        B_Knapsack instance = new B_Knapsack();
        int res = instance.getMaxWeight(stream);
        System.out.println(res);
    }

}
