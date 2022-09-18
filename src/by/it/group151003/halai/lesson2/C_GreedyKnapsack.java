package by.it.group151003.halai.lesson2;

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

        int[] iArrProfits = new int[4];
        for (int i = 0; i < iArrProfits.length; i++)
        {
            iArrProfits[i] = items[i].cost / items[i].weight;
        }

        Comparator<Item> cmpProfit = (left, right) -> left.cost/left.weight < right.cost/right.weight ? -1 : left.cost/left.weight < right.cost/right.weight ? 1 : 0;
        Arrays.sort(items, cmpProfit.reversed());

        double result = 0;
        int iCapLeft = W;
        int indx =  0;
        while (iCapLeft  != 0)
        {
            int iMaxIndx = 0;
            for (int i = 1; i < iArrProfits.length; i++)
                iMaxIndx = iArrProfits[i] > iArrProfits[iMaxIndx] ? i : iMaxIndx;

            if (iCapLeft - items[iMaxIndx].weight >= 0)
                if (iCapLeft - items[indx++].weight >= 0)
                {
                    result += items[iMaxIndx].cost;
                    iArrProfits[iMaxIndx] = 0;
                    iCapLeft -= items[iMaxIndx].weight;
                    result += items[indx - 1].cost;
                    iCapLeft -= items[indx - 1].weight;
                }
                else
                {
                    result += iArrProfits[iMaxIndx] * iCapLeft;
                    result += items[indx - 1].cost / items[indx - 1].weight * iCapLeft;
                    iCapLeft = 0;
                }
        }


        System.out.printf("Удалось собрать рюкзак на сумму %f\n",result);
        return result;
    }
    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        String root=System.getProperty("user.dir")+"/src/";
        File f=new File(root+"by/it/group151003/halai/lesson2/greedyKnapsack.txt");
        double costFinal=new C_GreedyKnapsack().calc(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость %f (время %d)",costFinal,finishTime - startTime);
    }
}