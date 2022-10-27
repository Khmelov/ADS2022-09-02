package by.it.group151002.chuklin.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
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
    void sort(int[] array){
        if(array.length > 1){
            int[] left = Arrays.copyOf(array, array.length / 2);
            int[] right = new int[array.length - left.length];
            for(int i = 0; i < right.length; i++){
                right[i] = array[i + array.length / 2];
            }
            sort(left);
            sort(right);
            int k = 0;
            int i = 0;
            int j = 0;
            while(i < left.length && j < right.length){
                if(left[i] < right[j]){
                    array[k++] = left[i++];
                }else{
                    array[k++] = right[j++];
                }
            }
            while(i < left.length){
                array[k++] = left[i++];
            }
            while(j < right.length){
                array[k++] = right[j++];
            }
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
        sort(a);
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
