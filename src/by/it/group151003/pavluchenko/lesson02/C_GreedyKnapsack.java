package by.it.group151003.pavluchenko.lesson02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class C_GreedyKnapsack {
    private static class Item implements Comparable<Item> {
        int cost;
        int weight;

        Item(int cost, int weight) {
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
            //тут может быть ваш компаратор

            return 0;

        }
    }

    double calc(File source) throws FileNotFoundException {
        Scanner input = new Scanner(source);
        int n = input.nextInt();
        int W = input.nextInt();
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(input.nextInt(), input.nextInt());
        }
        for (Item item:items) {
            System.out.println(item);
        }
        System.out.printf("Всего предметов: %d. Рюкзак вмещает %d кг.\n",n,W);

        double result = 0;
        for ( int i = 0; i < items.length - 1; i++)
        {
            int max = i;
            for ( int j = i + 1; j < items.length; j++)
            {
                if ((items[max].cost / items[max]. weight) < (items[j].cost / items[j]. weight))
                {
                    max = j;
                }
            }
            Item temp = items[i];
            items[i] = items[max];
            items[max] = temp;

        }
        int i = 0;
        while (i < items.length)
        {
            if (W >= items[i].weight)
            {
                W -= items[i].weight;
                result += items[i].cost;
            }
            else
            {
                result += items[i].cost / items[i].weight * W;
                W = 0;
            }
            i++;
        }

        System.out.printf("Удалось собрать рюкзак на сумму %f\n",result);
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        String root=System.getProperty("user.dir")+"/src/";
        File f=new File(root+"by/it/a_khmelev/lesson02/greedyKnapsack.txt");
        double costFinal=new C_GreedyKnapsack().calc(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость %f (время %d)",costFinal,finishTime - startTime);
    }
}