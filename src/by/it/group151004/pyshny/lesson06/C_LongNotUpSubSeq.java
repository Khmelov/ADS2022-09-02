package by.it.group151004.pyshny.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
Задача на программирование: наибольшая невозростающая подпоследовательность

Дано:
    целое число 1<=n<=1E5 ( ОБРАТИТЕ ВНИМАНИЕ НА РАЗМЕРНОСТЬ! )
    массив A[1…n] натуральных чисел, не превосходящих 2E9.

Необходимо:
    Выведите максимальное 1<=k<=n, для которого гарантированно найдётся
    подпоследовательность индексов i[1]<i[2]<…<i[k] <= длины k,
    для которой каждый элемент A[i[k]] не больше любого предыдущего
    т.е. для всех 1<=j<k, A[i[j]]>=A[i[j+1]].

    В первой строке выведите её длину k,
    во второй - её индексы i[1]<i[2]<…<i[k]
    соблюдая A[i[1]]>=A[i[2]]>= ... >=A[i[n]].

    (индекс начинается с 1)

Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ

    Sample Input:
    5
    5 3 4 4 2

    Sample Output:
    4
    1 3 4 5
*/


public class C_LongNotUpSubSeq {
    int getmax(ArrayList<Integer> a,int[] arr){
        int min=-1;
        int[] tmparr = new int[a.size()];
        int tmp=0,res=0;
        for (int i=0;i<a.size();i++){
            tmp=a.get(i);
            if(arr[tmp]>min){
                min=arr[tmp];
                res=tmp;
            }
        }
        return res;
    }

    int getNotUpSeqSize(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //общая длина последовательности
        int n = scanner.nextInt();
        int[] m = new int[n],l = new int[n],ind = new int[n];
        //читаем всю последовательность
        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();
            l[i]=1;
            ind[i]=0;
        }

        //тут реализуйте логику задачи методами динамического программирования (!!!)
        int result = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (m[i] <= m[j]) {
                    l[i] = l[j] + 1;
                }
            }
        }
        int max=0;
        for (int i=0;i<n;i++){
            if(max<l[i]) max=l[i];
        }
        result=max;
        ArrayList<Integer> arr = new ArrayList<Integer>();
        int j=0;
        while(max>0){
            for(int i=0;i<n;i++){
               if(l[i]==max) arr.add(i);
            }
            ind[j]=getmax(arr,m);
            j++;
            max--;
            arr.clear();
        }
        for (int i=result-1;i>=0;i--){
            System.out.print(ind[i]+1);
            System.out.print(" ");
        }
        System.out.println();
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson06/dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int result = instance.getNotUpSeqSize(stream);
        System.out.print(result);
    }

}