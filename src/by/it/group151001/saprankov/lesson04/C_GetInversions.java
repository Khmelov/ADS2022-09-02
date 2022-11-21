package by.it.group151001.saprankov.lesson04;

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
    int invertions;
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
        merge_sort(a,0,a.length-1);
        result=invertions;










        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }
    public void merge_sort(int a[],int left,int right){
        if(right-left>0){
            int mid = (left+right)/2;
            merge_sort(a,left,mid);
            merge_sort(a,mid+1,right);
            merge(a,left,mid,right);
        }
    }
    public void merge(int a[],int left,int mid,int right){
        int p1=left;
        int p2=mid+1;
        int p3=0;
        int[] res = new int[right-left+1];
        while(p1<=mid&&p2<=right){
            if(a[p1]<=a[p2]){
                res[p3]=a[p1];
                p1++;
                p3++;
            }
            else{
                res[p3]=a[p2];
                invertions=invertions+(mid-left);
                p2++;
                p3++;

            }
        }
        if(p1<=mid){
            while(p1<=mid){
                res[p3]=a[p1];
                p1++;
                p3++;
            }
        }
        if(p2<=right){
            while(p2<=right){
                res[p3]=a[p2];
                p2++;
                p3++;
            }
        }
        p3=0;
        for(int i=left;i<=right;i++){
            a[i]=res[p3];
            p3++;

        }
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
