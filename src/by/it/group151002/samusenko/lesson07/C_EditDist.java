package lesson07;

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

    private static int minAmongThree(int n1, int n2, int n3) {
        /*int res;
        if (n1 < n2){
            if (n1 < n3)
                res = n1;
            else
                res = n3;
        }
        else{
            if (n2 < n3)
                res = n2;
            else
                res = n3;
        }*/

        return Math.min(Math.min(n1,n2), n3);
    }

    String getDistanceEdinting(String s1, String s2) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int m = s1.length();
        int n = s2.length();
        int[][] Mx = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            Mx[i][0] = i;
        }
        for (int j = 0; j < n; j++) {
            Mx[0][j] = j;
        }
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                int term;
                //int term = s1.charAt(i - 1) == s2.charAt(j - 1) ? 0 : 1;
                if(s1.charAt(i - 1) == s2.charAt(j - 1))
                    term = 0;
                else
                    term =1;
                Mx[i][j] = minAmongThree(Mx[i][j - 1] + 1, Mx[i - 1][j] + 1, Mx[i - 1][j - 1] + term);
            }
        }
        StringBuilder result = new StringBuilder();
        int i = m;
        int j = n;
        while (i > 0 && j > 0) {
            int term = s1.charAt(i - 1) == s2.charAt(j - 1) ? 0 : 1;
            if (Mx[i][j] == Mx[i][j - 1] + 1) {
                result.insert(0, ",");
                result.insert(0, s2.charAt(i - 1));
                result.insert(0, "+");
                j--;
            } else if (Mx[i][j] == Mx[i - 1][j] + 1) {
                result.insert(0, ",");
                result.insert(0, s1.charAt(i - 1));
                result.insert(0, "-");
                i--;
            } else {
                if (term == 0) {
                    result.insert(0, ",");
                    result.insert(0, "#");
                } else {
                    result.insert(0, ",");
                    result.insert(0, s2.charAt(j - 1));
                    result.insert(0, "~");
                }
                i--;
                j--;
            }
        }
        if (i > 0) {
            result.insert(0, ",");
            result.insert(0, s1.charAt(i - 1));
            result.insert(0, "-");
        } else if (j > 0) {
            result.insert(0, ",");
            result.insert(0, s2.charAt(j - 1));
            result.insert(0, "+");
        }

        return result.toString();
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
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