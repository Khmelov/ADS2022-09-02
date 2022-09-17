package by.it.group151002.naftolsky.lesson02;
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

    void merge(Item[] arr, int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;
        Item[] L = new Item[n1];
        Item[] R = new Item[n2];
        int i, j, k;
        for (i = 0; i < n1; i++) {
            L[i] = arr[p + i];
        }
        for (j = 0; j < n2; j++) {
            R[j] = arr[q + 1 + j];
        }
        i = 0;
        j = 0;
        k = p;
        while (i < n1 && j < n2) {
            if (L[i].cost/(double)L[i].weight > R[j].cost/(double)R[j].weight) {
                arr[k++] = L[i++];
            }
            else {
                arr[k++] = R[j++];
            }
        }
        while (i < n1) {
            arr[k++] = L[i++];
        }
        while (j < n2) {
            arr[k++] = R[j++];
        }
    }

    //сортировка массива слиянием ("Разделяй и властвуй", сложность: О(n*lg(n)))
    void sort(Item[] arr, int p, int r) {
        if (p < r) {
            int mid = (p + r) / 2;
            sort(arr, p, mid);
            sort(arr, mid + 1, r);
            merge(arr, p, mid, r);
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

        //тут необходимо реализовать решение задачи
        //итогом является максимально воможная стоимость вещей в рюкзаке
        //вещи можно резать на кусочки (непрерывный рюкзак)
        double result = 0;
        //тут реализуйте алгоритм сбора рюкзака
        //будет особенно хорошо, если с собственной сортировкой
        //кроме того, можете описать свой компаратор в классе Item
        //ваше решение.
        sort(items, 0, items.length - 1);

        boolean isFull = false;
        int i = 0;
        while (!isFull && items.length > 0) {
            if (items[i].weight <= W) {
                result += items[i].cost;
                W = W - items[i].weight;
            }
            else {
                isFull = true;
                result += (double)items[i].cost * W / items[i].weight;
            }
            i++;
        }
        System.out.printf("Удалось собрать рюкзак на сумму %f\n",result);
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        String root=System.getProperty("user.dir")+"/src/";
        File f=new File(root+"by/it/group151002/naftolsky/lesson02/greedyKnapsack.txt");
        double costFinal=new C_GreedyKnapsack().calc(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость %f (время %d)",costFinal,finishTime - startTime);
    }
}