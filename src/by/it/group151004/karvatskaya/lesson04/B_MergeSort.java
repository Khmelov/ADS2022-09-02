package by.it.group151004.karvatskaya.lesson04;

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


    public static void SliSort(int[] mas) {
        if (mas.length > 1)
        {
            int[] lborder = new int[mas.length / 2];
            System.arraycopy(mas, 0, lborder, 0, lborder.length);
            int[] rborder = new int[mas.length - lborder.length];
            System.arraycopy(mas, mas.length / 2, rborder, 0, rborder.length);
            SliSort(lborder);
            SliSort(rborder);

            int i = 0;
            int j = 0;
            int m = 0;
            while (i < lborder.length && j < rborder.length)
            {
                if (lborder[i] <= rborder[j])
                {
                    mas[m] = lborder[i];
                    m++;
                    i++;
                }
                else
                {
                    mas[m] = rborder[j];
                    m++;
                    j++;
                }
            }
            while (i < lborder.length)
            {
                mas[m] = lborder[i];
                m++;
                i++;
            }
            while (j < rborder.length)
            {
                mas[m] = rborder[j];
                m++;
                j++;
            }
        }
    }
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



        SliSort(a);


        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151004/karvatskaya/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }


}
