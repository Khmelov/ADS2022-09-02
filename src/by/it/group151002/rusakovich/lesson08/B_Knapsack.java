package by.it.group151002.rusakovich.lesson08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: рюкзак без повторов

Первая строка входа содержит целые числа
    1<=W<=100000     вместимость рюкзака
    1<=n<=300        число золотых слитков
                    (каждый можно использовать только один раз).
Следующая строка содержит n целых чисел, задающих веса каждого из слитков:
  0<=w[1]<=100000 ,..., 0<=w[n]<=100000

Найдите методами динамического программирования
максимальный вес золота, который можно унести в рюкзаке.

Sample Input:
10 3
1 4 8
Sample Output:
9

*/

public class B_Knapsack {

    int getMaxWeight(InputStream stream ) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        Scanner scanner = new Scanner(stream);
        int w=scanner.nextInt();
        int n=scanner.nextInt();
        int gold[]=new int[n];
        for (int i = 0; i < n; i++) {
            gold[i]=scanner.nextInt();
        }

        int[][] weight_table = new int[n + 1][w + 1];
        for(int i = 0; i <= n; ++i)
            weight_table[i][0] = 0;
        for(int i = 0; i<= w; ++i)
            weight_table[0][i] = 0;


        for(int i = 1; i <= n; ++i){
            for(int j = 1; j <= w; ++j){
                if(gold[i-1] > j)
                    weight_table[i][j] = weight_table[i-1][j];
                else {
                    weight_table[i][j] = Math.max(weight_table[i-1][j], gold[i-1] + weight_table[i-1][j - gold[i-1]]);
                }
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return weight_table[n][w];
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson08/dataB.txt");
        B_Knapsack instance = new B_Knapsack();
        long startTime = System.currentTimeMillis();
        int res=instance.getMaxWeight(stream);
        long finishTime = System.currentTimeMillis();
        System.out.println("Knapsack weight: " + res);
        System.out.println("Time: " + (finishTime - startTime) + "ms");
    }

}
