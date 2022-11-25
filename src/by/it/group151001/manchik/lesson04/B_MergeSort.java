package by.it.group151001.manchik.lesson04;

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

    void Merge(int A[], int l, int r) {
        int target[] = new int[r-l+1];
        int border = (l+r)/2;
        int pL = l, pR = border+1, i = 0;
        while ((pL <= border) && (pR<= r)){
            if (A[pL] <= A[pR]) {
                target[i] = A[pL];
                pL++;
            } else {
                target[i] = A[pR];
                pR++;
            }
            i++;
        }
        for (int p = pL; p <= border; p++) {
            target[i] = A[p];i++;
        }
        for (int p = pR; p <= r; p++) {
            target[i] = A[p];i++;
        }
        int k = 0;
        for (i = l; i<=r; i++){
            A[i] = target[k];
            k++;
        }
    }
    void MergeSort(int A[], int l, int r){
        if (l<r) {
            int m = (l+r)/2;
            MergeSort(A, l ,m);
            MergeSort(A,m+1, r);
            Merge(A, l ,r);
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
        System.out.printf("Array: ");
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            System.out.printf("%d ",a[i]);
        }
        System.out.printf("\nSorted array: ");
        // тут ваше решение (реализуйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием
        MergeSort(a, 0, n-1);





        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151001/manchik/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }


}
