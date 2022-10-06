package by.it.group151002.strukov.lesson02;
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

    void mergeArray(Item[] array, int p, int q, int r) {
        int first = q - p + 1;
        int second = r - q;
        Item[] left = new Item[first];
        Item[] right = new Item[second];
        for (int i = 0; i < first; i++) {
            left[i] = array[p + i];
        }
        for (int i = 0; i < second; i++) {
            right[i] = array[q + 1 + i];
        }
        int i = 0;
        int j = 0;
        int temp = p;
        while (i < first && j < second) {
            if (left[i].cost/(double)left[i].weight > right[j].cost/(double)right[j].weight)
                array[temp++] = left[i++];
            else
                array[temp++] = right[j++];
        }
        while (i < first)
            array[temp++] = left[i++];
        while (j < second)
            array[temp++] = right[j++];
    }

    void sortArray(Item[] array, int p, int r) {//merge sort - сортировка слияниями
        if (p < r) {
            int middle = (p + r) / 2;
            sortArray(array, p, middle);
            sortArray(array, middle + 1, r);
            mergeArray(array, p, middle, r);
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
        boolean isFilled = false;
        int i = 0;
        //тут реализуйте алгоритм сбора рюкзака
        //будет особенно хорошо, если с собственной сортировкой
        //кроме того, можете описать свой компаратор в классе Item
        //ваше решение.
        //sortArray(items, 0, items.length - 1);
        sortArray(items, 0, items.length - 1);
        while(!isFilled && items.length > 0){
            if(items[i].weight <= W){
                result += items[i].cost;
                W = W - items[i].weight;
            }
            else{
                isFilled = true;
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
        File f=new File(root+"by/it/a_khmelev/lesson02/greedyKnapsack.txt");
        double costFinal=new C_GreedyKnapsack().calc(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость %f (время %d)",costFinal,finishTime - startTime);
    }
}