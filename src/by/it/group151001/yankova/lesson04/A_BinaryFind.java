package by.it.group151001.yankova.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
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
    int[] findIndex(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);

        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 1; i <= n; i++) {
            a[i-1] = scanner.nextInt();
        }
        Arrays.sort(a);

        int k = scanner.nextInt();
        int[] result =  new int[k];
        for (int i = 0; i < k; i++) {
            int value = scanner.nextInt();
            result[i] = binary_serach(a, 0, n-1, value);
        }
        return result;
    }

    private int binary_serach(int[] a, int l, int r, int key){
        while (l <= r){
            int mid = (l + r)/2;
            if(key == a[mid]) return mid + 1;
            else if(key > a[mid]) l = mid + 1;
            else if(key < a[mid]) r = mid - 1;
        }
        return -1;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151001/yankova/lesson04/dataA.txt");
        A_BinaryFind instance = new A_BinaryFind();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.findIndex(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
