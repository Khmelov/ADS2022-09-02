package by.it.group151003.haritonenko.lesson04;

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

    int MergeArrays(int[] a, int l, int m, int r) {
        int result = 0;
        int[] left = new int[m - l + 2];
        for (int i = l; i <= m; i++) {
            left[i - l] = a[i];
        }
        left[m - l + 1] = Integer.MAX_VALUE;

        int[] right = new int[r - m + 1];
        for (int i = m + 1; i <= r; i++) {
            right[i - m - 1] = a[i];
        }
        right[r - m] = Integer.MAX_VALUE;

        int i = 0, j = 0;
        for (int k = l; k <= r; k++) {
            if (left[i] <= right[j]) {
                a[k] = left[i];
                i++;
            }
            else {
                a[k] = right[j];
                j++;
                result += left.length - i - 1;

            }
        }
        return result;
    }

    int MergeSort(int[] a, int l, int r) {
        int result = 0;
        if (l == r) return 0;

        int m = (l + r) / 2;
        result += MergeSort(a, l, m);
        result += MergeSort(a, m+1, r);
        result += MergeArrays(a, l, m, r);
        return result;
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
        int result = 0;
        //!!!!!!!!!!!!!!!!!!!!!!!!     тут ваше решение   !!!!!!!!!!!!!!!!!!!!!!!!

        result = MergeSort(a, 0, a.length - 1);


        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
      //  InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataC.txt");
      InputStream stream = new FileInputStream(root + "by/it/group151003/haritonenko/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        //long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        //long finishTime = System.currentTimeMillis();
        System.out.print(result);
    }
}
