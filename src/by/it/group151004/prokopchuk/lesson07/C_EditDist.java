package by.it.group151004.prokopchuk.lesson07;

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

    int min (int a ,int b ,int c) {
        return Integer.min(Integer.min(a,b),c);
    }

    String getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        int i = one.length();
        int j = two.length();
        int[][] D = new int[i + 1][j + 1];
        for (int k = 0; k < one.length(); k++) {
            D[k][0] = k;
        }
        for (int k = 0; k < two.length(); k++) {
            D[0][k] = k;
        }
        for (int k = 1; k < i + 1; k++) {
            for (int l = 1; l < j + 1; l++) {
                int m = one.charAt(k - 1) == two.charAt(l - 1) ? 0 : 1;
                D[k][l] = min(D[k][l-1] + 1,
                        D[k-1][l] + 1,
                        D[k - 1][l - 1] + m);
            }
        }

        int k = i;
        int l = j;
        StringBuilder result = new StringBuilder();
        while ( k > 0 && l > 0) {
            int m = one.charAt(k - 1) == two.charAt(l - 1) ? 0 : 1;
            if (D[k][l] == D[k][l-1] + 1) {
                result.insert(0, ",");
                result.insert(0, two.charAt(k - 1));
                result.insert(0, "+");
                l--;
            } else if (D[k][l] == D[k-1][l] + 1) {
                result.insert(0, ",");
                result.insert(0, one.charAt(k- 1));
                result.insert(0, "-");
                k--;
            } else {
                if (m == 0) {
                    result.insert(0, ",");
                    result.insert(0, "#");
                } else {
                    result.insert(0, ",");
                    result.insert(0, two.charAt(l-1));
                    result.insert(0, "~");
                }
                k--;
                l--;
            }
        }
        if (k > 0) {
            result.insert(0, ",");
            result.insert(0, one.charAt(k-1));
            result.insert(0, "-");
        } else if ( l > 0) {
            result.insert(0, ",");
            result.insert(0, two.charAt(l-1));
            result.insert(0, "+");
        }


        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result.toString();
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151004/prokopchuk/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}