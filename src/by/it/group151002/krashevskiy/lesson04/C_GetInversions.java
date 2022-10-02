package by.it.group151002.krashevskiy.lesson04;

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

    static int inversions = 0;
    private static int[] mergeSort(int[] arr, int[] buffer, final int left, final int right) {
        if (left == right) {
            buffer[left] = arr[left];
            return buffer;
        }
        int middle = (left + right) / 2;

        int[] leftBuff = mergeSort(arr, buffer, left, middle);
        int[] rightBuff = mergeSort(arr, buffer, middle + 1, right);

        int[] merged;
        if (leftBuff.equals(arr))
            merged = buffer;
        else
            merged = arr;

        int leftCur = left;
        int rightCur = middle + 1;

        for (int i = left; i <= right; i++) {
            if (leftCur <= middle && rightCur <= right) {
                if (leftBuff[leftCur] < rightBuff[rightCur]) {
                    merged[i] = leftBuff[leftCur];
                    leftCur++;
                } else {
                    merged[i] = rightBuff[rightCur];
                    rightCur++;
                    inversions += middle - leftCur;
                }
            } else if (leftCur <= middle) {
                merged[i] = leftBuff[leftCur];
                leftCur++;
            } else {
                merged[i] = rightBuff[rightCur];
                rightCur++;
            }
        }

        return merged;
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
        mergeSort(a, new int[a.length], 0, a.length - 1);

        return inversions;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/krashevskiy/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        //long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        //long finishTime = System.currentTimeMillis();
        System.out.print(result);
    }
}