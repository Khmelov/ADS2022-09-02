package by.it.group151003.onuchina.lesson04;

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
        int[] temp = a;
        result = mergesort(a, temp, 0, n - 1);
        return result;
    }
        //!!!!!!!!!!!!!!!!!!!!!!!!     тут ваше решение   !!!!!!!!!!!!!!!!!!!!!!!!
        public static int merge(int[] arr, int[] artemp, int low, int mid, int high)
        {
            int k = low, i = low, j = mid + 1;
            int inversionCount = 0;

            // пока есть элементы в левом и правом рядах
            while (i <= mid && j <= high)
            {
                if (arr[i] <= arr[j]) {
                    artemp[k++] = arr[i++];
                }
                else {
                    artemp[k++] = arr[j++];
                    inversionCount += (mid - i + 1);
                }
            }

            // копируем оставшиеся элементы
            while (i <= mid) {
                artemp[k++] = arr[i++];
            }

           /* не нужно копировать вторую половину (поскольку остальные элементы
           уже находятся в правильном месте во временном массиве) */

            // копируем обратно в исходный массив, чтобы отразить порядок сортировки
            for (i = low; i <= high; i++) {
                arr[i] = artemp[i];
            }

            return inversionCount;
        }

    // Сортируем массив `arr[low…high]`, используя вспомогательный массив `aux`
    public static int mergesort(int[] arr, int[] artemp, int low, int high)
    {
        // базовый вариант
        if (high <= low) {        // если размер прогона <= 1
            return 0;
        }

        // найти середину
        int mid = (low + ((high - low) >> 1));
        int inversionCount = 0;

        // рекурсивно разделяем прогоны на две половины до тех пор, пока размер прогона не станет <= 1,
        // затем объединяет их и возвращает вверх по цепочке вызовов

        // разделить/объединить левую половину
        inversionCount += mergesort(arr, artemp, low, mid);

        // разделить/объединить правую половину
        inversionCount += mergesort(arr, artemp, mid + 1, high);

        // объединяем две половинки
        inversionCount += merge(arr, artemp, low, mid, high);

        return inversionCount;
    }
//!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!


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
