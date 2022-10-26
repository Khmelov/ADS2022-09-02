package by.it.group151002.talalaev.lesson04;

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
        //!!!!!!!!!!!!!!!!!!!!!!!!     тут ваше решение   !!!!!!!!!!!!!!!!!!!!!!!!
        mergeSortCounter(a);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return counter;
    }
    public int counter;

    int[] divArr(int[] arr){
        if (arr.length <= 1) {
            return arr;
        }
        int mid = arr.length / 2;
        int[] left = divArr(Arrays.copyOfRange(arr, 0, mid));
        int[] right = divArr(Arrays.copyOfRange(arr, mid, arr.length));

        return merge(left, right);
    }

    int[] merge(int[] lArr, int[] rArr) {
        int[] res = new int[lArr.length + rArr.length];
        int l = 0;
        int r = 0;
        int i = 0;
        while (i < res.length) {
            if (r >= rArr.length) {
                res[i] = lArr[l];
                l++;
            }else
            if (l >= lArr.length) {
                res[i] = rArr[r];
                counter += lArr.length - l;
                r++;
            }else
            if (lArr[l] <= rArr[r]){
                res[i] = lArr[l];
                l++;
            }else{
                res[i] = rArr[r];
                counter += lArr.length - l;
                r++;
            }
            i++;
        }
        return res;
    }

    int[] mergeSortCounter(int[] arr){
        counter = 0;
        return divArr(arr);
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
