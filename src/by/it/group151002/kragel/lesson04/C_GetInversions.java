package by.it.group151002.kragel.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.KeyPair;
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
    int[] merge(int[] a1, int[] a2){
        int[] res = new int[a1.length + a2.length - 1];
        res[0] = a1[0] + a2[0];
        int lim = a1.length <= a2.length ? a1.length : a2.length;
        int i = 1, j = 1;
        while(i < lim && j < lim){
            if (a1[i] <= a2[j])
                res[i + j - 1] = a1[i++];
            else {
                res[0] += a1.length - i;
                res[i + j - 1] = a2[j++];
            }
        }
        while(i < a1.length)
            res[i + j - 1] = a1[i++];
        while(j < a2.length)
            res[i + j - 1] = a2[j++];
        return res;
    }
    int[] recursCount(int[] arr, int l, int r){
        if (l < r){
            int m = (l + r) / 2;
            return merge(recursCount(arr, l, m), recursCount(arr, m + 1, r));
        }
        return new int[]{0, arr[l]};
    }
    int countInv(int[] arr){
        return recursCount(arr, 0, arr.length - 1)[0];
    }
    int calc(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        return countInv(a);
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/kragel/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        //long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        //long finishTime = System.currentTimeMillis();
        System.out.println(result);
    }
}
