package by.it.group151004.gonzarevich.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Stack;

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
        int[][] D = new int[one.length()+1][two.length()+1];
        for (int i = 0; i <= one.length(); i++) {
            D[i][0] = i;
        }
        for (int i = 0; i <= two.length(); i++) {
            D[0][i] = i;
        }

        for (int row = 1; row <= one.length(); row++) {
            for (int column = 1; column <= two.length(); column++) {
                int diff = 0;
                if (one.charAt(row-1) != two.charAt(column-1)) ++diff;
                D[row][column] = Math.min(D[row-1][column-1]+diff, Math.min(D[row-1][column]+1, D[row][column-1]+1));
            }
        }
        Stack<String> res = new Stack<String>();

        int row = one.length();
        int column = two.length();
        while (0 != row && 0 != column) {
            if (D[row-1][column] == D[row][column]-1) {
                res.push(",");
                res.push(String.valueOf(one.charAt(row-1)));
                res.push("+");
                row-=1;
            } else if (D[row][column-1] == D[row][column]-1) {
                res.push(",");
                res.push(String.valueOf(two.charAt(column-1)));
                res.push("-");
                column-=1;
            } else if (D[row-1][column-1] == D[row][column]-1) {
                res.push(",");
                res.push(String.valueOf(one.charAt(row-1)));
                res.push("~");
                row-=1;
                column-=1;
            } else if (D[row-1][column-1] == D[row][column]) {
                res.push(",");
                res.push("#");
                column-=1;
                row-=1;
            }
        }
        StringBuilder result = new StringBuilder("");
        while(!res.isEmpty()) {
            result.append(res.pop());
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result.toString();
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