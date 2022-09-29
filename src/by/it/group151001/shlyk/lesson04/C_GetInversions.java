package by.it.group151001.shlyk.lesson04;

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

    private static long nInversions = 0;
    private void mergeSort(int[] sortArray, int offset, int arrSize){
        if (arrSize == 1 || arrSize == 0)
            return;
        if (arrSize > 2)
        {
            int rightSize = arrSize / 2;
            int leftSize = arrSize - rightSize;
            mergeSort(sortArray, offset, rightSize);
            mergeSort(sortArray, offset + rightSize, leftSize);
            int iRight = offset, iLeft = offset + rightSize;
            int[] temp = new int[arrSize];
            for(int i = 0; i < arrSize; i++){
                if(iRight < rightSize + offset && iLeft < arrSize + offset){
                    if (sortArray[iRight] <= sortArray[iLeft])
                    {
                        temp[i] = sortArray[iRight];
                        iRight++;
                    } else
                    {
                        nInversions += (rightSize + offset - iRight);
                        temp[i] = sortArray[iLeft];
                        iLeft++;
                    }
                }else {
                    if(iRight == rightSize + offset){
                        nInversions += (rightSize + offset - iRight);
                        temp[i] = sortArray[iLeft];
                        iLeft++;
                    } else {
                        temp[i] = sortArray[iRight];
                        iRight++;
                    }
                }
            }
            int iTemp = 0;
            for(int i = offset; iTemp < temp.length; i++, iTemp++){
                sortArray[i] = temp[iTemp];
            }
            return;
        }
        if (sortArray[0] > sortArray[1]){
            int temp = sortArray[0];
            sortArray[0] = sortArray[1];
            sortArray[1] = temp;
            nInversions++;
        } //otherwise array is sorted
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
        mergeSort(a, 0,n);
        result = (int) nInversions;







        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151001/shlyk/lesson04/Values");
        C_GetInversions instance = new C_GetInversions();
        long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        long finishTime = System.currentTimeMillis();
        System.out.println(result);
        System.out.println(finishTime - startTime);
    }
}
