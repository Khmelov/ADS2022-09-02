package by.it.group151003.matoshko.lesson08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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

    int getMaxWeight(InputStream stream ) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        Scanner scanner = new Scanner(stream);
        int w=scanner.nextInt();
        int n=scanner.nextInt();
        int gold[]=new int[n];
        for (int i = 0; i < n; i++) {
            gold[i]=scanner.nextInt();
        }
        // Создание массива весов рюкзака (от 0 до вместимости рюкзака включительно)
        int weights[]=new int[w+1];
        // Проход по всем ячейкам массива
        for(int i=0;i<w+1;i++)
        {
            // Проход по всем весам слитков
            for(int j=0;j<n;j++)
            {
                // Если вес текущего слитка меньше либо равен вместимости рюкзака с вместимостью i
                if(gold[j]<=i)
                {
                    // То мы либо оставляем текущее значение, если оно уже максимальное
                    // или ячейка=вес слитка + максимальный вес, который можно унести в рюкзаке с вместимостью
                    // равной оставшемуся свободному месту.
                    weights[i]=Math.max(weights[i],weights[i-gold[j]]+gold[j]);
                }
            }
        }

        // В ячейке i=w будет искомое значение
        int result = weights[w];
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151003/matoshko/lesson08/dataA.txt");
        A_Knapsack instance = new A_Knapsack();
        int res=instance.getMaxWeight(stream);
        System.out.println(res);
    }
}
