package by.it.group151004.afanasenko.lesson02;

import java.io.*;
import java.util.Scanner;

public class C_GreedyKnapsack {
    private static class Item implements Comparable<C_GreedyKnapsack.Item> {
        int cost;
        int weight;

        Item(int cost, int weight) {
            this.cost = cost;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Item{" + "cost=" + cost + ", weight=" + weight + '}';
        }

        @Override
        public int compareTo(C_GreedyKnapsack.Item o) {
            return 0;
        }
    }

    public double calc(File source) throws FileNotFoundException {
        try (Scanner input = new Scanner(source)) {
            int n = input.nextInt();
            int W = input.nextInt();
            C_GreedyKnapsack.Item[] items = new C_GreedyKnapsack.Item[n];
            for (int i = 0; i < n; i++) {
                items[i] = new C_GreedyKnapsack.Item(input.nextInt(), input.nextInt());
            }
            for (C_GreedyKnapsack.Item item : items) {
                System.out.println(item);
            }
            System.out.printf("Всего предметов: %d. Рюкзак вмещает %d кг.\n", n, W);

            double result = 0;

            for (C_GreedyKnapsack.Item item : items) {
                double max = 60 / item.weight * item.cost;
                if (max > result) {
                    result = max;
                }
            }
            System.out.printf("Удалось собрать рюкзак на сумму %f\n", result);
            return result;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "greedyKnapsack.txt");
        double costFinal = new C_GreedyKnapsack().calc(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость %f (время %d)", costFinal, finishTime - startTime);
    }
}