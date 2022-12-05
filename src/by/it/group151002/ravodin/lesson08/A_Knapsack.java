package by.it.group151002.ravodin.lesson08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

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

    static int unboundedKnapsack(int W, int wt[], int val[],
                                 int idx, int dp[][])
    {
        if (idx == 0) {
            return (W / wt[0]) * val[0];
        }
        if (dp[idx][W] != -1)
            return dp[idx][W];
        int notTake = 0 + unboundedKnapsack(W, wt, val, idx - 1, dp);
        int take = Integer.MIN_VALUE;
        if (wt[idx] <= W) {
            take = val[idx] + unboundedKnapsack(W - wt[idx], wt, val, idx, dp);
        }
        return dp[idx][W] = Math.max(take, notTake);
    }

    int getMaxWeight(InputStream stream ) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        Scanner scanner = new Scanner(stream);
        int w=scanner.nextInt();
        int n=scanner.nextInt();
        int gold[]=new int[n];
        for (int i = 0; i < n; i++) {
            gold[i]=scanner.nextInt();
        }

        int[][] weights = new int[n][w+1];
        for (int[] row : weights)
            Arrays.fill(row, -1);
        int result = unboundedKnapsack(w, gold, gold, n - 1, weights);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson08/dataA.txt");
        A_Knapsack instance = new A_Knapsack();
        int res=instance.getMaxWeight(stream);
        System.out.println(res);
    }
}
