package by.it.group151003.barilko.lesson07;

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

    String getDistanceEdinting(String first, String second) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int[][] arr = new int[first.length() + 1][second.length() + 1];
        for(int i = 0; i < first.length() + 1; ++i)
        {
            arr[i][0] = i;
            if(i < second.length())
                arr[0][i] = i;
        }

        for(int i = 1; i < first.length() + 1; ++i)
            for(int j = 1; j < second.length() + 1; ++j)
            {
                int ins = arr[i][j - 1] + 1;
                int del = arr[i - 1][j] + 1;
                int sub = arr[i - 1][j - 1] + (first.charAt(i - 1) == second.charAt(j - 1) ? 0 : 1);
                arr[i][j] = Integer.min(Integer.min(ins, del), sub);
            }

        String result = "";
        int i = first.length();
        int j = second.length();
        while(i > 0 && j > 0)
        {
            int ins = arr[i][j - 1]; // 0
            int del = arr[i - 1][j]; // 1
            int sub = arr[i - 1][j - 1]; // 2

            int step = Integer.min(Integer.min(ins, del), sub);
            StringBuilder temp = new StringBuilder();
            if(step == arr[i][j])
            {
                --i; --j;
                temp.append("#,");
            }
            else
            {
                step = step == ins ? 0 : step == del ? 1 : 2;
                switch (step) 
                {
                    case 0:
                        --j;
                        temp.append("+ ,"); 
                        temp.setCharAt(1, second.charAt(j));
                        break;
                    case 1:
                        --i;
                        temp.append("- ,"); 
                        temp.setCharAt(1, first.charAt(i));
                        break;
                    case 2:
                        --j;
                        --i;
                        temp.append("~ ,"); 
                        temp.setCharAt(1, second.charAt(j));
                        break;
                }
            }
            result = temp.toString() + result;
        }
        StringBuilder temp = new StringBuilder();
        if(j > 0)
        {
            --j;
            temp.append("+ ,"); 
            temp.setCharAt(1, second.charAt(j));
        }
        if(i > 0)
        {
            --i;
            temp.append("- ,"); 
            temp.setCharAt(1, first.charAt(i));

        }
        result = temp.toString() + result;
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