package by.it.group151002.bybikov.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
Первая строка содержит число 1<=n<=10000, вторая - n натуральных чисел, не превышающих 10.
Выведите упорядоченную по неубыванию последовательность этих чисел.

При сортировке реализуйте метод со сложностью O(n)

Пример: https://karussell.wordpress.com/2010/03/01/fast-integer-sorting-algorithm-on/
Вольный перевод: http://programador.ru/sorting-positive-int-linear-time/
*/

public class B_CountSort {

    static final int HIGH_VALUE_BOUND = 10;

    int[] countSortRealization (int[] array) {
        int[] frequencyArray = new int[HIGH_VALUE_BOUND];
        Arrays.fill(frequencyArray, 0);
        for (int i = 0; i < array.length; i++)
            frequencyArray[array[i] - 1]++;
        int j = 0;
        for (int i = 0; i < frequencyArray.length; i++) {
            while (frequencyArray[i] > 0) {
                array[j] = i + 1;
                frequencyArray[i]--;
                j++;
            }
        }
        return array;
    }

    int[] countSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //размер массива
        int n = scanner.nextInt();
        int[] array = new int[n];

        //читаем точки
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
        //тут реализуйте логику задачи с применением сортировки подсчетом
        array = countSortRealization(array);




        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return array;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataB.txt");
        B_CountSort instance = new B_CountSort();
        int[] result=instance.countSort(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
