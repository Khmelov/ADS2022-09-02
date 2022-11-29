package by.it.group151002.krashevskiy.lesson07;

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
        StringBuilder result = new StringBuilder();
        int[][] matrix = new int[one.length() + 1][two.length() + 1];
        matrix[0][0] = 0;
        for (int i = 1; i <= two.length(); i++) {
            matrix[0][i] = matrix[0][i - 1] + 1;
        }
        int costDel = 0;
        int costIns = 0;
        int costRep = 0;
        int match = 0;
        for (int i = 1; i <= one.length(); i++) {
            matrix[i][0] = matrix[i - 1][0] + 1;
            for (int j = 1; j <= two.length(); j++) {
                costDel = matrix[i - 1][j] + 1;
                costIns = matrix[i][j - 1] + 1;
                match = one.charAt(i - 1) == two.charAt(j - 1) ? 0 : 1;
                costRep = matrix[i - 1][j - 1] + match;
                matrix[i][j] = Math.min(Math.min(costDel, costIns), costRep);
            }
        }

        int i = one.length();
        int j = two.length();
        while (i > 0 && j > 0) {
            match = one.charAt(i - 1) == two.charAt(j - 1) ? 0 : 1;
            if (matrix[i][j] == matrix[i][j - 1] + 1) {
                result.insert(0, "+" + two.charAt(i - 1) + ",");
                j--;
            } else if (matrix[i][j] == matrix[i - 1][j] + 1) {
                result.insert(0, "-" + one.charAt(i - 1) + ",");
                i--;
            } else {
                if (match == 0) {
                    result.insert(0, "#,");
                } else {
                    result.insert(0, "~" + two.charAt(j - 1) + ",");
                }
                i--;
                j--;
            }
        }
        if (i > 0) {
            result.insert(0, "-" + one.charAt(i - 1) + ",");
        } else if (j > 0) {
            result.insert(0, "+" + two.charAt(j - 1) + ",");
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result.toString();
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/krashevskiy/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}