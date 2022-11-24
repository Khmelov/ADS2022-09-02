package by.it.group151004.haiko.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //общая длина последовательности
        int n = scanner.nextInt();
        int[] m = new int[n];
        //читаем всю последовательность
        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();
        }
        //тут реализуйте логику задачи методами динамического программирования (!!!)

        int[] p = new int[n];
        int[] ind = new int[n];
        for (int i=0; i<n; i++){
            p[i]=1;
            ind[i]=-1;
        }

        for (int i=0; i < n; i++){

            for (int j = i+1; j<n; j++){
                if (m[j]<=m[i]) {
                    p[j] = p[i] + 1;
                    ind[j] = i;
                }
            }
        }
        int result = 1;
        int maxInd=0;
        for (int i=0; i<n; i++){
            if (result<p[i]){   result=p[i];    maxInd = i;}
        }
        System.out.println(result);
        int[] resArr = new int[result];
        do {
            resArr[result-1] = maxInd+1;
            //if (resArr[result-1]== 0)  resArr[result-1] = 1;
            result--;
            maxInd = ind[maxInd];
        } while (ind[maxInd] != -1);
        resArr[result-1] = maxInd+1;

        for (int i = 0 ; i < resArr.length; i++) {
            System.out.print(resArr[i]);System.out.print(' ');
        }
        System.out.println(' ');
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return resArr.length;
    }
    //  2 3 4
    //0 1 2 3
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson06/dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int result = instance.getNotUpSeqSize(stream);
        System.out.print(result);
    }

}