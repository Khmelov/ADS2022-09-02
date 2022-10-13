package by.it.group151003.baranouski.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    int[] mergeArrays(int [] arr1, int [] arr2){

        int sumLength = arr1.length + arr2.length;
        int ind1 = 0;
        int ind2 = 0;
        List<Integer> result = new ArrayList<>();

        for(int i = 0; i < sumLength; i++){
            if (ind1 == arr1.length)
            {
                result.add(arr2[ind2]);
                ind2++;
                continue;
            }
            if(ind2 == arr2.length)
            {
                result.add(arr1[ind1]);
                ind1++;
                continue;
            }
            if (arr1[ind1] <= arr2[ind2])
            {
                result.add(arr1[ind1]);
                ind1++;
            }
            else
            {
                result.add(arr2[ind2]);
                ind2++;
            }
        }

        int[] intResultCopy = result.stream().mapToInt(i->i).toArray();
        return intResultCopy;
    }


    int[] mergeSort(int [] arr){
        if(arr.length <= 1) return arr;
        return mergeArrays(mergeSort(Arrays.copyOfRange(arr,0,arr.length/2)),
                mergeSort(Arrays.copyOfRange(arr,arr.length/2,arr.length)));
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
        a=mergeSort(a);





        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151003/baranouski/lesson04/dataB1.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }


}
