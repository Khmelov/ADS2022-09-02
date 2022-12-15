package by.it.group151004.buhovets.lesson08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
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
        int cell[][]=new int[n][w+1];
        int gold[]=new int[n];
        for (int i = 0; i < n; i++) {
            gold[i]=scanner.nextInt();
        }
        Arrays.sort(gold);

        for(int i=0; i<n;i++){
            cell[i][0]=0;
        }
        for(int j=1; j<w; j++){
            cell[0][j]=gold[0];
        }

        for(int i=1; i<n;i++){
            for(int j=1; j<=w;j++){
                //                 j: 0 1 2 3 4 5 6 7 8 9
                //        equal to w: 1 2 3 4 5 6 7 8 9 10
                if(gold[i]<=j)
                    cell[i][j]=Math.max(cell[i-1][j], gold[i]+cell[i-1][j-gold[i]]);
                else
                    cell[i][j]=cell[i-1][j];
            }
        }


        System.out.println("SIZE: 0 1 2 3 4 5 6 7 8 9 10");
        PrintTable(cell, n, w);



        int result = cell[n-1][w-1];
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    void PrintTable(int[][] cell, int n, int m){
        for(int i=0; i<n;i++){
            System.out.print("      ");
            for(int j=0; j<=m;j++){
                System.out.print(cell[i][j]+" ");
            }
            System.out.println();
        }

    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151004/buhovets/lesson08/dataB.txt");
        B_Knapsack instance = new B_Knapsack();
        int res=instance.getMaxWeight(stream);
        System.out.println(res);
    }

}
