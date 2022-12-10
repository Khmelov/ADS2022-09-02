package by.it.group151002.ravodin.lesson07;

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

    static int getEqNum(char first, char second)
    {
        if (first == second)
            return 0;
        else
            return 1;
    }
    String getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        int[][] mat = new int[one.length() + 1][two.length() + 1];
        char[][] ops = new char[one.length() + 1][two.length() + 1];
        for (int i = 0; i <= one.length(); i++) {
            mat[i][0] = i;
            ops[i][0] = 'D';
        }
        for (int i = 0; i <= two.length(); i++) {
            mat[0][i] = i;
            ops[0][i] = 'I';
        }

        for (int i = 1; i <= one.length(); i++)
            for (int j = 1; j <= two.length(); j++) {
                int cost = getEqNum(one.charAt(i - 1), two.charAt(j - 1));
                if(mat[i][j - 1] < mat[i - 1][j] && mat[i][j - 1] < mat[i - 1][j - 1] + cost) {
                    mat[i][j] = mat[i][j - 1] + 1;
                    ops[i][j] = '+';
                }
                else if(mat[i - 1][j] < mat[i - 1][j - 1] + cost) {
                    mat[i][j] = mat[i - 1][j] + 1;
                    ops[i][j] = '-';
                }
                else {
                    mat[i][j] = mat[i - 1][j - 1] + cost;
                    ops[i][j] = (cost == 1) ? '~' : '#';
                }
            }
        String str = "";
        int i = one.length(), j = two.length();
        do {
            char c = ops[i][j];
            str += c;
            str += ',';
            if(c == '~' || c == '#') {
                i--;
                j--;
            }
            else if(c == '-') {
                i --;
            }
            else {
                j --;
            }
        } while((i != 0) || (j != 0));
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return str;
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