package by.it.group151004.sakovsky.lesson07;

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
        int[][] table = new int[one.length()+1][two.length()+1];
        for (int i=1; i<=one.length(); i++){
            table[i][0]=i;
        }
        for (int i=1; i<=two.length(); i++){
            table[0][i]=i;
        }
        int cost;
        for(int i=1; i<=one.length(); i++){
            for (int j=1; j<=two.length(); j++){
                cost = one.charAt(i-1)==two.charAt(j-1) ? 0:1;
                table[i][j]=Integer.min(table[i-1][j]+1, Integer.min(table[i][j-1]+1,table[i-1][j-1]+cost));
            }
        }
        char symb;
        char arrA[] = new char[one.length() + 1];
        char arrB[] = new char[two.length() + 1];
        for (int i = 1; i < arrA.length; i++) {
           arrA[i] = one.charAt(i - 1);
        }
        for (int i = 1; i < arrB.length; i++) {
            arrB[i] = two.charAt(i - 1);
        }
        StringBuilder result = new StringBuilder();
        int i = one.length();
        int j = two.length();
        do {
            cost = arrA[i] == arrB[j] ? 0 : 1;
            symb = arrA[i] == arrB[j] ? '#' : '~';
            if (table[i][j] == table[i - 1][j - 1] + cost) {
                result.insert(0, ',');
                result.insert(0, symb);
                if (symb == '~') {
                    result.insert(1, arrB[j]);
                }
                i--;
                j--;
            } else if (table[i][j] == table[i][j - 1] + 1) {
                result.insert(0, ',');
                result.insert(0, '+');
                result.insert(1, arrB[i]);
                j--;
            } else if (table[i][j] == table[i - 1][j] + 1) {
                result.insert(0, ',');
                result.insert(0, '-');
                result.insert(1, arrA[i]);
                i--;
            }
        } while (i > 0 && j > 0);
        if (i > 0) {
            result.insert(0, ',');
            result.insert(0, '-');
            result.insert(1, arrA[i]);
        } else if (j > 0) {
            result.insert(0, ',');
            result.insert(0, '+');
            result.insert(1, arrB[j]);
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