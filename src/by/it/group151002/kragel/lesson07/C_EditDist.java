package by.it.group151002.kragel.lesson07;

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
    Итерационно вычислить алгоритм преобразования двух данных непустых строк
    Вывести через запятую редакционное предписание в формате:
     операция("+" вставка, "-" удаление, "~" замена, "#" копирование)
     символ замены или вставки

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    #,#,

    Sample Input 2:
    short
    ports
    Sample Output 2:
    -s,~p,#,#,#,+s,

    Sample Input 3:
    distance
    editing
    Sample Output 2:
    +e,#,#,-s,#,~i,#,-c,~g,


    P.S. В литературе обычно действия редакционных предписаний обозначаются так:
    - D (англ. delete) — удалить,
    + I (англ. insert) — вставить,
    ~ R (replace) — заменить,
    # M (match) — совпадение.
*/


public class C_EditDist {
    int min(int a, int b, int c) {
        return a < b && a < c ? a : (b < a && b < c ? b : c);
    }

    String getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int m = one.length(), n = two.length();
        int[][] dist = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            dist[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dist[0][j] = j;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dist[i][j] = min(dist[i - 1][j] + 1,
                        dist[i][j - 1] + 1,
                        dist[i - 1][j - 1] + (one.charAt(i - 1) == two.charAt(j - 1) ? 0 : 1));
            }
        }
        String result = "";
        int i = m, j = n;
        while (i != 0 && j != 0) {
            if (dist[i][j] == dist[i][j - 1] + 1) {
                result = "+" + two.charAt(--j) + result;
            } else if (dist[i][j] == dist[i - 1][j - 1] + 1) {
                result = "~" + two.charAt(--j) + result;
                --i;
            } else if (dist[i][j] == dist[i - 1][j] + 1) {
                result = "-" + one.charAt(--i) + result;
            } else if (dist[i][j] == dist[i - 1][j - 1]) {
                result = "#" + result;
                --i;
                --j;
            }
            result = "," + result;
        }
        while (j > 0)
            result = ",+" + two.charAt(--j) + result;
        while (i > 0)
            result = ",-" + one.charAt(--i) + result;
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result.substring(1);
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/kragel/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
    }

}