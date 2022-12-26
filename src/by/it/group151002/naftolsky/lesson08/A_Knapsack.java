package by.it.group151002.naftolsky.lesson08;

import by.it.group151002.naftolsky.lesson05.A_QSort;

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

    int partition(int[] arr, int lowIndex, int highIndex) {
        int pivotValue = arr[lowIndex + highIndex / 2];

        int i = lowIndex - 1;
        int j = highIndex + 1;
        while (true)
        {
            do {
                i++;
            } while (arr[i] < pivotValue);

            do {
                j--;
            } while (arr[j] > pivotValue);

            if (i >= j) {
                return j;
            }

            int tempNumber = arr[i];
            arr[i] = arr[j];
            arr[j] = tempNumber;
        }
    }

    void quickSort(int[] arr, int  lowIndex, int highIndex) {
        if (lowIndex >= highIndex) {
            return;
        }
        int pivotIndex = partition(arr, lowIndex, highIndex);
        quickSort(arr, lowIndex, pivotIndex);
        quickSort(arr,pivotIndex + 1, highIndex);
    }

    int getMaxWeight(InputStream stream ) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        Scanner scanner = new Scanner(stream);
        int w = scanner.nextInt();
        int n = scanner.nextInt();
        int gold[] = new int[n];
        for (int i = 0; i < n; i++) {
            gold[i] = scanner.nextInt();
        }

        quickSort(gold, 0, n - 1);
        int result = 0;

        int i = n - 1;
        while (result < w && i >= 0) {
            while (result + gold[i] <= w) {
                result = result + gold[i];
            }

            i--;
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/naftolsky/lesson08/dataA.txt");
        A_Knapsack instance = new A_Knapsack();
        int res=instance.getMaxWeight(stream);
        System.out.println(res);
    }
}
