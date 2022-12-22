package by.it.group151004.danilov.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Реализуйте сортировку слиянием для одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо отсортировать полученный массив.

Sample Input:
5
2 3 9 2 9
Sample Output:
2 2 3 9 9
*/
public class B_MergeSort {

    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            System.out.println(a[i]);
        }

        // тут ваше решение (реализуйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием

        int BlockSizeIterator;
        int BlockIterator;
        int LeftBlockIterator;
        int RightBlockIterator;
        int MergeIterator;

        int LeftBorder;
        int MidBorder;
        int RightBorder;

        for (BlockSizeIterator = 1; BlockSizeIterator < n; BlockSizeIterator *= 2) {
            for (BlockIterator = 0; BlockIterator < n - BlockSizeIterator; BlockIterator += 2 * BlockSizeIterator) {
                LeftBlockIterator = 0;
                RightBlockIterator = 0;
                LeftBorder = BlockIterator;
                MidBorder = BlockIterator + BlockSizeIterator;
                RightBorder = BlockIterator + 2 * BlockSizeIterator;
                RightBorder = Math.min(RightBorder, n);
                int[] SortedBlock = new int[RightBorder - LeftBorder];

                //Пока в обоих массивах есть элементы выбираем меньший из них и заносим в отсортированный блок
                while (LeftBorder + LeftBlockIterator < MidBorder && MidBorder + RightBlockIterator < RightBorder) {
                    if (a[LeftBorder + LeftBlockIterator] < a[MidBorder + RightBlockIterator]) {
                        SortedBlock[LeftBlockIterator + RightBlockIterator] = a[LeftBorder + LeftBlockIterator];
                        LeftBlockIterator += 1;
                    } else {
                        SortedBlock[LeftBlockIterator + RightBlockIterator] = a[MidBorder + RightBlockIterator];
                        RightBlockIterator += 1;
                    }
                }
                //После этого заносим оставшиеся элементы из левого или правого блока
                while (LeftBorder + LeftBlockIterator < MidBorder) {
                    SortedBlock[LeftBlockIterator + RightBlockIterator] = a[LeftBorder + LeftBlockIterator];
                    LeftBlockIterator += 1;
                }
                while (MidBorder + RightBlockIterator < RightBorder) {
                    SortedBlock[LeftBlockIterator + RightBlockIterator] = a[MidBorder + RightBlockIterator];
                    RightBlockIterator += 1;
                }

                for (MergeIterator = 0; MergeIterator < LeftBlockIterator + RightBlockIterator; MergeIterator++) {
                    a[LeftBorder + MergeIterator] = SortedBlock[MergeIterator];
                }
            }
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result = instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index : result) {
            System.out.print(index + " ");
        }
    }


}
