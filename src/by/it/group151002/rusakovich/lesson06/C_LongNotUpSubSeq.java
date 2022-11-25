package by.it.group151002.rusakovich.lesson06;

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

    public static int res_l = -1;
    int[] find_not_up_seq(int n, int[] arr){
        int[] index = new int[n];
        int[] aux_index = new int[n];
        int[] temp = new int[n+1];
        int last_el = arr[0];
        int aux_count = 1;
        temp[0] = 1;
        index[0] = 0;
        aux_index[0] = 0;
        for(int i = 1; i <= n; ++i){
            temp[i] = temp[i-1];
            if(arr[i] <= last_el) {
                index[temp[i]] = i;
                ++temp[i];
                last_el = arr[i];
            } else {
                if(arr[i] <= arr[i-1]) {
                    aux_count++;
                }
                aux_index[aux_count - 1] = i;
                if(aux_count >= temp[i]){
                    int j = 0;
                    while(arr[index[temp[i] - j - 1]] < arr[aux_index[0]])
                        ++j;
                    for(int z = 0;z < aux_count;j--, z++){
                        index[temp[i] - j] = aux_index[z];
                    }
                    ++temp[i];
                    last_el = arr[i];
                    aux_count = 1;
                }
            }
        }
        res_l = temp[n];
        return index;
    }


    int[] getNotUpSeqSize(InputStream stream) throws FileNotFoundException {
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
        return find_not_up_seq(n-1, m);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/rusakovich/lesson06/dataC1.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int[] index = instance.getNotUpSeqSize(stream);
        System.out.println(res_l);
        for(int i = 0; i < res_l; i++)
            System.out.print(index[i]+1 + " ");
    }

}