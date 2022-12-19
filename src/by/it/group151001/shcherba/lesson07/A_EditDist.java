package by.it.group151001.shcherba.lesson07;

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
    static int getDistanceEdinting(String one, String two) {

        int n = one.length();
        int m = two.length();
        int[][] words = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(words[i], -1);
        }

        class edit {
            int dist(int i, int j) {
                if (words[i][j] == -1) {
                    if (i == 0)
                        words[i][j] = j;
                    else if (j == 0)
                        words[i][j] = i;
                    else {
                        int ins = dist(i , j - 1) + 1;
                        int del = dist(i - 1, j) + 1;
                        int sub = dist(i - 1, j - 1) + diff(one.charAt(i - 1), two.charAt(j - 1));
                        words[i][j] = min(ins, del, sub);
                    }

                }
                return words[i][j];
            }

        }
        return new edit().dist(n, m);
    }

    private static int min(int a, int b, int c) {
        return Integer.min(Integer.min(a, b), c);
    }

    private static int diff (char a, char b) {
        if (a == b)
            return 0;
        else return 1;
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
