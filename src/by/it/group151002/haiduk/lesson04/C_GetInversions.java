package by.it.group151002.haiduk.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Рассчитать число инверсий одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо посчитать число пар индексов 1<=i<j<n, для которых A[i]>A[j].

    (Такая пара элементов называется инверсией массива.
    Количество инверсий в массиве является в некотором смысле
    его мерой неупорядоченности: например, в упорядоченном по неубыванию
    массиве инверсий нет вообще, а в массиве, упорядоченном по убыванию,
    инверсию образуют каждые (т.е. любые) два элемента.
    )

Sample Input:
5
2 3 9 2 9
Sample Output:
2

Головоломка (т.е. не обязательно).
Попробуйте обеспечить скорость лучше, чем O(n log n) за счет многопоточности.
Докажите рост производительности замерами времени.
Большой тестовый массив можно прочитать свой или сгенерировать его программно.
*/


public class C_GetInversions {
    public int invertions = 0;
    public void merge(int[] a, int left, int middle, int right) {
        int lLength = middle - left + 1;
        int rLength = right - middle;
        int[] l = new int[lLength];
        int[] r = new int[rLength];
        for (int i = 0; i < lLength; i++)
            l[i] = a[left + i];
        for (int j = 0; j < rLength; j++)
            r[j] = a[middle + 1 + j];
        int i = 0;
        int j = 0;
        int k = left;
        while (i < lLength && j < rLength)
            if (l[i] <= r[j])
                a[k++] = l[i++];
            else {
                a[k++] = r[j++];
                invertions += middle + 1 - left - i;
            }
        while (i < lLength)
            a[k++] = l[i++];
        while (j < rLength)
            a[k++] = r[j++];
    }

    public void mergeSort(int[] a, int left, int right) {
        if (left < right) {
            int m = (left + right) / 2;
            mergeSort(a, left, m);
            mergeSort(a, m + 1, right);
            merge(a, left, m, right);
        }
    }

    int calc(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!
        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        mergeSort(a, 0, n - 1);
        return invertions;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        //long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        //long finishTime = System.currentTimeMillis();
        System.out.print(result);
    }
}
