package by.it.group151003.haritonenko.lesson04;

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

  void MergeArrays(int[] list, int l, int m, int r) {

      int[] left = new int[m - l + 2];
      for (int i = l; i <= m; i++) {
          left[i - l] = list[i];
      }
     left[m - l + 1] = Integer.MAX_VALUE;

      int[] right = new int[r - m + 1];
      for (int i = m + 1; i <= r; i++) {
          right[i - m - 1] = list[i];
      }
     right[r - m] = Integer.MAX_VALUE;

      int i = 0, j = 0;
      for (int k = l; k <= r; k++) {
          if (left[i] <= right[j]) {
              list[k] = left[i];
              i++;
          }
          else {
              list[k] = right[j];
              j++;
          }
      }
  }

    void MergeSort(int[] a, int l, int r) {
        if (l == r) return;
        else {
            int m = (l + r) / 2;
            MergeSort(a, l, m);
            MergeSort(a, m+1, r);
            MergeArrays(a, l, m, r);
        }
    }

    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] a=new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            System.out.println(a[i]);
        }

        // тут ваше решение (реализуйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием

        MergeSort(a, 0, a.length - 1);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}