package by.it.group151002.redkovskiy.lesson04;

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
    int inverses = 0;
    private void mergingProcessWithInversesCalculation(int[] arr, int[] leftArr, int[] rightArray, int left, int right) {
        int pos = 0, i = 0, j = 0;
        while (pos < left && i < right) {
            if (leftArr[pos] <= rightArray[i])
                arr[j++] = leftArr[pos++];
            else {
                arr[j++] = rightArray[i++];
                inverses += left - pos; //увеличиваем счётчик на каждом этапе
            }
        }
        while (pos < left)
            arr[j++] = leftArr[pos++];
        while (i < right)
            arr[j++] = rightArray[i++];
    }

    private void mergeSortWithInversesCalculation(int[] arr, int n) {
        if (n == 1)
            return;
        int middleIndex = n / 2;
        int[] leftArr = new int[middleIndex];
        int[] rightArr = new int[n - middleIndex];

        //left array is from 0 to middleIndex - 1
        System.arraycopy(arr, 0, leftArr, 0, middleIndex);

        //right array is from middleIndex to n - 1
        System.arraycopy(arr, middleIndex, rightArr, 0, n - middleIndex);

        mergeSortWithInversesCalculation(leftArr, middleIndex);
        mergeSortWithInversesCalculation(rightArr, n - middleIndex);

        mergingProcessWithInversesCalculation(arr, leftArr, rightArr, middleIndex, n - middleIndex);
    }

    int calc(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        int size = scanner.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }

        mergeSortWithInversesCalculation(arr, size);

        return inverses;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/redkovskiy/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        int result = instance.calc(stream);
        System.out.print(result);
    }
}