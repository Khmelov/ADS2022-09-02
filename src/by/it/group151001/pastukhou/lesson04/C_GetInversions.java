package by.it.group151001.pastukhou.lesson04;

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

    int[] mergeSort(int[] a, int n) {
        if (n == 1) return a;
        int mid = n / 2;
        int b[] = new int[mid + 1];
        for (int i = 0; i < mid; i++) {
            b[i] = a[i];
        }
        b[mid] = 0;
        int c[] = new int[n - mid + 1];
        for (int i = mid; i < n; i++) {
            c[i - mid] = a[i];
        }
        c[n - mid] = 0;
        b = mergeSort(b, mid);
        c = mergeSort(c, n - mid);
        int[] res = new int[n + 1];
        res[n] = b[mid] + c[n - mid];
        int i = 0, bi = 0, ci = 0;
        while (bi < mid && ci < n - mid) {
            if (b[bi] <= c[ci]) {
                res[i] = b[bi];
                bi++;
            } else {
                res[i] = c[ci];
                ci++;
                res[n] += mid - bi;
            }
            i++;
        }
        if (bi == mid) {
            while (ci < n - mid) {
                res[i] = c[ci];
                ci++;
                i++;
            }
        } else {
            while (bi < mid) {
                res[i] = b[bi];
                bi++;
                i++;
            }
        }
        return res;
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
        a = mergeSort(a, n);
        return a[n];
        //!!!!!!!!!!!!!!!!!!!!!!!!     тут ваше решение   !!!!!!!!!!!!!!!!!!!!!!!!









        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151001/pastukhou/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        //long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        //long finishTime = System.currentTimeMillis();
        System.out.print(result);
    }
}
