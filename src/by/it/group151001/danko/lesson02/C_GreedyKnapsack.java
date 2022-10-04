package by.it.group151001.danko.lesson02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
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

        public double costPerUnitWeight () {
            return cost / (double) weight;
        }
    }

    double calc(File source) throws FileNotFoundException {
        Scanner input = new Scanner(source);
        int n = input.nextInt();      //сколько предметов в файле
        int W = input.nextInt();      //какой вес у рюкзака
        Item[] items = new Item[n];   //получим список предметов
        for (int i = 0; i < n; i++) { //создавая каждый конструктором
            items[i] = new Item(input.nextInt(), input.nextInt());
        }
        //покажем предметы
        for (Item item:items) {
            System.out.println(item);
        }
        System.out.printf("Всего предметов: %d. Рюкзак вмещает %d кг.\n",n,W);

        double result = 0;
        int itemsInKnapSack = 0; // Количество вещей в рюкзаке
        int weightAtPoint = 0; // Вес рюкзака в данный момент времени
        Arrays.sort(items, Comparator.comparingDouble(Item::costPerUnitWeight).reversed()); //
        for (Item item:items) {
            System.out.println(item);
        }
        while(W != weightAtPoint && itemsInKnapSack <= items.length) {
            if(W > weightAtPoint + items[itemsInKnapSack].weight) {
                weightAtPoint += items[itemsInKnapSack].weight;
                result += items[itemsInKnapSack].cost;

            }
            else {
                result += ((W - weightAtPoint) / (double) items[itemsInKnapSack].weight) * items[itemsInKnapSack].cost;
                weightAtPoint = W;
            }
            itemsInKnapSack++;
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