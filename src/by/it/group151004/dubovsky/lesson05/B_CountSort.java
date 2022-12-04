package by.it.group151004.dubovsky.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
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
        int[] points=new int[n];
        int maxVal = 0;
        //читаем точки
        for (int i = 0; i < n; i++) {
            points[i]=scanner.nextInt();
            if (points[i] > maxVal) maxVal = points[i];
        }
        //тут реализуйте логику задачи с применением сортировки подсчетом

        ArrayList<Integer> tmp = new ArrayList<>(maxVal + 1);

        int result[] = new int[maxVal + 1];
        for (int i = 0; i < points.length; i++) {
            result[points[i]]++;
        }

        for (int i = 0; i < result.length; i++) {
            if (result[i] == 0) continue;
            else if (result[i] == 1) {
                tmp.add(i);
            } else {
                for (int j = 0; j < result[i]; j++) {
                    tmp.add(i);
                }
            }
        }



        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return points = tmp.stream().filter(i -> i != 0).mapToInt(i -> i).toArray();
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
