package by.it.group151001.shlyk.lesson08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

    int[][] buildMatrix(int[] arrItems, int nWeight){
        int[][] matrix = new int[arrItems.length + 1][nWeight + 1]; //excessive length
        //initialization of matrix with zeroes is redundant -> Java)
        for(int iItem = 1; iItem <= arrItems.length; iItem++){
            for(int iWeight = 1; iWeight <= nWeight; iWeight++){
                matrix[iItem][iWeight] = matrix[iItem - 1][iWeight];
                if(arrItems[iItem - 1] <= iWeight){
                    int nUpdated = matrix[iItem - 1][iWeight - arrItems[iItem - 1] ] + arrItems[iItem - 1];
                    if( nUpdated > matrix[iItem][iWeight])
                        matrix[iItem][iWeight] = nUpdated;
                }
            }
        }
        return matrix;
    }
    int getMaxWeight(String fName) throws IOException {
        Parser parser = new Parser(fName, Parser.ParserType.Bag);
        int[] arrItems = parser.getArray();
        int bagCapacity = parser.getBagCapacity();
        int[][] matrix = buildMatrix(arrItems, bagCapacity);
        return matrix[arrItems.length][bagCapacity];
    }


    public static void main(String[] args) throws IOException {
        String root = System.getProperty("user.dir") + "/src/";
        String sourceName = root + "by/it/group151001/shlyk/lesson08/dataB.txt";
        B_Knapsack instance = new B_Knapsack();
        int res=instance.getMaxWeight(sourceName);
        System.out.println(res);
    }

}
