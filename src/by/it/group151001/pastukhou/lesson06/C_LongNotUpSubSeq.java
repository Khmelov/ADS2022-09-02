package by.it.group151001.pastukhou.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Math.max;

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
        int result = 0;
        int[] dp = new int[n];
        int[] pos = new int[n];
        int[] prev = new int[n];
        dp[0] = 2000000001;
        pos[0] = -1;
        for (int i = 1; i < n; ++i) {
            dp[i] = -1;
        }
        for (int i = 0; i < n; ++i) {
            int l = 0, r = n;
            while (r - l > 1) {
                int mid = (l + r) / 2;
                if (dp[mid] >= m[i]) {
                    l = mid;
                } else {
                    r = mid;
                }
            }
            int j = r;
            if (dp[j - 1] >= m[i] && m[i] >= dp[j]) {
                dp[j] = m[i];
                pos[j] = i;
                prev[i] = pos[j - 1];
                result = max(result, j);
            }



        }
        ArrayList<Integer> ans = new ArrayList<Integer>();
        int p = pos[result];
        while (p != -1) {
            ans.add(0, p + 1);
            p = prev[p];
        }
        System.out.println(result);
        for (int i = 0; i < ans.size(); i++) {
            System.out.print(ans.get(i));
            System.out.print(" ");
        }
        return result;
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151001/pastukhou/lesson06/dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int result = instance.getNotUpSeqSize(stream);
    }

}