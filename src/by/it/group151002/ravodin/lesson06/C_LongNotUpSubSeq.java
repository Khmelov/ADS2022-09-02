package by.it.group151002.ravodin.lesson06;

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

    int find_not_up_seq(int n, int[] numbers, int[] index){
        int[] extra_index = new int[n+1];
        int[] temp = new int[n];
        int last = numbers[0];
        int counter = 1;
        temp[0] = 1;
        index[0] = 0;
        extra_index[0] = 0;
        for(int i = 1; i < n; ++i){
            temp[i] = temp[i-1];
            if(numbers[i] <= last) {
                index[temp[i]] = i;
                ++temp[i];
                last = numbers[i];
            } else {
                if(numbers[i] <= numbers[i-1]) {
                    counter++;
                }
                extra_index[counter - 1] = i;
                if(counter >= temp[i]){
                    int j = 0;
                    while(numbers[index[temp[i] - j - 1]] < numbers[extra_index[0]])
                        ++j;
                    for(int z = 0;z < counter;j--, z++){
                        index[temp[i] - j] = extra_index[z];
                    }
                    ++temp[i];
                    last = numbers[i];
                    counter = 1;
                }
            }
        }
        return temp[n-1];
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
        //тут реализуйте логику задачи методами динамического программирования (!!!);
        //Integer seq_length = -1;
        Integer seq_length = -1;
        int[] index = new int[n];
        int result = find_not_up_seq(n, m, index);
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