package by.it.group151001.trybchik.lesson02;
/*
Даны
1) объем рюкзака 4
2) число возможных предметов 60
3) сам набор предметов
    100 50
    120 30
    100 50
Все это указано в файле (by/it/a_khmelev/lesson02/greedyKnapsack.txt)

Необходимо собрать наиболее дорогой вариант рюкзака для этого объема
Предметы можно резать на кусочки (т.е. алгоритм будет жадным)
 */
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
    void CostSort(Item[] items)
    {
        int i ;
        for ( i = 0; i<items.length-1;i++)
        {
            int min  = i;
            for (int j = i+1;j<items.length;j++)
            {
                int min_cost =items[min].cost/items[min].weight;
                int currentcost =  items[j].cost/items[j].weight;
                if (currentcost <= min_cost)
                {
                    min = j;
                }
            }
            Item tmp = new Item(0,0);
            tmp.cost = items[min].cost;
            tmp.weight = items[min].weight;
            items[min].weight = items[i].weight;
            items[min].cost = items[i].cost;
            items[i].cost = tmp.cost;
            items[i].weight = tmp.weight;
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


        CostSort(items);
        int i = items.length-1;
        int currentWeight = 0;
        double result = 0;
        while (currentWeight < W)
        {
            if (items[i].weight<=W-currentWeight)
            {
                currentWeight = currentWeight + items[i].weight;
                result = result + items[i].cost;
                i--;
            }
            else
            {
                double part_cost;
                part_cost = (W-currentWeight)*(items[i].cost/items[i].weight);
                result = result + part_cost;
                currentWeight = W;
                i--;
            }
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