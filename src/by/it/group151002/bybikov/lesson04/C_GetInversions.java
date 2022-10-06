package by.it.group151002.bybikov.lesson04;

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
Необходимо посчитать число пар индексов 1 <= i < j < n, для которых A[i]>A[j].

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

    private int inversionsCount;

    int[] mergeTwoArrays (int[] firstArray, int[] secondArray) {
        int[] resultArray = new int[firstArray.length + secondArray.length];
        int i = 0, j = 0;
        while (i < firstArray.length && j < secondArray.length) {
            if(firstArray[i] <= secondArray[j]) {
                resultArray[i + j] = firstArray[i];
                i++;
            }
            else {
                resultArray[i + j] = secondArray[j];
                this.inversionsCount = this.inversionsCount + firstArray.length - i;
                j++;
            }
        }
        for ( ; i < firstArray.length; i++)
            resultArray[i + j] = firstArray[i];
        for ( ; j < secondArray.length; j++)
            resultArray[i + j] = secondArray[j];
        return resultArray;
    }

    int[] mergeSort (int[] array) {
        if(array == null || array.length < 2)
            return array;
        int beginIndex = 0, endIndex = array.length;
        int centerIndex = (beginIndex + endIndex) / 2;
        int[] firstPartArray = mergeSort(Arrays.copyOfRange(array, beginIndex, centerIndex));
        int[] secondPartArray = mergeSort(Arrays.copyOfRange(array, centerIndex, endIndex));
        int[] resultArray = mergeTwoArrays(firstPartArray, secondPartArray);
        return resultArray;
    }

    int calc(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!
        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] inputArray = new int[n];
        for (int i = 0; i < n; i++) {
            inputArray[i] = scanner.nextInt();
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!     тут ваше решение   !!!!!!!!!!!!!!!!!!!!!!!!
        this.inversionsCount = 0;
        inputArray = mergeSort(inputArray);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return this.inversionsCount;
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
