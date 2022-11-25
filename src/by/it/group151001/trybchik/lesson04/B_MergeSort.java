package by.it.group151001.trybchik.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Реализуйте сортировку слиянием для одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо отсортировать полученный массив.

Sample Input:
5
2 3 9 2 9
Sample Output:
2 2 3 9 9
*/
public class B_MergeSort {

    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] a=new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            System.out.println(a[i]);
        }

        // тут ваше решение (реализуйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием
        a = mergesort(a,a.length);




        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }
    private int[] mergesort(int[] mas,int n) {
        if (n==1) return mas;
        int middle = n / 2;
        int b[] = new int[middle];
        int c[] = new int[n-middle];
        for (int  i = 0;i<middle;i++)
        {
            b[i] = mas[i];
        }
        for (int i = middle;i<n;i++)
        {
            c[i-middle] = mas[i];
        }
        b = mergesort(b,middle);
        c = mergesort(c,n-middle);
        int result[] =  new int[n];
        int bi = 0,ci = 0,i = 0;
        while(bi<middle && ci<(n-middle))
        {
            if(b[bi]<=c[ci])
            {
                result[i] = b[bi];
                bi++;
            }
            else
            {
                result[i] = c[ci];
                ci++;
            }
            i++;
        }
        if(ci == (n-middle))
        {
            while(bi<middle)
            {
                result[i] = b[bi];
                i++;
                bi++;
            }
        }
        else
        {
            while(ci<n-middle)
            {
                result[i] = c[ci];
                i++;
                ci++;
            }
        }
        return result;
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }


}
