package by.it.group151001.milkovskaya.lesson04;

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
    public static void merge(int[] a, int left,int mid, int right){
        int it1=0,it2=0;
        int[] result = new int[right - left];
        while(left + it1 < mid && mid + it2<right){
            if(a[left + it1] < a[mid + it2]){
               result[it1+it2]=a[left + it1];
               it1++;
            } else {
                result[it1 + it2] = a[mid +it2];
                it2++;
            }
        }
        while(left + it1 < mid){
            result[it1 + it2] = a[left + it1];
            it1++;
        }
        while(mid+it2 < right){
            result[it1 + it2] = a[mid+it2];
            it2++;
        }
        for(int i = 0; i < it1 + it2; i++){
            a[left + i] = result[i];
        }
    }
    public static int Min(int x, int y){
       if(x <= y) return x;
       else return y;
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

        for(int i = 1; i < n; i = 2 * i){
            for(int j = 0; j < n - i; j = j + 2*i){
                merge(a, j,j+i, Min(j + 2 * i, n));
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
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }


}
