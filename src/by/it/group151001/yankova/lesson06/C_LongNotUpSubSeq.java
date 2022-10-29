package by.it.group151001.yankova.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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

    int getNotUpSeqSize(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        int[] length = new int[n];
        int parentInd = 0, maxInd = 0;
        List<Integer>[] groupSeq = new List[n];
        for (int i = 0; i < n; i++) {
            groupSeq[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            length[i] = 1;
            for (int j = 0; j < i; j++) {
                if (a[j] >= a[i] && length[j] + 1 > length[i]) {
                    length[i] = length[j] + 1;
                    parentInd = j;
                }
            }
            groupSeq[i].addAll(groupSeq[parentInd]);
            groupSeq[i].add(i+1);
            if(groupSeq[i].size() > groupSeq[maxInd].size())
                maxInd = i;
        }

        for(Integer item: groupSeq[maxInd]){
            System.out.print(item + " ");
        }
        System.out.println();

        return groupSeq[maxInd].size();
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151001/yankova/lesson06/dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int result = instance.getNotUpSeqSize(stream);
        System.out.print(result);
    }

}