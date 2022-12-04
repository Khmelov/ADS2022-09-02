package by.it.group151001.danko.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
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
    public static String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }
    String getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int N = one.length() + 1;
        int M = two.length() + 1;
        int[][] tableD = new int[N][M];
        for(int[] row : tableD) {
            Arrays.fill(row, -1);
        }
        for (int i = 0; i < N; ++i)
            tableD[i][0] = i;
        for (int j = 0; j < M; ++j)
            tableD[0][j] = j;

        for (int i = 1; i < N; ++i)
            for (int j = 1; j < M; ++j) {
                int ins = tableD[i][j - 1] + 1;
                int del = tableD[i - 1][j] + 1;
                int sub = tableD[i - 1][j - 1] + compare(one.charAt(i - 1), two.charAt(j - 1));
                tableD[i][j] = Math.min(Math.min(ins, del), sub);
            }
        StringBuilder result = new StringBuilder();

        int i = one.length(), j = two.length();

        while (i != 0 || j != 0){
            if (i > 0 && j > 0 && tableD[i - 1][j - 1] + compare(one.charAt(i - 1), two.charAt(j - 1)) == tableD[i][j]){
                if(one.charAt(i - 1) == two.charAt(j - 1)){
                    result.insert(0, "#,");
                }
                else {
                    result.insert(0, "~" + two.charAt(j - 1) + ",");
                }
                i--;
                j--;
            } else if (j > 0 && tableD[i][j] == tableD[i][j - 1] + 1) {
                result.insert(0, "+" + two.charAt(j - 1) + ",");
                j--;
            } else if ( i > 0 && tableD[i][j] == tableD[i - 1][j] + 1) {
                result.insert(0, "-" + one.charAt(i - 1) + ",");
                i--;
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result.toString();
    }

    int compare(char i, char j) {
        int result = i == j? 0 : 1;
        return result;
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