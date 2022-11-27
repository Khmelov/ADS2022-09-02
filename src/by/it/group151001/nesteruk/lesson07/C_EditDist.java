package by.it.group151001.nesteruk.lesson07;

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

    int diff(char one, char two) {
        if(one == two) {
            return 0;
        }
        else {
            return 1;
        }
    }

    int min(int one, int two, int three) {
        int result = one;
        if(two < result) {
            result = two;
        }
        if(three < result) {
            result = three;
        }
        return result;
    }

    String getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        String preres = ",";
        int n = one.length(), m = two.length();
        int[][] D = new int[n+1][m+1];
        for(int i = 1; i < n; i++) {
            D[i][0] = i;
        }
        for(int i = 1; i < n; i++) {
            D[0][i] = i;
        }
        for(int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int k = diff(one.charAt(i-1), two.charAt(j-1));
                D[i][j] = min(D[i-1][j] +1, D[i][j-1]+1, D[i-1][j-1]+k);
            }
        }
        int i = n, j = m;
        int k = 0;
        while (i >= 0 && j >= 0) {
            if(i > 0 && j > 0) {
                k = diff(one.charAt(i - 1), two.charAt(j - 1));
            }
            if(i > 0 && j > 0 && D[i-1][j-1] + k == D[i][j]) {
                if (k == 1) {
                    preres += two.charAt(j-1);
                    preres = preres +"~";
                } else {
                    preres = preres +"#";
                }
                i = i-1;
                j = j-1;
            }
            else if(j > 0 && D[i][j-1] + 1 == D[i][j]) {
                preres += two.charAt(j-1);
                preres = preres +"+";
                j = j - 1;
            }
            else if(i > 0 && D[i-1][j] + 1 == D[i][j]) {
                preres += one.charAt(i-1);
                preres = preres +"-";
                i = i - 1;
            }
            if (i == 0 && j == 0) {
                break;
            }
            preres += ",";
        }
        char[] result = new char[preres.length()];
        for(int t = 0; t < preres.length(); t++) {
            result[preres.length()-t-1] = preres.charAt(t);
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return new String(result);
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