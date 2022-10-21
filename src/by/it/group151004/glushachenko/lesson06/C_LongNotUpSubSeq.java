package by.it.group151004.glushachenko.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
Задача на программирование: наибольшая невозрастающая подпоследовательность

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


    int find(int[] arr, int[] subsequence) {

        if (arr.length <= 1) {
            return 1;
        }

        int length = -1;

        int[] size = new int[arr.length];

        for (int i = 0; i < arr.length; ++i) {
            subsequence[i] = -1;
            size[i] = -1;
        }

        subsequence[0] = arr[0];
        size[0] = 0;

        for (int i = 1; i < arr.length; i++) {
            size[i] = ceilIndex(subsequence, i, arr[i]);

            if (length < size[i]) {
                length = size[i];
            }
        }
        return length + 1;
    }

    int ceilIndex(int[] subsequence, int startRight, int key){
        int mid, left = 0, right = startRight, index = 0;
        boolean isIndex = false;

        for (mid = (left + right) / 2; left <= right && !isIndex; mid = (left + right) / 2) {
            if (subsequence[mid] < key) {
                right = mid - 1;
            }
            else if (mid + 1 <= right && subsequence[mid + 1] < key) {
                subsequence[mid + 1] = key;
                index = mid + 1;
                isIndex = true;
            } else {
                left = mid + 1;
            }
        }

        if (!isIndex) {
            if (mid == left) {
                subsequence[mid] = key;
                index = mid;
            }
            else {
                subsequence[mid + 1] = key;
                index = mid + 1;
            }
        }

        return index;
    }

    void findInd(int[] src, int[] ind){
        for (int i = 0; i < ind.length; i++){
            boolean cond = true;
            for (int j = i; j < src.length && cond; j++) {
                if (ind[i] == src[j]) {
                    System.out.print(j + 1 + " ");
                    src[j] = 0;
                    cond = false;
                }
                src[j] = 0;
            }
            if (i + 1 >= ind.length || ind[i + 1] == 0){
                System.out.println();
                return;
            }
        }
    }

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
        int[] num = new int[m.length];
        int result;
        result = find(m,num);
        findInd(m,num);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
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