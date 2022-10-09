package by.it.group151002.protchenko.lesson02;
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
            return this.cost - o.cost;
        }
    }

    Item[] merge(Item[] arrA, Item[] arrB) {
        Item[] arr = new Item[arrA.length + arrB.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < arrA.length && j < arrB.length) {
            if (((double)arrA[i].cost / arrA[i].weight) > ((double)arrB[j].cost / arrB[j].weight)) {
                arr[k++] = arrA[i++];
            } else {
                arr[k++] = arrB[j++];
            }
        }
        while (i < arrA.length) {
            arr[k++] = arrA[i++];
        }
        while (j < arrB.length) {
            arr[k++] = arrB[j++];
        }
        return arr;
    }
    Item[] sort(Item[] arr) {
        if (arr.length < 2) {
            return arr;
        }

        Item[] arrA = new Item[arr.length / 2];
        System.arraycopy(arr, 0, arrA, 0, arr.length/2);
        Item[] arrB = new Item[arr.length - arr.length / 2];
        System.arraycopy(arr, arr.length/2, arrB, 0, arr.length - arr.length / 2);

        arrA = sort(arrA);
        arrB = sort(arrB);
        return merge(arrA, arrB);
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

        items = sort(items);

        double result = 0;
        int leftWeight = W;
        int i = 0;
        while (leftWeight != 0 || i < items.length) {
            if (leftWeight > items[i].weight) {
                result += items[i].cost;
                leftWeight -= items[i].weight;
            } else {
                result += (double)items[i].cost * leftWeight / items[i].weight;
                leftWeight = 0;
            }
            i++;
        }

        //тут необходимо реализовать решение задачи
        //итогом является максимально воможная стоимость вещей в рюкзаке
        //вещи можно резать на кусочки (непрерывный рюкзак)

        //тут реализуйте алгоритм сбора рюкзака
        //будет особенно хорошо, если с собственной сортировкой
        //кроме того, можете описать свой компаратор в классе Item
        //ваше решение.

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