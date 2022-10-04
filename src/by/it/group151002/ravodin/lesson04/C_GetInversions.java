package by.it.group151002.ravodin.lesson04;

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

    public static int NumberOfInvs = 0;

    void MergeSort(int arr[]){
        Divide(arr,0 ,arr.length - 1);
    }
    void Divide(int arr[], int leftIndex, int rightIndex)
    {
        if (leftIndex < rightIndex) {
            int midIndex = leftIndex + (rightIndex - leftIndex) / 2;
            Divide(arr, leftIndex, midIndex);
            Divide(arr, midIndex + 1, rightIndex);
            merge(arr, leftIndex, midIndex, rightIndex);
        }//2 3 9 2 9
    }

    void merge(int arr[], int leftIndex, int midIndex, int rightIndex)
    {
        int leftLength = midIndex - leftIndex + 1;
        int rightLength = rightIndex - midIndex;
        int leftPart[] = new int[leftLength];
        int rightPart[] = new int[rightLength];
        for (int i = 0; i < leftLength; ++i)
            leftPart[i] = arr[leftIndex + i];
        for (int j = 0; j < rightLength; ++j)
            rightPart[j] = arr[midIndex + 1 + j];
        int i = 0, j = 0;
        int k = leftIndex;
        while (i < leftLength && j < rightLength) {
            if (leftPart[i] <= rightPart[j]) {
                arr[k] = leftPart[i];
                i++;
            }
            else {
                NumberOfInvs += leftLength - i;
                arr[k] = rightPart[j];
                j++;
            }
            k++;
        }
        while (i < leftLength) {
            arr[k] = leftPart[i];
            i++;
            k++;
        }
        while (j < rightLength) {
            arr[k] = rightPart[j];
            j++;
            k++;
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

        MergeSort(a);
        int result = NumberOfInvs;





        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        //String root = System.getProperty("user.dir") + "/src/";
        //InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataC.txt");
        String path = "D:\\ADS\\src\\by\\it\\group151002\\ravodin\\lesson04\\dataC.txt";
        InputStream stream = new FileInputStream(path);
        C_GetInversions instance = new C_GetInversions();
        //long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        //long finishTime = System.currentTimeMillis();
        System.out.print(result);
    }
}
