package by.it.group151003.stoyanov.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
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

    private String getEditorialInstruction(String a, String b) {
        int distanceMatrix[][] = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i <= a.length(); distanceMatrix[i][0] = i, i++);
        for (int i = 0; i <= b.length(); distanceMatrix[0][i] = i, i++);

        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                distanceMatrix[i][j] = min(
                        distanceMatrix[i - 1][j - 1] + getCost(a.charAt(i - 1), b.charAt(j - 1)),
                        distanceMatrix[i - 1][j] + 1,
                        distanceMatrix[i][j - 1] + 1);
            }
        }

        StringBuilder builder = new StringBuilder();
        int i = a.length(), j = b.length();
        while (i > 0 && j > 0) {
            builder.insert(0, ",");
            if (a.charAt(i - 1) == b.charAt(j - 1)) {
                i--;
                j--;
                builder.insert(0, "#");
                continue;
            }

            int min = min(distanceMatrix[i - 1][j - 1], distanceMatrix[i - 1][j], distanceMatrix[i][j - 1]);
            if (distanceMatrix[i - 1][j - 1] == min) {
                builder.insert(0, "~" + b.charAt(j - 1));
                i--;
                j--;
            }
            else if (distanceMatrix[i][j - 1] == min) {
                builder.insert(0, "+" + b.charAt(j - 1));
                j--;
            }
            else if (distanceMatrix[i - 1][j] == min) {
                builder.insert(0, "-" + a.charAt(i - 1));
                i--;
            }
        }

        while (i-- > 0) builder.insert(0, "-" + a.charAt(i) + ",");
        while (j-- > 0) builder.insert(0, "+" + b.charAt(j) + ",");
        return builder.toString();
    }

    private int min(int... nums) {
        return Arrays.stream(nums).min().orElseThrow(() -> new RuntimeException("Zero parameters given"));
    }

    private int getCost(char a, char b) {
        return a == b ? 0 : 1;
    }

    String getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!


        String result = getEditorialInstruction(one, two);
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