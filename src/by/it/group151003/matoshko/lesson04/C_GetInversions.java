package by.it.group151003.matoshko.lesson04;

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
    private static void mergeSort(int[] arr)
    {
        int n=arr.length;
        if(n==1) return;

        int mid=n/2;
        int[] l=new int[mid];
        int[] r=new int[n-mid];

        for(int i=0;i<mid;i++)
            l[i]=arr[i];
        for(int i=mid;i<n;i++)
            r[i-mid]=arr[i];

        mergeSort(l);
        mergeSort(r);
        merge(arr,l,r);

    }


    private static void merge(int[] arr,int[] l, int[] r)
    {
        int left = l.length;
        int right = r.length;
        int i=0;
        int j=0;
        int index=0;


        while (i<left && j<right)
        {
            if(l[i]<=r[j])
            {
                arr[index]=l[i];
                index++;
                i++;
            } else
            {
                arr[index]=r[j];
                index++;
                j++;
                    result=result+left-i;

            }
        }

        for(int ll=i;ll<left;ll++)
        {
            arr[index++]=l[ll];
        }
        for(int rr=j;rr<right;rr++)
        {
            arr[index++]=r[rr];
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
        mergeSort(a);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151003/matoshko/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        //long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        //long finishTime = System.currentTimeMillis();
        System.out.print(result);
    }
}
