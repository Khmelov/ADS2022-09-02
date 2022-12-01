package by.it.group151001.timoshek.lesson04;

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

   void MergeSort(int[] a,int n)
   {
   if (n<=1) return ;

   int mid=n/2;

   int[] m1=new int[mid],m2=new int[n-mid];

   for(int i=0;i<mid;i++)
       m1[i]=a[i];

   for(int i=mid;i<n;i++)
       m2[i-mid]=a[i];

   MergeSort(m1,mid);
   MergeSort(m2,n-mid);

   int j=0,i=0,k=0;

   while((i<mid)&&(j<n-mid))
       if(m1[i]<m2[j])
            a[k++]=m1[i++];
       else a[k++]=m2[j++];

   while(i<mid)
       a[k++]=m1[i++];

   while(j<n-mid)
       a[k++]=m2[j++];

   return;
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
            System.out.print(a[i]+" ");
        }

        System.out.println();

        // тут ваше решение (реализуйте сортировку слиянием)

        MergeSort(a,n);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151001/timoshek/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }


}
