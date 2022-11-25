package by.it.group151003.mytnik.lesson08;

import java.io.InputStream;
import java.util.Scanner;

public class B_Knapsack {

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
        int n = items.length;
        int[][] maxWeight = new int[n + 1][weight + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= weight; j++) {
                maxWeight[i][j] = maxWeight[i - 1][j];

                if (items[i - 1] <= j) {
                    maxWeight[i][j] = Math.max(maxWeight[i][j], maxWeight[i - 1][j - items[i - 1]] + items[i - 1]);
                }
            }
        }

        return maxWeight[n][weight];
    }
}