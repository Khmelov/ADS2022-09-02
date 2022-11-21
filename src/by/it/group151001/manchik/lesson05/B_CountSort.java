package by.it.group151001.manchik.lesson05;

import java.awt.*;
import java.awt.geom.Point2D;
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

    void sort(int A[], int min, int max, int ALength){
        int countL = max-min+1;
        int count[] = new int[countL];
        int newindex = 0;
        for (int i = 0; i< ALength; i++){
            count[A[i] - min]++;
        }
        for (int i = 0; i<countL; i++){
            for (int j = 0; j< count[i]; j++){
                A[newindex] = i + min;
                newindex++;
            }
        }
    }

    int[] countSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //размер массива
        int n = scanner.nextInt();
        int[] points=new int[n];

        //читаем точки
        for (int i = 0; i < n; i++) {
            points[i]=scanner.nextInt();
        }
        //тут реализуйте логику задачи с применением сортировки подсчетом
        sort(points, 0, 10, n);




        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return points;
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
