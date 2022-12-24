package by.it.group151003.yagnish.lesson07;

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

    int Diff(char one,char two){
        return one == two ? 0 : 1;
    }
    int Diff2(int one,int two){
        if (one >= two) return one-two;
    else return two-one;
    }
    String  getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int [][] d = new int[one.length()+1][two.length()+1];
        for (int i = 0; i <= one.length(); i++)
            d[i][0] = i;
        for (int j = 0; j <= two.length(); j++)
            d[0][j] = j;
        for (int i = 1;i<= one.length();i++)
            for (int j = 1; j<= two.length();j++){
                d[i][j] = Integer.min(Integer.min(d[i-1][j]+1,d[i][j-1]+1),d[i-1][j-1]+ Diff(one.charAt(i-1),two.charAt(j-1)))   ;
            }
        int i = one.length();
        int j = two.length();
        String res="";
        while ((i>0)||(j>0)){
                if (j>0&&(d[i][j] == d[i][j-1]+1)){
                    res="+"+two.charAt(i-1 + Diff2(one.length(),two.length()))+","+res;
                    j--;
                } else
                if
                (i>0 &&j>0&&(d[i][j] == d[i-1][j-1]+1)){
                    res="~"+two.charAt(j-1)+","+res;
                    i--;
                    j--;
                } else
                if (i>0&&(d[i][j] == d[i-1][j]+1)){
                    res="-"+one.charAt(i-1)+","+res;
                    i--;
                } else
                if (i>0 &&j>0&&(d[i][j] == d[i-1][j-1])){
                    res="#,"+res;
                    i--;
                    j--;
                }
            }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return res;
    }


    public static void main(String[]a1rgs) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}