package by.it.group151003.harlap.lesson07;

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

    String getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        int[][] L = new int[one.length() + 1][two.length() + 1];
        for (int i = 0; i <= one.length(); i++)
            for (int j = 0; j <= two.length(); j++) {
                if (i == 0) L[i][j] = j;
                else if (j == 0) L[i][j] = i;
                else {
                    int inscost = L[i][j - 1] + 1;
                    int delcost = L[i - 1][j] + 1;
                    int subscost = L[i - 1][j - 1] + ((one.charAt(i - 1) == two.charAt(j - 1)) ? 0 : 1);
                    L[i][j] = Math.min(Math.min(inscost, delcost), subscost);
                }
            }

        StringBuilder result = new StringBuilder();
        int i = one.length();
        int j = two.length();
        while (i != 0 || j != 0) {
            if (L[i][j] == L[i][j - 1] + 1) {
                result.append("+").append(two.charAt(j - 1)).append(",");
                --j;
            } else if (L[i][j] == L[i - 1][j] + 1) {
                result.append("-").append(one.charAt(i - 1)).append(",");
                --i;
            } else {
                if (L[i][j] == L[i - 1][j - 1]) {
                    result.append("#,");
                } else
                    result.append("~").append(two.charAt(j - 1)).append(",");
                i--;
                j--;
            }
        }

        StringBuilder res = new StringBuilder();
        int count = result.length() - 2;
        while (count >= 0) {
            int len = 0;
            String str = "";
            while (count >= 0 && result.charAt(count) != ',') {
                count--;
                len++;
            }
            res.append(result.substring(count + 1, count + len + 1));
            res.append(str).append(",");
            count--;
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return res.toString();
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
    }

}