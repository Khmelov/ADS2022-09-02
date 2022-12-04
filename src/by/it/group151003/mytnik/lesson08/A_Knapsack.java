package by.it.group151003.mytnik.lesson08;

import java.io.InputStream;
import java.util.Scanner;

public class A_Knapsack {
    public int getMaxWeight(InputStream stream) {
        Scanner scanner = new Scanner(stream);

        int w = scanner.nextInt();
        int n = scanner.nextInt();
        int[] gold = new int[n];

        for (int i = 0; i < n; i++) {
            gold[i] = scanner.nextInt();
        }

        return getMaxWeight(w, gold);
    }

    public int getMaxWeight(int weight, int[] items) {
        int[] maxWeight = new int[weight + 1];

        for (int item : items) {
            for (int j = 1; j <= weight; j++) {
                if (item <= j) {
                    maxWeight[j] = Math.max(maxWeight[j], maxWeight[j - item] + item);
                }
            }
        }

        return maxWeight[weight];
    }
}