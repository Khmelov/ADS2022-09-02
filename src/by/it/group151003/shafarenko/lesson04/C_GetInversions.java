package by.it.group151003.shafarenko.lesson04;

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

    int calc(int[] a) throws FileNotFoundException {
        //подготовка к чтению данных
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!
        int result;
        //!!!!!!!!!!!!!!!!!!!!!!!!     тут ваше решение   !!!!!!!!!!!!!!!!!!!!!!!!

        int[] aCopy = new int[a.length];
        int[] buffer = new int[a.length];
        System.arraycopy(a, 0, aCopy, 0, a.length);
        result = MergeSort(aCopy, buffer, 0, a.length - 1);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    private int MergeSort(int[] arr, int[] buffer, int left, int right) {
        int result = 0;
        if (left == right)
            return 0;

        int middle = (left + right) / 2;
        result += MergeSort(arr, buffer, left, middle);
        result += MergeSort(arr, buffer, middle + 1, right);
        result += Merge(arr, buffer, left, right);
        return result;
    }

    private int Merge (int[] arr, int[] buffer, int left, int right) {
        int result = 0;
        int middle = (left + right) / 2;
        int i = left, j = middle + 1, k = left;
        while (i <= middle && j <= right) {
            if (arr[i] <= arr[j]) {
                buffer[k++] = arr[i++];
            } else {
                buffer[k++] = arr[j++];
                result += middle + 1 - i;
            }
        }
        while (i <= middle) {
            buffer[k++] = arr[i++];
        }
        while (j <= right) {
            buffer[k++] = arr[j++];
        }
        for (i = left; i <= right; i++) {
            arr[i] = buffer[i];
        }
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/";
        InputStream stream = new FileInputStream(root + "by/it/group151003/shafarenko/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        Scanner scanner = new Scanner(stream);
        //long startTime = System.currentTimeMillis();
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        int result = instance.calc(arr);
        //long finishTime = System.currentTimeMillis();
        System.out.print(result);
    }
}
