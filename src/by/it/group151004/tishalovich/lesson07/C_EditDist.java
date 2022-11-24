package by.it.group151004.tishalovich.lesson07;

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
        int[][] matrix = new int[two.length() + 1][one.length() + 1];

        for (int i = 0; i < one.length() + 1; i++) {
            matrix[0][i] = i;
        }

        for (int i = 0; i < two.length() + 1; i++) {
            matrix[i][0] = i;
        }

        for (int i = 1; i < two.length() + 1; i++) {
            for (int j = 1; j < one.length() + 1; j++) {
                int t = one.charAt(j-1) == two.charAt(i-1) ? 0 : 1;
                matrix[i][j] = Math.min(matrix[i - 1][j] + 1,
                        Math.min(matrix[i][j - 1] + 1, matrix[i - 1][j - 1] + t));
            }
        }

        StringBuilder sb = new StringBuilder();
        int i = two.length();
        int j = one.length();
        while(i != 0 || j != 0){
            if(i == 0){
                for (int k = j; k > 0; k--) {
                    sb.append(one.charAt(j - 1));
                    sb.append('+');
                    sb.append(',');
                }
                j = 0;
            } else if(j == 0){
                for (int k = i; k > 0; k--) {
                    sb.append(two.charAt(i - 1));
                    sb.append('-');
                    sb.append(',');
                }
                i = 0;
            } else if(matrix[i][j] == matrix[i][j - 1] + 1){
                sb.append(one.charAt(j - 1));
                j--;
                sb.append('+');
                sb.append(',');
            } else if (matrix[i][j] == matrix[i - 1][j] + 1) {
                sb.append(two.charAt(i - 1));
                i--;
                sb.append('-');
                sb.append(',');
            } else if(matrix[i][j] == matrix[i - 1][j - 1] && one.charAt(j - 1) == two.charAt(i - 1)){
                i--;
                j--;
                sb.append('#');
                sb.append(',');
            } else{
                sb.append(one.charAt(j-1));
                i--;
                j--;
                sb.append('~');
                sb.append(',');
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.reverse();
        sb.append(',');
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        String result = sb.toString();
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