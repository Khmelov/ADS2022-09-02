package by.it.group151003.savitski.lesson06;

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
        int result = 0;
        int[] max_lengthes = new int[n];
        int[] previous_elements = new int[n];
        int[] output_indexes = new int[n];
        int last_maxseq_elem = 0;

        for (int i = 0; i < n; i++) {
            max_lengthes[i] = 1;
            for (int j = 0; j < i; j++) {

                if (m[j] >= m[i] && max_lengthes[j] + 1 > max_lengthes[i]) {
                    max_lengthes[i] = max_lengthes[j] + 1;
                    previous_elements[i] = j;
                }

            }

            if (result < max_lengthes[i]) {
                result = max_lengthes[i];
                last_maxseq_elem = i;
            }
        }

        int count = 0;
        int i = last_maxseq_elem;
        while (previous_elements[i] > 0 || i != 0){
            output_indexes[count] = i + 1;
            i = previous_elements[i];
            count++;
        }
        if (previous_elements[i] == 0 && i == 0){
            output_indexes[count] = 1;
            count++;
        }

        for (int j = count - 1; j >= 0; j--)
            System.out.print(output_indexes[j] + " ");

        System.out.println();
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