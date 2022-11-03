package by.it.group151001.yankova.lesson07;

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
        String s1l = null, s1r = null, s2l = null, s2r = null;
        if(one.length() <= 1 || two.length() <= 1){
            StringBuilder res = new StringBuilder();
            if (one.length() == 0) {
                for (int i = 0; i < two.length(); ++i) {
                    res.append("+").append(two.charAt(i)).append(",");
                }
            } else if (two.length() == 0) {
                for (int i = 0; i < one.length(); ++i) {
                    res.append("-").append(one.charAt(i)).append(",");
                }
            } else if (one.length() <= two.length()) {
                if (two.indexOf(one.charAt(0)) == -1) {
                    res.append("~").append(two.charAt(0)).append(",");
                    for (int i = 1; i < two.length(); ++i) {
                        res.append("+").append(two.charAt(i)).append(",");
                    }
                } else {
                    for (int i = 0; i < two.length(); ++i) {
                        if (one.charAt(0) == two.charAt(i)) {
                            res.append("#,");
                        } else {
                            res.append("+").append(two.charAt(i)).append(",");
                        }
                    }
                }
            } else {
                if (one.indexOf(two.charAt(0)) == -1) {
                    res.append("~").append(two.charAt(0)).append(",");
                    for (int i = 1; i < one.length(); ++i) {
                        res.append("-").append(one.charAt(i)).append(",");
                    }
                } else {
                    for (int i = 0; i < one.length(); ++i) {
                        if (two.charAt(0) == one.charAt(i)) {
                            res.append("#,");
                        } else {
                            res.append("-").append(one.charAt(i)).append(",");
                        }
                    }
                }
            }
            return res.toString();
        } else {
            if(two.length() <= one.length()){
                s1l = one.substring(0, one.length()/2);
                s1r = one.substring(one.length()/2, one.length());
                int[] d = getLastRow(s1l, two);
                int[] e = getLastRow(s1r, two);
                int k=0;
                for(int i = 1; i <= two.length(); i++){
                    if(d[i] + e[two.length() - i] < d[k] + e[two.length() - k]) k = i;
                }
                s2l = two.substring(0, k);
                s2r = two.substring(k, two.length());
            } else{
                s2l = two.substring(0, two.length()/2);s2r = two.substring(two.length()/2, two.length());
                int[] d = getLastRow(s2l, one);
                int[] e = getLastRow(s2r, one);
                int k=0;
                for(int i = 1; i <= one.length(); i++){
                    if(d[i] + e[one.length() - i] < d[k] + e[one.length() - k]) k = i;
                }
                s1l = one.substring(0, k);
                s1r = one.substring(k, one.length());
            }
        }
        return getDistanceEdinting(s1l, s2l) + getDistanceEdinting(s1r, s2r);
    }

    int[] getLastRow(String one, String two) {
        int[][] D = new int[2][two.length() + 1];
        D[0][0] = 0;
        for(int i = 0; i <= one.length(); i++) {
            for (int j = 0; j <= two.length(); j++) {
                if (j == 0 && i > 0) D[1][0] = i;
                else if (i == 0) D[0][j] = j;
                else {
                    int R = D[0][j - 1] + (one.charAt(i-1) == two.charAt(j-1) ? 0 : 1);
                    D[1][j] = (D[1][j - 1] + 1) < (D[0][j] + 1) ? Math.min(D[1][j - 1] + 1, R) : Math.min(D[0][j] + 1, R);
                }
            }
            if(i > 0) System.arraycopy(D[1], 0, D[0], 0, two.length());
        }
        return D[1];
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151001/yankova/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}