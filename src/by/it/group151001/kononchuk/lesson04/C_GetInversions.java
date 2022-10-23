package by.it.group151001.kononchuk.lesson04;

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

    int mergeSort(int[] arr, int left, int right) {
        int result = 0;
        if(left < right){
            int middle = (left + right) / 2;
            result += mergeSort(arr, left, middle);
            result += mergeSort(arr, middle + 1, right);

            int[] lArr = new int[middle + 1 - left];
            int[] rArr = new int[right - middle];

            System.arraycopy(arr, left, lArr, 0, lArr.length);
            System.arraycopy(arr, middle + 1, rArr, 0, rArr.length);

            int l = 0, r = 0, temp = 0;

            for(int i = left; i <= right; i++)
            {
                if (l < lArr.length && r < rArr.length){
                    if (lArr[l] <= rArr[r]){
                        temp = lArr[l];
                        l++;
                    }
                    else {
                        temp = rArr[r];
                        r++;
                        result += lArr.length - l;
                    }
                }
                else {
                    if (l >= lArr.length)
                    {
                        temp = rArr[r];
                        r++;
                    }
                    else if (r >= rArr.length){
                        temp = lArr[l];
                        l++;
                    }
                }

                arr[i] = temp;
            }
        }
        return result;
    }

    int calc(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!
        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        int result = mergeSort(arr, 0, n - 1);

        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151001/kononchuk/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        //long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        //long finishTime = System.currentTimeMillis();
        System.out.print(result);
    }
}
