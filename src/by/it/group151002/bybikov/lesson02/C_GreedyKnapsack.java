package by.it.group151002.bybikov.lesson02;
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
        double unitCost;

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
            return 0;
        }
    }

    void sortItems (Item[] items) {
        for (int i = 1; i < items.length; i++) {
            int j = i;
            boolean wasExchange;
            do {
                wasExchange = false;
                if(items[j].unitCost > items[j - 1].unitCost) {
                    Item tmp = items[j];
                    items[j] = items[j - 1];
                    items[j - 1] = tmp;
                    wasExchange = true;
                }
                j--;
            }while (wasExchange && j > 0);
        }
    }

    double calc(File source) throws FileNotFoundException {
        Scanner input = new Scanner(source);
        int n = input.nextInt();      //сколько предметов в файле
        int backpackSpace = input.nextInt();      //какой вес у рюкзака
        Item[] items = new Item[n];   //получим список предметов
        for (int i = 0; i < n; i++) { //создавая каждый конструктором
            items[i] = new Item(input.nextInt(), input.nextInt());
            items[i].unitCost = (double)items[i].cost / (double) items[i].weight;
        }
        sortItems(items);
        //покажем предметы
        for (Item item:items) {
            System.out.println(item);
        }
        System.out.printf("Всего предметов: %d. Рюкзак вмещает %d кг.\n",n, backpackSpace);

        //тут необходимо реализовать решение задачи
        //итогом является максимально воможная стоимость вещей в рюкзаке
        //вещи можно резать на кусочки (непрерывный рюкзак)
        //тут реализуйте алгоритм сбора рюкзака
        //будет особенно хорошо, если с собственной сортировкой
        //кроме того, можете описать свой компаратор в классе Item
        //ваше решение.
        double backpackPrice = 0;
        int i = 0;
        for (; i < n && backpackSpace > 0; i++) {
            backpackPrice = backpackPrice + items[i].cost;
            backpackSpace = backpackSpace - items[i].weight;
        }
        i--;
        if (backpackSpace < 0)
            backpackPrice = backpackPrice + backpackSpace * items[i].unitCost;
        System.out.printf("Удалось собрать рюкзак на сумму %f\n", backpackPrice);
        return backpackPrice;
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