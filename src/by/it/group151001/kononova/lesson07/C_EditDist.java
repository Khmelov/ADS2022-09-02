package by.it.group151001.kononova.lesson07;

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

    int difference(char one, char two) {
        if (one == two) {
            return 0;
        } else return 1;
    }

    int min_of_three(int one, int two, int three) {
        int min = one;
        if (two < min) {
            min = two;
        }
        if (three < min) {
            min = three;
        }
        return min;
    }

    public String getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        String result1 = "";
        String result = "";
        int n = one.length();
        int m = two.length();
        int[][] D = new int[n+1][m+1];

        for (int i = 0; i <= n; i++) {
            D[i][0] = i;
        }
        for (int j = 0; j <= m; j++) {
            D[0][j] = j;
        }

        int c;
        for (int i =1; i<= n; i++) {
            for (int j = 1; j <= m; j++) {
                c = difference(one.charAt(i-1), two.charAt(j-1));
                D[i][j] = min_of_three(D[i-1][j] +1, D[i][j-1]+1, D[i-1][j-1]+c);
            }
        }

        int i = n;
        int j = m;
        boolean f;
        while (i >= 0 && j >= 0) {
            f = true;
            if ((i >0) && (j >0)) {
                c = difference(one.charAt(i - 1), two.charAt(j - 1));
            } else c = 0;
            if (f && (i >0) && (j >0) && (D[i][j] == D[i-1][j-1] + c)) {
                if (c == 1) {
                    result1 = result1 +"~";
                    result1 += two.charAt(j-1);
                } else {
                    result1 = result1 +"#";
                }
                i = i-1;
                j = j-1;
                f = false;
            }
            if (f && (i >0) && (D[i][j] == D[i-1][j] + 1) ) {
                result1 = result1 +"-";
                result1 += one.charAt(i-1);
                i = i - 1;
                f = false;
            }
            if (f && (j >0) && (D[i][j] == D[i][j-1] + 1)) {
                result1 = result1 +"+";
                result1 += two.charAt(j-1);
                j = j - 1;
                f = false;
            }
            if (i == 0 && j == 0) {
                i --;
                j--;
            }
            result1 += ",";
        }
        i = result1.length()-2;
        while (i >= 0) {
            j = i;
            while (i>=0 && result1.charAt(i) != ',') {
                i--;
            }
            result += result1.substring(i+1,j+2);
            i--;
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151001/kononova/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}