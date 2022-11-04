package by.it.group151003.savitski.lesson02;
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
    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();

        String root=System.getProperty("user.dir")+"/src/";

        File f=new File(root+"by/it/group151001/shlyk/lesson02/greedyKnapsack.txt");

        double costFinal=new C_GreedyKnapsack().calc(f);
        long finishTime = System.currentTimeMillis();

        System.out.printf("Общая стоимость %f (время %d)",costFinal,finishTime - startTime);
    }
    interface methodSort {
        void sort(int lBorder, int rBorder);
    }
    private static class Item implements Comparable<Item> {
        public static void sort(Item[] array) {
            methodSort self = new methodSort() {
                @Override
                public void sort(int lBorder, int rBorder) {
                    int i = lBorder;
                    int j = rBorder;

                    Item x = array[(i + j) / 2];

                    do {
                        while (x.compareTo(array[i]) < 0)
                            i++;
                        while (x.compareTo(array[j]) > 0)
                            j--;
                        if (i <= j) {
                            Item temp = array[i];
                            array[i] = array[j];
                            array[j] = temp;
                            j--;
                            i++;
                        }
                    } while (i < j);
                    if (i < rBorder) {
                        sort(i, rBorder);
                    }

                    if (j > lBorder) {
                        sort(lBorder, j);
                    }
                }
            };

            self.sort(0, array.length - 1);
        }
        final static int less = -1;
        final static int equal = 0;

        final static int greater = 1;
        int cost;
        int weight;

        float density;
        Item(int cost, int weight) {
            this.cost = cost;
            this.weight = weight;
            this.density = (float) cost / weight;
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
            int result = (int) (this.density - o.density);
            if (result != equal)
                result /= Math.abs(result);
            return result;
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

        Item.sort(items);

        if (W <= 0) {
            return 0;
        }

        int freeSpace = W;
        int currWeight;
        int iItem = 0;
        boolean isFull = false;
        double result = 0;

        while(!isFull){
            currWeight = items[iItem].weight;

            if (currWeight < freeSpace){
                result += items[iItem].cost;
                freeSpace -= currWeight;
            } else {
                result += (double) items[iItem].cost * freeSpace / currWeight;
                freeSpace = 0;
            }

            iItem++;
            isFull = (freeSpace == 0 || iItem >= items.length);
        }
        System.out.printf("Удалось собрать рюкзак на сумму %f\n",result);

        return result;
    }
}