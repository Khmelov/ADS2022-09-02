package by.it.group151003.matoshko.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/*
Задача на программирование: наибольшая возростающая подпоследовательность
см.     https://ru.wikipedia.org/wiki/Задача_поиска_наибольшей_увеличивающейся_подпоследовательности
        https://en.wikipedia.org/wiki/Longest_increasing_subsequence

Дано:
    целое число 1≤n≤1000
    массив A[1…n] натуральных чисел, не превосходящих 2E9.

Необходимо:
    Выведите максимальное 1<=k<=n, для которого гарантированно найдётся
    подпоследовательность индексов i[1]<i[2]<…<i[k] <= длины k,
    для которой каждый элемент A[i[k]]больше любого предыдущего
    т.е. для всех 1<=j<k, A[i[j]]<A[i[j+1]].

Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ

    Sample Input:
    5
    1 3 3 2 6

    Sample Output:
    3
*/

// Процедура подсчета наибольшей возрастающей последовательности для
// всех элементов A[i] основного массива A. При этом
// D[i] - длина НВП, заканчивающая на элементе A[i]
// т е это соответствующая этому элементу длина НВП
public class A_LIS {

    private int LISBottomUp(int n,int[] A)
    {
        // Создаем массив D длин НВП, где длинна D[i] заканчивается на элемент A[i]
        int[] D= new int[n];
        // Проход по всем элементам исходного массива
        for(int i=0;i<n;i++)
        {
            // Ихначально инициализируем длину НВП этого элемента единицой
            D[i]=1;
            // Проходим по всем предшествующим элементам массива
            for(int j=0;j<=i-1;j++)
            {
                // Если предшеств. элемент меньше проверяемого и длина его НВП меньше, чем длина НВП
                // этого же предшествующего элемента, то тогда НВП предыдущего элемента является оптимальной
                // подстрокой для A[i], если мы добавим A[i] в конец последовательности A[j], а значит длина такой
                // последовательности будет УЖЕ на одну больше
                if(A[j]<A[i] && D[j]+1>D[i])
                    D[i]=D[j]+1;
            }
        }
        // Осталость найти максимальную длину из всего массива D
        int count=0;
        for(int i=1;i<n;i++)
            if(D[i]>D[count])
                count=i;

        return D[count];


    }

    int getSeqSize(InputStream stream) throws FileNotFoundException {
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
        result= LISBottomUp(n,m);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151003/matoshko/lesson06/dataA.txt");
        A_LIS instance = new A_LIS();
        int result = instance.getSeqSize(stream);
        System.out.print(result);
    }
}
