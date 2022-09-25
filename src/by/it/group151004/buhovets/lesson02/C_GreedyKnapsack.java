package by.it.group151004.buhovets.lesson02;
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
        //o-обьект с к-ым сравнивают
        public int compareTo(Item o) {
            //тут может быть ваш компаратор
            return this.cost/this.weight-o.cost/o.weight;

        }
        /*Comparator возвращает int по следующей схеме:
        отрицательный int (первый объект отрицательный, то есть меньше)
        положительный int (первый объект положительный, хороший, то есть больший)
        ноль = объекты равны*/
    }


    double calc(File source) throws FileNotFoundException {
        Scanner input = new Scanner(source);
        int n = input.nextInt();      //сколько предметов в файле
        int Weight = input.nextInt();      //какой вес у рюкзака
        Item[] items = new Item[n];   //получим список предметов
        for (int i = 0; i < n; i++) { //создавая каждый конструктором
            items[i] = new Item(input.nextInt(), input.nextInt());
        }

        // Arrays.sort(items, Comparator.comparing(options -> options.cost/options.weight));
         Sort(items, 0,items.length-1);
        //Arrays.sort(items);

        //покажем предметы
        for (Item item:items) {
            System.out.println(item);
        }
        System.out.printf("Всего предметов: %d. Рюкзак вмещает %d кг.\n",n,Weight);

        //тут необходимо реализовать решение задачи
        //итогом является максимально воможная стоимость вещей в рюкзаке
        //вещи можно резать на кусочки (непрерывный рюкзак)
        double result = 0;
        int freeWeight = Weight;
        int i= items.length-1;
        //тут реализуйте алгоритм сбора рюкзака
        //будет особенно хорошо, если с собственной сортировкой
        //кроме того, можете описать свой компаратор в классе Item
        //ваше решение.
        while(freeWeight>0 && i>=0){
            if(items[i].weight<=freeWeight){
                result+=items[i].cost;
                freeWeight-=items[i].weight;
            }
            else{
                result += freeWeight*items[i].cost/items[i].weight;
                freeWeight=0;
            }
            i--;
        }

        System.out.printf("Удалось собрать рюкзак на сумму %f\n",result);
        return result;
    }

    public static void swap(Item[] item, int i,int j){
        Item temp= item[i];
        item[i]=item[j];
        item[j]=temp;
    }
    public static int pCount(Item[] items, int start, int end){
        Item p = items[end];
        int pInd = start;

        for(int i=start; i<end; i++){
            if(items[i].cost/items[i].weight - p.cost/p.weight<=0.000001){
                swap(items, i, pInd);
                pInd++;
            }
        }
        swap(items, end, pInd);
        return pInd;
    }
    public static void Sort(Item[] items, int start, int end){
        if(start<end){
            int p = pCount(items, start,end);
            Sort(items, start, p-1);
            Sort(items, p+1, end);
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        String root=System.getProperty("user.dir")+"/src/";
        File f=new File(root+"by/it/group151004/buhovets/lesson02/greedyKnapsack.txt");
        double costFinal=new C_GreedyKnapsack().calc(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость %f (время %d)",costFinal,finishTime - startTime);
    }
}