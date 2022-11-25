package by.it.group151002.krumkachev.lesson07;

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
        return (a > b) ? Math.min(b, c) : Math.min(a, c);
    }

    String getDistanceEditing(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int[][] matrix = new int[one.length() + 1][two.length() + 1];
        for (int i = 0; i <= one.length(); i++)
            matrix[i][0] = i;
        for (int j = 0; j <= two.length(); j++)
            matrix[0][j] = j;
        for (int i = 1; i <= one.length(); i++) {
            for (int j = 1; j <= two.length(); j++) {
                int replacementCost = (one.charAt(i - 1) == two.charAt(j - 1)) ? 0 : 1;
                matrix[i][j] = min(matrix[i][j - 1] + 1, matrix[i - 1][j] + 1, matrix[i - 1][j - 1] + replacementCost);
            }
        }
        StringBuilder editing = new StringBuilder();
        int i = one.length();
        int j = two.length();
        while (i != 0 && j != 0) {
            int min = min(matrix[i - 1][j], matrix[i][j - 1], matrix[i - 1][j - 1]);
            if (min == matrix[i - 1][j - 1]) {
                if (one.charAt(i - 1) == two.charAt(j - 1))
                    editing.append('#');
                else
                    editing.append('~').append(two.charAt(j - 1));
                i--; j--;
            }
            else if (min == matrix[i - 1][j])
                editing.append('-').append(one.charAt(--i));
            else if (min == matrix[i][j - 1])
                editing.append('+').append(two.charAt(--j));
            editing.append(',');
        }
        while (i != 0)  editing.append('-').append(one.charAt(--i)).append(',');
        while (j != 0)  editing.append('+').append(two.charAt(--j)).append(',');

        StringBuilder result = new StringBuilder();
        for (i = editing.length() - 1; i >= 0; ) {
            j = i - 1;
            while (j >= 0 && editing.charAt(j) != ',')
                j--;
            result.append(editing.substring(j + 1, i + 1));
            i = j;
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result.toString();
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEditing(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEditing(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEditing(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEditing("", ""));
    }

}