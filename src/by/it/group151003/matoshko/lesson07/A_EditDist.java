package by.it.group151003.matoshko.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/

Дано:
    Две данных непустые строки длины не более 100, содержащие строчные буквы латинского алфавита.

Необходимо:
    Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ
    Рекурсивно вычислить расстояние редактирования двух данных непустых строк

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    0

    Sample Input 2:
    short
    ports
    Sample Output 2:
    3

    Sample Input 3:
    distance
    editing
    Sample Output 3:
    5

*/

public class A_EditDist {


    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        // Строк и столбцов в матрице должно быть на 1 больше чем длина слова
        // Строки - слово А. Столбцы - слово В
        // Преобразуем слово А в слово В
        int N=one.length()+1;
        int M=two.length()+1;
        int[][] table = new int[N][M];

        // Инициализация массива -1
        for (int i = 0; i < N; ++i)
            Arrays.fill(table[i], -1);

        // Рекурсивное вычисление
        FindDist(table,one,two,N-1,M-1);

        int res = table[N-1][M-1];

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return res;
    }

    private int FindDist(int[][] table, String s1, String s2,int i, int j)
    {
        // Просмотр массива начинается рекурсивно с самой правой нижней ячейки
        // Рекурсивный спуск заканчивается на элементе 0.0 и начинается инициализация
        if(table[i][j] ==-1)
            // инициализация первой строки
            if(i==0)
                table[i][j]=j;
            // инициализация первого столбца
            else if(j==0)
                table[i][j]=i;
            else {
                // вычисление ячейки на основании трех слева и сверху
                int insert = FindDist(table,s1,s2,i,j-1)+1;
                int delete = FindDist(table,s1,s2,i-1,j)+1;
                int change = FindDist(table,s1,s2,i-1,j-1)+ ((s1.charAt(i-1) == s2.charAt(j-1)) ? 0 : 1);
                table[i][j]=Math.min(Math.min(insert,delete),change);
            }
            return table[i][j];
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }
}
