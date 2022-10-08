package by.it.group151001.shlyk.lesson05;

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

    //it's supposed to be only specific values -> without unlimited range
    //posible range: 0..10
public static void countSort(int[] sortArray, int minVal, int maxVal){
    int[] valCounter = new int[maxVal - minVal + 1];
    for (int value : sortArray) {
        valCounter[value - minVal]++; //image to effective array
    }
    int iStart = 0;
    for(int i = 0; i < valCounter.length; i++){
        for(int j = 0; j < valCounter[i]; j++){
            sortArray[iStart] = i + minVal;
            iStart++;
        }
    }
}

    int[] countSort(Scanner scanner) {
        int n = scanner.nextInt();
        int[] points=new int[n];

        for (int i = 0; i < n; i++) {
            points[i]=scanner.nextInt();
        }
        countSort(points, 0, 20);

        return points;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataB.txt");
        B_CountSort instance = new B_CountSort();
        int[] result=instance.countSort(new Scanner(stream));
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
