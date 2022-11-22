package by.it.group151001.kononova.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
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

    public static int Merge(int[] a, int[] aCopy, int l, int middle, int r) {
        int k = l, i = l, j = middle + 1;
        int inversions = 0;

        while (i <= middle && j <= r) {
            if (a[i] <= a[j]) {
                aCopy[k] = a[i];
                k++;
                i++;
            }
            else {
                aCopy[k] = a[j];
                k++;
                j++;
                inversions += (middle - i + 1);
            }
        }
        while (i <= middle) {
            aCopy[k] = a[i];
            k++;
            i++;
        }
        for (i = l; i <= r; i++) {
            a[i] = aCopy[i];
        }

        return inversions;
    }


    public static int MergeSort(int[] a, int[] aCopy, int l, int r) {
        if (l >= r) {
            return 0;
        }
        int middle = (l + r)/2;
        int inversions = 0;

        inversions += MergeSort(a, aCopy, l, middle);
        inversions += MergeSort(a, aCopy, middle + 1, r);
        inversions += Merge(a, aCopy, l, middle, r);

        return inversions;
    }

    public int calc(InputStream stream) throws FileNotFoundException {
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

        int[] aCopy = Arrays.copyOf(a, n);
        result = MergeSort(a,aCopy,0,n-1);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151001/kononova/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        //long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        //long finishTime = System.currentTimeMillis();
        System.out.print(result);
    }
}
