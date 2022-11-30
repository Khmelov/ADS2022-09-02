package by.it.group151001.danko.lesson07;

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
    Итерационно вычислить расстояние редактирования двух данных непустых строк

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

public class B_EditDist {


    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int M = one.length() + 1;
        int N = two.length() + 1;
        int[][] tableD = new int[M][N];
        for(int[] row : tableD) {
            Arrays.fill(row, -1);
        }
        for (int i = 0; i < M; ++i)
            tableD[i][0] = i;
        for (int j = 0; j < N; ++j)
            tableD[0][j] = j;

        for (int i = 1; i < M; ++i)
            for (int j = 1; j < N; ++j) {
                int ins = tableD[i][j - 1] + 1;
                int del = tableD[i - 1][j] + 1;
                int sub = tableD[i - 1][j - 1] + compare(one.charAt(i - 1), two.charAt(j - 1));
                tableD[i][j] = Math.min(Math.min(ins, del), sub);
            }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return tableD[M - 1][N - 1];
    }


    int compare(char i, char j) {
        int result = i == j? 0 : 1;
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        B_EditDist instance = new B_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}