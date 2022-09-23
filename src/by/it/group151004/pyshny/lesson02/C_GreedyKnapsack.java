package by.it.group151004.pyshny.lesson02;
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
import java.util.Arrays;
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

        //тут необходимо реализовать решение задачи
        //итогом является максимально воможная стоимость вещей в рюкзаке
        //вещи можно резать на кусочки (непрерывный рюкзак)
        double result = 0;
        //тут реализуйте алгоритм сбора рюкзака
        //будет особенно хорошо, если с собственной сортировкой
        //кроме того, можете описать свой компаратор в классе Item
        //ваше решение.
        double[][] arr = new double[n][2];
        for(int i=0;i<n;i++){
            arr[i][0]=(double) items[i].cost/items[i].weight;
            arr[i][1]=i+1;
        }
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 1; i < n; i++) {
                if (arr[i][0] > arr[i - 1][0]) {
                    double n1, n2;
                    n1 = arr[i][0];
                    n2 = arr[i][1];
                    arr[i][0] = arr[i - 1][0];
                    arr[i][1] = arr[i - 1][1];
                    arr[i - 1][0] = n1;
                    arr[i - 1][1] = n2;
                    flag=true;
                }
            }
        }
        double curweight=0;
        int i=0;
        while(curweight<60){
            if (curweight+items[(int)arr[i][1]-1].weight<=60){
                result=result+items[(int)arr[i][1]-1].cost;
                curweight=curweight+items[(int)arr[i][1]-1].weight;
                i++;
            }
            else{
                result=result+arr[i][0]*(60-curweight);
                curweight=curweight+60-curweight;
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