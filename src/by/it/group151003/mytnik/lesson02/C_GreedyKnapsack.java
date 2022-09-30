package by.it.group151003.mytnik.lesson02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class C_GreedyKnapsack {
    private static class Item implements Comparable<Item> {
        int cost;
        int weight;

        public Item(int cost, int weight) {
            this.cost = cost;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "cost=" + cost +
                    ", weight=" + weight +
                    '}';
        }

        @Override
        public int compareTo(Item o) {
            return o.cost/o.weight - this.cost/this.weight;
        }
    }

    public double calc(File source) throws FileNotFoundException {
        Scanner input = new Scanner(source);
        int n = input.nextInt();
        int W = input.nextInt();
        Item[] items = new Item[n];

        for (int i = 0; i < n; i++) {
            items[i] = new Item(input.nextInt(), input.nextInt());
        }

        double result = 0;

        Arrays.sort(items);

        for (Item item : items) {
            if (W >= item.weight) {
                result += item.cost;
                W -= item.weight;
            } else {
                result += (double) item.cost / item.weight * W;
                break;
            }
        }

        return result;
    }
}