package by.it.group151003.matoshko.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
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
        int n = scanner.nextInt(), maxNum = 10;
        int[] points=new int[n];

        //читаем точки
        for (int i = 0; i < n; i++) {
            points[i]=scanner.nextInt();
        }

        int[] sortedArr = new int[n];
        int[] frequency = new int[maxNum];

        // Каждой возможной точке из диапазона ставится элемент массива, в котором хранится частота повторения элемента
        for(int i=0;i<maxNum;i++){
            frequency[i]=0;
        }

        // Считаем частоту: если элемент равен 3, то элемент с таким индексом в массиве частот увеличивается на 1
        for(int i=0;i<n;i++){
            frequency[points[i] - 1]++;
        }

        // Последовательно выводим элемент * его количество
        int count = 0;
        for (int i = 0; i < frequency.length; i++) {
            for (int j = 0; j < frequency[i]; j++) {
                sortedArr[count++] = i + 1;
            }
        }


      /*  int[] sortedArr = new int[n];
        //тут реализуйте логику задачи с применением сортировки подсчетом
        Map<Integer,Integer> arr= new HashMap<>();
        for(int i=0;i<n;i++)
        {
            if(arr.containsKey(points[i]))
                arr.put(points[i],arr.get(points[i])+1);
            else
                arr.put(points[i],1);
        }
        for(int i:arr.keySet())
        {
           sortedArr
        }
*/



        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return sortedArr;
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
