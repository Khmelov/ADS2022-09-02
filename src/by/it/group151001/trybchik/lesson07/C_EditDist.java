package by.it.group151001.trybchik.lesson07;

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

    int diff(char a,char b)
    {
        if (a==b)
            return 0;
        else return 1;
    }
    int min (int a,int b,int c)
    {
        if(a<b && a<c)
            return a;
        else if (b<a && b<c)
            return b;
        else return c;
    }
    String GetSolution (String one,String two)
    {
        int len1  = one.length();
        int len2 = two.length();
        StringBuilder result =new StringBuilder();
        int [][] D =new int[len1+1][len2+1];
        for (int j= 0 ;j<=len2;j++)D[0][j] = j;
        for(int i = 0;i<=len1;i++)D[i][0] = i;
        for(int i = 0;i<=len1;i++)
            for(int j = 0;j<=len2;j++)
            {
                if(i != 0 && j!= 0 )
                {
                    int ins = D[i][j-1]+1;
                    int del =D[i-1][j]+1;
                    int sub = D[i-1][j-1]+diff(one.charAt(i-1),two.charAt(j-1));
                    D[i][j] = min(ins,del,sub);
                }
            }
        int i = len1;
        int j = len2;
        while (i>=0 && j>= 0)
        {
            if (i==0 && j==0) break;
                if (i>0 && D[i][j] == D[i - 1][j] + 1) {
                    result.append("-");
                    result.append(one.charAt(i - 1));
                    result.append(",");
                    i--;
                } else if (j>0 && D[i][j] == D[i][j - 1] + 1) {
                    result.append("+");
                    result.append(two.charAt(j - 1));
                    result.append(",");
                    j--;
                } else if (i>0 && j>0 && D[i][j] == D[i - 1][j - 1] + diff(one.charAt(i - 1), two.charAt(j - 1))) {
                    if (diff(one.charAt(i - 1), two.charAt(j - 1)) == 0) {
                        result.append("#");
                        result.append(",");
                        i--;
                        j--;
                    } else {
                        result.append("~");
                        result.append(two.charAt(j - 1));
                        result.append(",");
                        i--;
                        j--;
                    }
            }
            else break;
        }
        StringBuilder revstr = new StringBuilder();
        for (i = result.length()-2;i>=0;i--)
        {
            revstr.append(result.charAt(i));
        }
        revstr.append(",");
        return revstr.toString();
    }
    String getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!


        String result = GetSolution(one,two);
        System.out.printf("%s",result);
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