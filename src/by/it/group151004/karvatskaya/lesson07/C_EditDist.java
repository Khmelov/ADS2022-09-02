package by.it.group151004.karvatskaya.lesson07;

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
        String result = "";
        int[][] mas = new int[one.length() + 1][two.length() + 1];
        for(int n = 0; n <= one.length(); n++)
        {
            mas[n][0] = n;
        }
        for(int n= 0; n <= two.length(); n++)
        {
            mas[0][n] = n;
        }
        for(int n = 1; n <= one.length(); n++)
        {
            for(int m = 1; m <= two.length(); m++)
            {
                mas[n][m] = Math.min(mas[n - 1][m], mas[n][m - 1]) + 1;
                mas[n][m] = Math.min(mas[n][m], mas[n - 1][m - 1] + ((one.charAt(n - 1) == two.charAt(m - 1))? 0 : 1));
            }
        }
        int n= one.length();
        int m = two.length();
        while(m != 0 && n != 0)
        {
            if(mas[n - 1][m] + 1 == mas[n][m])
            {
                result = "-," + result;
                n -= 1;
            }
            else
                if(mas[n][m - 1] + 1 == mas[n][m])
            {
                result = "+," + result;
                m -= 1;
            }
            else
                if(mas[n - 1][m - 1] + 1 == mas[n][m])
            {
                result = "~," + result;
                m -= 1;
                n -= 1;
            }
            else
            {
                result = "#," + result;
                m -= 1;
                n -= 1;
            }
        }


        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
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