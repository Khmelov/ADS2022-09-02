package by.it.group151001.hlebanova.lesson07;

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
    int Min(int a, int b, int c){
        if(b<a && b<c) return b;
        else if(a<c && a<b) return a;
        else return c;
    }
    int[][] EditDist(int n, String A, int m, String B){
        int D [][] = new int [n+1][m+1];
        int c = 0;
        for(int i=0;i<=n;i++){
            D[i][0] = i;
        }
        for(int j = 1; j<=m; j++){
            D[0][j] = j;
        }
        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=m; j++){
                if (A.charAt(i-1) != B.charAt(j-1)) c = 1;
                else c = 0;
                D[i][j] = Min(D[i-1][j]+1,D[i][j-1]+1,D[i-1][j-1]+c);
            }
        }
        return D;
    }
    String getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int n = one.length();
        int m = two.length();
        int D[][] = new int[n][m];
        D = EditDist(n,one,m,two);
        String result = "";
        int c = 0;
        int i = n;
        int j = m;
        while (i>0 && j>0){
                if(D[i][j]==D[i-1][j]+1){
                    result ="-" + one.charAt(i-1) + ","+ result;
                    i--;
                } else
                    if(D[i][j]==D[i][j-1]+1){
                        result = "+" + two.charAt(j-1) + "," + result;
                        j--;
                    } else{
                        if (one.charAt(i-1) != two.charAt(j-1)) c = 1;
                        else c = 0;
                        if(D[i][j]==D[i-1][j-1]+c){
                            switch (c){
                                case 1:
                                    result = "~" + two.charAt(j-1) + "," + result;
                                    break;
                                case 0:
                                    result = "#" + "," + result;
                                    break;
                            }
                            i--;
                            j--;
                        }
                    }
        }
        while(j>0){
            result = "+" + two.charAt(j-1) + "," + result;
            j--;
        }
        while(i>0){
            result = "-" + one.charAt(i-1) + "," + result;
            i--;
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