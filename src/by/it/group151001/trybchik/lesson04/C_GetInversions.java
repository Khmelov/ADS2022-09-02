package by.it.group151001.trybchik.lesson04;

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


    private int[] mergesort(int[] mas,int n,int count) {
        if (n==1) return mas;
        int middle = n / 2;
        int b[] = new int[middle+1];
        int c[] = new int[n-middle+1];
        for (int  i = 0;i<middle;i++)
        {
            b[i] = mas[i];
        }
        for (int i = middle;i<n;i++)
        {
            c[i-middle] = mas[i];
        }
        b = mergesort(b,middle,count);
        c = mergesort(c,n-middle,count);
        int result[] =  new int[n+1];
        result[n] = count;
        int bi = 0,ci = 0,i = 0;
        while(bi<middle && ci<(n-middle))
        {
            if(b[bi]<=c[ci])
            {
                result[i] = b[bi];
                bi++;
            }
            else
            {
                count = count+middle-bi;
                result[n] = result[n]+middle-bi;
                result[i] = c[ci];
                ci++;
            }
            i++;
        }
        if(ci == (n-middle))
        {
            while(bi<middle)
            {
                result[i] = b[bi];
                i++;
                bi++;
            }
        }
        else
        {
            while(ci<n-middle)
            {
                result[i] = c[ci];
                i++;
                ci++;
            }
        }
        result[n] = result[n]+c[ci];
        result[n] = result[n]+b[bi];
        return result;
    }
    int calc(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!
        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] a = new int[n+1];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        int result = 0;
        //!!!!!!!!!!!!!!!!!!!!!!!!     тут ваше решение   !!!!!!!!!!!!!!!!!!!!!!!!
        a = mergesort(a,n,0);
        result = a[n];
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
