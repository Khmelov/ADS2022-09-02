package by.it.group151004.bosko.lesson07;

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

    int min(int a, int b, int c){
        return Integer.min(a, Integer.min(b, c));
    }

    int diff(char a, char b){
        if (a == b)
            return 0;
        else
            return 1;
    }

    String getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        StringBuilder result = new StringBuilder();
        int[][] arr = new int[one.length() + 1][two.length() + 1];
        for (int i = 0; i < arr.length; i++)
            arr[i][0] = i;
        for (int j = 0; j < arr[0].length; j++)
            arr[0][j] = j;
        for (int i = 1; i < arr.length; i++)
            for (int j = 1; j < arr[i].length; j++)
                arr[i][j] = min(arr[i - 1][j] + 1, arr[i][j - 1] + 1, arr[i - 1][j - 1] + diff(one.charAt(i - 1), two.charAt(j - 1)));
        // --+ \- |~
        int i = one.length();
        int j = two.length();
        while (i > 0 && j > 0) {
            result.insert(0, ",");
            boolean flag = one.charAt(i - 1) == two.charAt(j - 1);
            int c = flag ? 0 : 1;
            char symbol = flag ? '#' : '~';
            if (arr[i][j] == arr[i - 1][j - 1] + c) {
                if (symbol == '~')
                    result.insert(0, "~" + two.charAt(j - 1));
                else
                    result.insert(0, symbol);
                --j;
                --i;
            }
            else
            if (arr[i][j] == arr[i - 1][j] + 1) {
                result.insert(0, '-');
                result.insert(1, one.charAt(i - 1));
                --i;
            }
            else if (arr[i][j] == arr[i][j - 1] + 1) {
                result.insert(0, '+');
                result.insert(1, two.charAt(j - 1));
                --j;
            }
        }

        if (i > 0) {
            result.insert(0,',');
            result.insert(0,'-');
            result.insert(1, one.charAt(i - 1));
        } else if (j > 0) {
            result.insert(0,',');
            result.insert(0,'+');
            result.insert(1, two.charAt(j - 1));
        };
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