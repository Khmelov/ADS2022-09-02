package by.it.group151003.denisenko.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    int mergeArrays(int[] baseArray, int [] arr1, int [] arr2)
    {

        int sumLength = arr1.length + arr2.length;
        int ind1 = 0;
        int ind2 = 0;
        int inversionsCount = 0;
        List<Integer> result = new ArrayList<>();

        for(int i = 0; i < sumLength; i++){
            if (ind1 == arr1.length)
            {
                result.add(arr2[ind2]);
                ind2++;
                continue;
            }
            if(ind2 == arr2.length)
            {
                result.add(arr1[ind1]);
                ind1++;
                continue;
            }
            if (arr1[ind1] <= arr2[ind2])
            {
                result.add(arr1[ind1]);
                ind1++;
            }
            else
            {
                result.add(arr2[ind2]);
                inversionsCount += arr1.length-ind1;
                ind2++;
            }
        }

        return inversionsCount;
    }

    int getInversions(int[] arr)
    {
        if(arr.length <= 1){
            return 0;
        }
        int middle = (arr.length + 1)/2;
        return getInversions(Arrays.copyOfRange(arr, 0, middle)) +
                getInversions(Arrays.copyOfRange(arr, middle, arr.length)) +
                mergeArrays(arr, Arrays.copyOfRange(arr, 0, middle), Arrays.copyOfRange(arr, middle, arr.length));
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



        result = getInversions(a);


        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151003/denisenko/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        //long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        //long finishTime = System.currentTimeMillis();
        System.out.print(result);
    }
}