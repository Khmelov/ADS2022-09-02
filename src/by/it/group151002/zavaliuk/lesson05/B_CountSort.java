package by.it.group151002.zavaliuk.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Первая строка содержит число 1<=n<=10000, вторая - n натуральных чисел, не превышающих 10.
Выведите упорядоченную по неубыванию последовательность этих чисел.

При сортировке реализуйте метод со сложностью O(n)

Пример: https://karussell.wordpress.com/2010/03/01/fast-integer-sorting-algorithm-on/
Вольный перевод: http://programador.ru/sorting-positive-int-linear-time/
*/

public class B_CountSort {


    int[] countSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //размер массива
        int n = scanner.nextInt();
        int[] arrOfPoints = new int[n];

        //читаем точки
        for (int i = 0; i < n; i++) {
            arrOfPoints[i] = scanner.nextInt();
        }
        //тут реализуйте логику задачи с применением сортировки подсчетом
        int maxAmount;
        int minAmount = maxAmount = arrOfPoints[0];

        for (int i = 0; i < n; i++) {
            if (arrOfPoints[i] < minAmount) minAmount = arrOfPoints[i];
            else if (arrOfPoints[i] > maxAmount) maxAmount = arrOfPoints[i];
        }

        int[] amount = new int[maxAmount - minAmount + 1];
        for (int i = 0; i < n; i++) {
            amount[arrOfPoints[i] - minAmount]++;
        }

        int k = 0;
        for (int i = 0; i < amount.length; i++) {
            for (int j = 0; j < amount[i]; j++) {
                arrOfPoints[k++] = minAmount + i;
            }
        }


        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return arrOfPoints;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataB.txt");
        B_CountSort instance = new B_CountSort();
        int[] result = instance.countSort(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

}
