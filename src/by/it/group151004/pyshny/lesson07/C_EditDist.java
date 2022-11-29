package by.it.group151004.pyshny.lesson07;

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
        int len1 = one.length(),len2 = two.length();
        int[][] arr = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            arr[i][0] = i;
        }
        for (int i = 1; i <= len2; i++) {
            arr[0][i] = i;
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                int cost = one.charAt(i - 1) == two.charAt(j - 1) ? 0 : 1;
                arr[i][j] = Integer.min(Integer.min(arr[i - 1][j] + 1, arr[i][j - 1] + 1), arr[i - 1][j - 1] + cost);
            }
        }
        int i=len1,j=len2,cost;
        char c;
        StringBuilder result = new StringBuilder();
        char arr1[] = new char[i + 1];
        char arr2[] = new char[j + 1];
        for (int k = 1; k < arr1.length; k++) {
            arr1[k] = one.charAt(k - 1);
        }
        for (int k = 1; k < arr2.length; k++) {
            arr2[k] = two.charAt(k - 1);
        }
        do {
            if(arr1[i] == arr2[j])cost=0;else cost=1;
            if(arr1[i] == arr2[j])c='#';else c='~';
            if (arr[i][j] == arr[i - 1][j - 1] + cost) {
                result.insert(0, ',');
                result.insert(0, c);
                if (c == '~') {
                    result.insert(1, arr2[j]);
                }
                i--;
                j--;
            } else if (arr[i][j] == arr[i][j - 1] + 1) {
                result.insert(0, ',');
                result.insert(0, '+');
                result.insert(1, arr2[i]);
                j--;
            } else if (arr[i][j] == arr[i - 1][j] + 1) {
                result.insert(0, ',');
                result.insert(0, '-');
                result.insert(1, arr1[i]);
                i--;
            }
        } while (i > 0 && j > 0);
        if (i > 0) {
            result.insert(0, ',');
            result.insert(0, '-');
            result.insert(1, arr1[i]);
        } else if (j > 0) {
            result.insert(0, ',');
            result.insert(0, '+');
            result.insert(1, arr2[j]);
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