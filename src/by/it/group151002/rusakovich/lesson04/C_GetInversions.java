package by.it.group151002.rusakovich.lesson04;

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

    static int invCounter = 0;
    void Merge(int[] a, int p, int q, int r){
        int nl = q - p;
        int nr = r - q + 1;
        int[] left = new int[nl];
        int[] right = new int [nr];
        for(int i = 0; i < nl; i++){
            left[i] = a[p + i];
        }
        for(int i = 0; i < nr; i++){
            right[i] = a[q+i];
        }
        int i = 0, j = 0;
        for(int k = p; k <= r; ++k){
            if(i < left.length && j < right.length) {
                if (left[i] <= right[j]) {
                    a[k] = left[i];
                    ++i;
                } else {
                    a[k] = right[j];
                    invCounter += left.length - i;
                    ++j;
                }
            } else {
                if(i < left.length) {
                    a[k] = left[i];
                    ++i;
                }
                if(j < right.length) {
                    a[k] = right[j];
                    ++j;
                }
            }
        }
    }
    void countInversions(int[] a, int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;
            countInversions(a, p, q);
            countInversions(a, q + 1, r);
            Merge(a, p, q + 1, r);
        }
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
        //!!!!!!!!!!!!!!!!!!!!!!!!     тут ваше решение   !!!!!!!!!!!!!!!!!!!!!!!!


        countInversions(a, 0, a.length-1);





        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return invCounter;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/rusakovich/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        //long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        //long finishTime = System.currentTimeMillis();
        System.out.print(result);
    }
}
