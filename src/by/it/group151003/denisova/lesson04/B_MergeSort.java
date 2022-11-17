package by.it.group151003.denisova.lesson04;

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
        MergeSort(a);
        // тут ваше решение (реализуйте сортировку слиянием)
        return a;
    }
    //сортировка
    private static void MergeSort(int[] Arr){
        int k=Arr.length;
        if(k==1) return;

        int Middle=k/2;
        int[] left=new int[Middle];
        int[] right=new int[k-Middle];

        for(int i=0;i<Middle;i++)
            left[i]=Arr[i];

        for(int i=Middle;i<k;i++)
            right[i-Middle]=Arr[i];
        MergeSort(left);
        MergeSort(right);
        Merge(Arr,left,right);
    }

    //слияние 2 подмассивов
    public static void Merge(int[] Arr,int[] l, int[] r){
      int left=l.length;
      int right= r.length;
      int i=0;
      int j=0;
      int ind=0;

      while(i<left && j<right){
          if(l[i]<r[j]){
              Arr[ind]=l[i];
              ind++;
              i++;
          }
          else{
              Arr[ind]=r[j];
              ind++;
              j++;

          }
      }
      for(int lel=i; lel<left; lel++){
          Arr[ind++]=l[lel];
      }
      for(int rer=j;rer<right;rer++){
          Arr[ind++]=r[rer];
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
