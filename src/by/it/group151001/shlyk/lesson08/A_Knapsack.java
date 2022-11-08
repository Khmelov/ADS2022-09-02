package by.it.group151001.shlyk.lesson08;

import java.io.*;
import java.util.Arrays;

/*
Задача на программирование: рюкзак с повторами

Первая строка входа содержит целые числа
    1<=W<=100000     вместимость рюкзака
    1<=n<=300        сколько есть вариантов золотых слитков
                     (каждый можно использовать множество раз).
Следующая строка содержит n целых чисел, задающих веса слитков:
  0<=w[1]<=100000 ,..., 0<=w[n]<=100000

Найдите методами динамического программирования
максимальный вес золота, который можно унести в рюкзаке.


Sample Input:
10 3
1 4 8
Sample Output:
10

Sample Input 2:

15 3
2 8 16
Sample Output 2:
14

*/

public class A_Knapsack {
    public void reverse(int[] arrSorted){
        for(int i = 0; i < arrSorted.length / 2; i++){
            int old = arrSorted[i];
            arrSorted[i] = arrSorted[arrSorted.length - 1 - i];
            arrSorted[arrSorted.length - 1 - i] = old;
        }
    }


    int getMaxWeight(String fName) throws IOException {
        Parser parser = new Parser(fName, Parser.ParserType.Bag);
        int[] arrItems = parser.getArray();
        int bagCapacity = parser.getBagCapacity();
        Arrays.sort(arrItems);
        reverse(arrItems);
        int wRest = bagCapacity;
        for(int item : arrItems){
            while(wRest >= item)
                wRest -= item;
        }
        return bagCapacity - wRest;
    }


    public static void main(String[] args) throws IOException {
        String root = System.getProperty("user.dir") + "/src/";
        A_Knapsack instance = new A_Knapsack();
        int res=instance.getMaxWeight(root + "by/it/group151001/shlyk/lesson08/dataA.txt");
        System.out.println(res);
    }
}
