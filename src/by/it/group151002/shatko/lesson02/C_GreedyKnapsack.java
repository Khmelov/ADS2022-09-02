package by.it.group151002.shatko.lesson02;
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

    void quickSort(Item[] array, int low, int high) {
        if (array.length == 0)
            return;

        if (low >= high)
            return;

        int middleI = low + (high - low) / 2;
        int middle = array[middleI].cost / array[middleI].weight;

        int i = low, j = high;
        while (i <= j) {
            while (array[i].cost / array[i].weight > middle) {
                i++;
            }

            while (array[j].cost / array[j].weight < middle) {
                j--;
            }

            if (i <= j) {
                Item temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        if (low < j)
            quickSort(array, low, j);

        if (high > i)
            quickSort(array, i, high);
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
        int low = 0;
        int high = items.length - 1;
        int size = 60;
        int i = 0;
        quickSort(items, low, high);

        while (size > 0) {
            if (size >= items[i].weight) {
                result += items[i].cost;
                size -= items[i].weight;
            } else {
                int costPerWeight = items[i].cost / items[i].weight;
                result += costPerWeight * size;
                size = 0;
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