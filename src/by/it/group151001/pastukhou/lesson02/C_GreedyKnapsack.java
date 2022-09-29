package by.it.group151001.pastukhou.lesson02;

/*
Даны
1) объем рюкзака 4
2) число возможных предметов 60
3) сам набор предметов
    60 20
    100 50
    120 30
    100 50
Все это указано в файле (by/it/a_khmelev/lesson02/greedyKnapsack.txt)

Необходимо собрать наиболее дорогой вариант рюкзака для этого объема
Предметы можно резать на кусочки (т.е. алгоритм будет жадным)
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

import static java.lang.Math.abs;
import static java.lang.Math.min;

public class C_GreedyKnapsack {
    private static class Item {
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

        public static Comparator<Item> ItemCWComparator = new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                double o = 1.0D * o1.cost / o1.weight - 1.0D * o2.cost / o2.weight;
                if (abs(o) <= 0.001D) return 0;
                    else if (o < -0.001D) return 1;
                        else return -1;
            }
        };
    }

    double calc(File source) throws FileNotFoundException {
        Scanner input = new Scanner(source);
        int n = input.nextInt();
        int W = input.nextInt();
        if (W <= 0) return -1;
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(input.nextInt(), input.nextInt());
        }
        Arrays.sort(items, Item.ItemCWComparator);
        for (Item item:items) {
            System.out.println(item);
        }
        System.out.printf("Всего предметов: %d. Рюкзак вмещает %d кг.\n",n,W);
        double result = 0;
        int i = 0;
        while(W > 0 && i < items.length) {
            result += (1.0D * items[i].cost / items[i].weight) * min(W, items[i].weight);
            W -= min(W, items[i].weight);
            i++;
        }
        System.out.printf("Удалось собрать рюкзак на сумму %f\n",result);
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        String root=System.getProperty("user.dir")+"/src/";
        File f=new File(root+"by/it/group151001/pastukhou/lesson02/greedyKnapsack3.txt");
        double costFinal=new C_GreedyKnapsack().calc(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость %f (время %d)",costFinal,finishTime - startTime);
    }
}