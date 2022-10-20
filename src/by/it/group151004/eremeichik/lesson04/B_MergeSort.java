package by.it.group151004.eremeichik.lesson04;

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
        }
        sort(a,0,a.length-1);
        // тут ваше решение (реализуйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием






        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }

    private void sort(int[] arr, int left, int right){
        if(right>left){
            int middle = left + (right-left)/2;
            sort(arr,middle+1, right);
            sort(arr, left, middle);
            merge(arr,left,middle,right);
        }
    }

    private void merge(int[] arr, int left, int middle,int right){
        int[] leftSubarray = Arrays.copyOfRange(arr,left,middle+1);
        int[] rightSubarray = Arrays.copyOfRange(arr,middle+1,right+1);
        int leftIndex = 0;
        int rightIndex = 0;
        int initialArrIndex = left;
        while(leftIndex<leftSubarray.length && rightIndex<rightSubarray.length){
            if(leftSubarray[leftIndex] <= rightSubarray[rightIndex]){
                arr[initialArrIndex] = leftSubarray[leftIndex];
                leftIndex++;
            } else{
                arr[initialArrIndex] = rightSubarray[rightIndex];
                rightIndex++;
            }
            initialArrIndex++;
        }
        while(leftIndex<leftSubarray.length){
            arr[initialArrIndex] = leftSubarray[leftIndex];
            initialArrIndex++;
            leftIndex++;
        }
        while(rightIndex<rightSubarray.length){
            arr[initialArrIndex] = rightSubarray[rightIndex];
            initialArrIndex++;
            rightIndex++;
        }
    }

    private void swap(int[] arr, int firstIndex, int secondIndex){
        int temp = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = temp;
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
