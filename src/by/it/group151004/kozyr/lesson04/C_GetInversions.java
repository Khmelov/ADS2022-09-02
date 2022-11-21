package by.it.group151004.kozyr.lesson04;

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
    int merge(int[] arr, int r, int m, int l){
        int n1 = m - r + 1;
        int n2 = l - m;

        int sArr1[] = new int[n1];
        int sArr2[] = new int[n2];

        for (int i = 0; i < n1; i++)
            sArr1[i] = arr[r + i];
        for (int j = 0; j < n2; j++)
            sArr2[j] = arr[m + 1 + j];

        int i, j, k;
        i = 0;
        j = 0;
        k = r;

        int invCount = 0;

        while (i < n1 && j < n2){
            if (sArr1[i] <= sArr2[j]) {
                arr[k] = sArr1[i];
                i++;
            } else {
                arr[k] = sArr2[j];
                j++;
                invCount += (m + 1) - (r + i);
            }
            k++;
        }

        while (i < n1){
            arr[k] = sArr1[i];
            i++;
            k++;
        }

        while (j < n2){
            arr[k] = sArr2[j];
            j++;
            k++;
        }

        return invCount;
    }
    int mergeSort(int[] arr, int left, int right){
        int count = 0;
        if (left < right){
            int mid = (left + right)/2;

            count += mergeSort(arr, left, mid);
            count += mergeSort(arr, mid + 1, right);

            count += merge(arr, left, mid, right);
        }
        return count;
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


        result = mergeSort(a, 0, a.length - 1);






        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
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
