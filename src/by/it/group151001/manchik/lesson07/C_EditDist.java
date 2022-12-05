package by.it.group151001.manchik.lesson07;

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

    static int INF = 101;
    int min(int a, int b, int c){
        if (a<=b && a<=c) return a;
        else if (b<=a && b<=c) return b;
        else return c;
    }
    int diff(char a, char b){
        if (a==b) return 0; else return 1;
    }
    String EditDist(int D[][], int n, int m, String one, String two){
        int i,j;
        StringBuilder res = new StringBuilder();
        StringBuilder fres = new StringBuilder();
        for (i = 0 ; i<=n;i++) D[i][0] = i;
        for (j = 0; j<=m;j++) D[0][j] = j;
        for (i = 1; i<=n;i++){
            for (j = 1; j<=m;j++){
                D[i][j] = min(D[i-1][j]+1,
                        D[i][j-1]+1,
                        D[i-1][j-1] + diff(one.charAt(i-1),
                                two.charAt(j-1)));
            }
        }
        i = n;
        j = m;
        int minnum = 0;
        while (i>=0 && j>=0) {
            if (i == 0 && j==0 ) break;
            if (i > 0 && j > 0) {
                minnum = min(D[i - 1][j], D[i][j - 1], D[i - 1][j - 1]);
                if (minnum == D[i - 1][j - 1]) {
                    if (diff(one.charAt(i - 1), two.charAt(j - 1)) == 0) {
                        i--;
                        j--;
                        res.append("#,");
//                        System.out.printf("#,");
                    } else {
                        res.append("~");
                        res.append(two.substring(j - 1, j));
                        res.append(",");
//                        System.out.printf("~%c,", two.charAt(j - 1));
                        i--;
                        j--;
                    }
                } else if (minnum == D[i][j - 1]) {
                    res.append("+");
                    res.append(two.substring(j - 1, j));
                    res.append(",");
//                    System.out.printf("+%c,", two.charAt(j - 1));
                    j--;
                } else if (minnum == D[i - 1][j]) {
                    res.append("-");
                    res.append(one.substring(i - 1, i));
                    res.append(",");
//                    System.out.printf("-%c,", one.charAt(i - 1));
                    i--;
                }
            } else {
                if ((j>0) && D[i][j] > D[i][j - 1]) {
                    res.append("+");
                    res.append(two.substring(j - 1, j));
                    res.append(",");
//                    System.out.printf("+%c,", two.charAt(j - 1));
                    j--;
                } else if (i>0){
                    res.append("-");
                    res.append(one.substring(i - 1, i));
                    res.append(",");
//                    System.out.printf("-%c,", one.charAt(i - 1));
                    i--;
                }
            }
        }
        j = res.length()-2;
        while (j >= 0) {
            i = j;
            while (j>=0 && res.charAt(j) != ',') {
                j--;
            }
            fres.append(res.substring(j+1,i+2));
            j--;
        }
        return fres.toString();
    }
    String getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int strlen1 = one.length();
        int strlen2 = two.length();
        int D[][] = new int[strlen1+1][strlen2+1];

        String result = EditDist(D,strlen1,strlen2,one, two);
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