package by.it.group151004.sakovsky.lesson04;

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
    int[] MergeSort(int[] a){
        if(a.length<=1){
            return a;
        }else {
            int mid=a.length>>1;
            int[] leftArr = new int[mid];
            int[] rightArr = new int[a.length - mid];
            int[] result = new int[a.length];
            for (int i = 0; i < mid; i++) {
                leftArr[i] = a[i];
            }
            int j = 0;
            for (int i = mid; i < a.length; i++) {
                rightArr[j] = a[i];
                j++;
            }
            leftArr = MergeSort(leftArr);
            rightArr = MergeSort(rightArr);
            result = merge(leftArr, rightArr);
            return result;
        }
    }
    int[] merge(int[] lArr, int[] rArr){
        int[] result= new int[lArr.length+rArr.length];
        int n=0, m=0, k=0;
        while(n<lArr.length && m<rArr.length){
            if(lArr[n]<=rArr[m]){
                result[k]=lArr[n];
                n++;
            }else{
                result[k]=rArr[m];
                m++;
            }
            k++;
        }
        while(n<lArr.length){
            result[k]=lArr[n];
            k++;
            n++;
        }
        while(m<rArr.length){
            result[k]=rArr[m];
            k++;
            m++;
        }
        return result;
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
        a = MergeSort(a);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151004/sakovsky/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }


}
