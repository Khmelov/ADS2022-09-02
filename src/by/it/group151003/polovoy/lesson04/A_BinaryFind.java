package by.it.group151003.polovoy.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
В первой строке источника данных даны:
        - целое число 1<=n<=100000 (размер массива)
        - сам массив A[1...n] из n различных натуральных чисел,
          не превышающих 10E9, в порядке возрастания,
Во второй строке
        - целое число 1<=k<=10000 (сколько чисел нужно найти)
        - k натуральных чисел b1,...,bk не превышающих 10E9 (сами числа)
Для каждого i от 1 до kk необходимо вывести индекс 1<=j<=n,
для которого A[j]=bi, или -1, если такого j нет.

        Sample Input:
        5 1 5 8 12 13
        5 8 1 23 1 11

        Sample Output:
        3 1 -1 1 -1

(!) Обратите внимание на смещение начала индекса массивов JAVA относительно условий задачи
*/

public class A_BinaryFind {
    int[] findIndex(InputStream stream){

        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!


        int n = scanner.nextInt();
        int[] a=new int[n];
        for (int i = 1; i <= n; i++) {
            a[i-1] = scanner.nextInt();
        }

        int k = scanner.nextInt();
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {

            int left = 0, right = k - 1;
            int value = scanner.nextInt();

            int mid = (left + right) / 2;

            while (true) {

                if (Math.abs(left - right) == 1){

                    if (value == a[left])
                        result[i] = left+1;

                    else if (value == a[right])
                        result[i] = right+1;

                    else
                        result[i] = -1;
                    break;

                }

                if (value < a[mid])
                {
                    right = mid;
                    mid = (left + right) / 2;
                }

                else if (value > a[mid])
                {
                    left = mid;
                    mid = (left + right) / 2;
                }

                else
                {
                    result[i] = mid + 1;
                    break;
                }

            }

        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        System.out.println();
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151003/polovoy/lesson04/dataA.txt");
        A_BinaryFind instance = new A_BinaryFind();
        long startTime = System.currentTimeMillis();
        int[] result=instance.findIndex(stream);
        long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
        System.out.println();
        System.out.println(finishTime - startTime);
    }

}
