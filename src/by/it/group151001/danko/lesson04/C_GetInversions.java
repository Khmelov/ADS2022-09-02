package by.it.group151001.danko.lesson04;

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

    public static int result = 0;

    void mergeSort(int[] arr) {
        if(arr.length == 1) return;
        int n = arr.length;
        int mid = n/2;

        int[] left = new int[mid];
        int[] right = new int[n-mid];
        for(int i = 0; i < mid; i++)
            left[i] = arr[i];
        for(int i = mid; i < n; i++)
            right[i-mid] = arr[i];

        mergeSort(left);
        mergeSort(right);
        merge(arr, left, right);
    }

    void merge(int[] arr, int[] left, int[] right)
    {
        int leftID = left.length;
        int rightID = right.length;
        int i = 0, j = 0, index = 0;

        while (i < leftID && j < rightID)
        {
            if(left[i] <= right[j])
            {
                arr[index]=left[i];
                index++;
                i++;
            } else
            {
                arr[index] = right[j];
                index++;
                j++;
                result = result + leftID - i;

            }
        }

        for(int ll = i; ll < leftID; ll++)
        {
            arr[index++] = left[ll];
        }
        for(int rr=j; rr < rightID; rr++)
        {
            arr[index++] = right[rr];
        }

    }

    int calc(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!
        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!     тут ваше решение   !!!!!!!!!!!!!!!!!!!!!!!!
        mergeSort(a);
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
