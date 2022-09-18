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

    int partition(double[] arrSpecific, Item[] arr, int lowIndex, int highIndex) {
        double pivotValue = arrSpecific[lowIndex + highIndex / 2];

        int i = lowIndex - 1;
        int j = highIndex + 1;
        while (true)
        {
            do {
                i++;
            } while (arrSpecific[i] > pivotValue);

            do {
                j--;
            } while (arrSpecific[j] < pivotValue);

            if (i >= j) {
                return j;
            }

            double tempNumber = arrSpecific[i];
            arrSpecific[i] = arrSpecific[j];
            arrSpecific[j] = tempNumber;

            Item tempItem = arr[i];
            arr[i] = arr[j];
            arr[j] = tempItem;
        }
    }

    void quickSort(double[] arrSpecific, Item[] arr, int  lowIndex, int highIndex) {
        if (lowIndex >= highIndex) {
            return;
        }
        int pivotIndex = partition(arrSpecific, arr, lowIndex, highIndex);
        quickSort(arrSpecific, arr, lowIndex, pivotIndex);
        quickSort(arrSpecific, arr, pivotIndex + 1, highIndex);
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
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.printf("Всего предметов: %d. Рюкзак вмещает %d кг.\n", n, W);

        //тут необходимо реализовать решение задачи
        //итогом является максимально воможная стоимость вещей в рюкзаке
        //вещи можно резать на кусочки (непрерывный рюкзак)
        double result = 0;
        //тут реализуйте алгоритм сбора рюкзака
        //будет особенно хорошо, если с собственной сортировкой
        //кроме того, можете описать свой компаратор в классе Item
        //ваше решение.
        double[] arrSpecific = new double[items.length];
        for (int i = 0; i < arrSpecific.length; i++) {
            arrSpecific[i] = (double) items[i].cost / items[i].weight;
        }

        quickSort(arrSpecific, items, 0, items.length - 1);
        boolean isCorrect = true;
        int i = 0;

        while (isCorrect && i < items.length) {
            if (items[i].weight > W) {
                isCorrect = false;
            } else {
                result = result + items[i].cost;
                W = W - items[i].weight;
                i++;
            }
        }

        if (i < items.length && W > 0) {
            result = result + (double) items[i].cost * W / items[i].weight;
        }

        System.out.printf("Удалось собрать рюкзак на сумму %f\n", result);
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        String root=System.getProperty("user.dir")+"/src/";
        File f = new File(root+"by/it/group151002/naftolsky/lesson02/greedyKnapsack.txt");
        double costFinal=new C_GreedyKnapsack().calc(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость %f (время %d)",costFinal,finishTime - startTime);
    }
}