package by.it.group151002.strukov.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
        return Integer.min(a, Integer.min(b, c));
    }

    String getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int firstLength = one.length();
        int secondLength = two.length();
        int[][] dist = new int[firstLength + 1][secondLength + 1];
        int i, j;
        for (i = 0; i <= firstLength; i++)
            dist[i][0] = i;
        for (j = 0; j <= secondLength; j++)
            dist[0][j] = j;
        for (i = 1; i <= firstLength; i++)
            for (j = 1; j <= secondLength; j++) {
                dist[i][j] = min(dist[i - 1][j] + 1,
                        dist[i][j - 1] + 1,
                        dist[i - 1][j - 1] + (one.charAt(i - 1) == two.charAt(j - 1) ? 0 : 1));
            }
        String result = "";
        i = firstLength;
        j = secondLength;
        while (i != 0 && j != 0) {
            if (dist[i][j] == dist[i][j - 1] + 1)
                result = result.concat("+" + two.charAt(--j));
            else if (dist[i][j] == dist[i - 1][j - 1] + 1) {
                result = result.concat("~" + two.charAt(--j));
                --i;
            }
            else if (dist[i][j] == dist[i - 1][j] + 1)
                result = result.concat("-" + one.charAt(--i));
            else if (dist[i][j] == dist[i - 1][j - 1]) {
                result = result.concat("#");
                --i;
                --j;
            }
            result = result.concat(i == 0 && j == 0 ? "" : ",");
        }
        while (j > 0)
            result = result.concat("+" + two.charAt(--j));
        while (i > 0)
            result = result.concat("-" + one.charAt(--i));

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}