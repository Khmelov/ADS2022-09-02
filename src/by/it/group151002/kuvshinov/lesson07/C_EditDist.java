package by.it.group151002.kuvshinov.lesson07;

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

    int min(int a, int b, int c){
        return Math.min(a, Math.min(b, c));
    }

    String getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int a = one.length();
        int b = two.length();
        int cost;
        int[][] matrix = new int[a + 1][b + 1];

        for (int i = 1; i <= a; i++)
            matrix[i][0] = i;
        for (int i = 1; i <= b; i++)
            matrix[0][i] = i;


        for (int i = 1; i <= a; i++)
            for (int j = 1; j <= b; j++) {
                if (one.charAt(i - 1) == two.charAt(j - 1)){
                    cost = 0;
                } else cost = 1;
                matrix[i][j] = min(matrix[i - 1][j - 1] + cost,matrix[i][j - 1] + 1,matrix[i - 1][j] + 1);
            }

        String result = ",";

        int m = a;
        int n = b;
        while (m != 0 && n != 0) {
            if (matrix[m][n] == matrix[m][n - 1] + 1) {
                result = "+" + two.charAt(--n) + result;
            }
            else if (matrix[m][n] == matrix[m - 1][n] + 1) {
                result = "-" + one.charAt(--m) + result;
            }
            else if (matrix[m][n] == matrix[m - 1][n - 1] + 1) {
                result = "~" + two.charAt(--n) + result;
                --m;
            }
            else if (matrix[m][n] == matrix[m - 1][n - 1]) {
                result = "#" + result;
                --m;
                --n;
            }
            result = "," + result;
        }
        while (n > 0)
            result = ",+" + two.charAt(--n) + result;
        while (m > 0)
            result = ",-" + one.charAt(--m) + result;
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result.substring(1);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/kuvshinov/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}
