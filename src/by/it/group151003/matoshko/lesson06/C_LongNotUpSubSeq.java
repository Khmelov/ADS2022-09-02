package by.it.group151003.matoshko.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLOutput;
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

    public int global;
    public int[] C;
    private int LISBottomUp(int n,int[] A)
    {
        // Создаем массив D длин НВП, где длинна D[i] заканчивается на элемент A[i]
        int[] D= new int[n];
        C= new int[n];
        // Проход по всем элементам исходного массива
        for(int i=0;i<n;i++)
        {
            // Ихначально инициализируем длину НВП этого элемента единицой
            D[i]=1;
            C[i]=-1;
            // Проходим по всем предшествующим элементам массива
            for(int j=0;j<=i-1;j++)
            {
                // Если предшеств. элемент меньше проверяемого и длина его НВП меньше, чем длина НВП
                // этого же предшествующего элемента, то тогда НВП предыдущего элемента является оптимальной
                // подстрокой для A[i], если мы добавим A[i] в конец последовательности A[j], а значит длина такой
                // последовательности будет УЖЕ на одну больше
                if(A[j]>=A[i] && D[j]+1>D[i]) {
                    D[i] = D[j] + 1;
                    C[i]=j;
                }
            }
        }
        // Осталость найти максимальную длину из всего массива D
        int count=0;
        for(int i=1;i<n;i++)
            if(D[i]>D[count])
                count=i;

        // Возвращаем индекс максимального элемента и сам элемент
        global=count;
        return D[count];


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
        int result = 0;
        result=LISBottomUp(n,m);

        // Вывод всех индексов в обратном порядке(=1 чтобы нумерация с единицы шла)
        int i=result;
        int ind=0;
        // Массив индексов
        int res[]=new int[result];

        // Переписываем индексы(их количество равно длине последовательности)
        while(i!=0)
        {
            // global - индекс последнего элемента
            res[ind]=global;
            ind++;
            // Сдвигаем записываемый индекс на предыдущий индекс
            // Где по индексу текущего элемента в C хранится индекс предыдущего элемента
            global=C[global];
            i--;
        }

        // Вывод массива в обратном порядке
        for(int k=res.length-1;k>=0;k--)
        {
            System.out.print(res[k]+1+" ");
        }
        System.out.println("");

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151003/matoshko/lesson06/dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int result = instance.getNotUpSeqSize(stream);
        System.out.print(result);
    }

}