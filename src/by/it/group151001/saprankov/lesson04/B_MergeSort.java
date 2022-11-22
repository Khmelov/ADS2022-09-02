package by.it.group151001.saprankov.lesson04;

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
        int[] a=new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            System.out.println(a[i]);
        }


        // тут ваше решение (реализуйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием
        merge_sort(a,0,a.length-1);








        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }

    public void merge_sort(int a[],int left,int right){
        if(right-left>0) {
            int mid = (right + left) / 2;
            merge_sort(a, left, mid);
            merge_sort(a, mid + 1, right);
            merge(a, left, mid, right);
        }
    }
    public void merge(int a[],int left, int mid,int right){
        //first buffer start pointer
        int p1=left;
        //second buffer start pointer
        int p2=mid+1;
        //result buffer start pointer
        int p3=0;
        //result buffer
        int res[] = new int[(right - left)+1];
        //while one of buffers not empty
        while(p1<=mid&&p2<=right){
            if(a[p1]<a[p2]){
                res[p3]=a[p1];
                p1++;
                p3++;
            }
            else
            {
                res[p3]=a[p2];
                p2++;
                p3++;
            }
        }
        //add remain buffer
        if(p1<=mid){
            while(p1<=mid){
            res[p3]=a[p1];
            p1++;
            p3++;
            }

        }
        if(p2<=right){
            while(p2<=right){
                res[p3]=a[p2];
                p2++;
                p3++;
            }

        }
        //saving result
        p3=0;
        for(int i=left;i<=right;i++){
            a[i]=res[p3];
            p3++;
        }
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
