package by.it.group151003.alamov.lesson08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class A_Knapsack {

    int getMaxWeight(InputStream stream) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        Scanner scanner = new Scanner(stream);
        int weightKnapsack = scanner.nextInt();
        int amountParts = scanner.nextInt();
        int[] arrGold = new int[amountParts];

        for (int i = 0; i < amountParts; i++) {
            arrGold[i] = scanner.nextInt();
        }

        int[] maxWeight = new int[weightKnapsack + 1];
        Arrays.fill(maxWeight, 0);

        for (int i = 0; i < amountParts; ++i) {
            for (int weight = arrGold[i]; weight <= weightKnapsack; ++weight) {
                if (arrGold[i] <= weight)
                    maxWeight[weight] = Math.max(maxWeight[weight], maxWeight[weight - arrGold[i]] + arrGold[i]);
            }
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return maxWeight[weightKnapsack];
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson08/dataA.txt");
        A_Knapsack instance = new A_Knapsack();
        int res = instance.getMaxWeight(stream);
        System.out.println(res);
    }
}
