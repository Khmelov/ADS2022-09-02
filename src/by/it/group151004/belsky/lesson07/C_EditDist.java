package by.it.group151004.belsky.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

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
        String buf = one;
        one = two;
        two = buf;

        int resultCount = 0;
        int[][] arr = new int[one.length()+1][two.length()+1];
        for (int[] subarr : arr) {
            Arrays.fill(subarr, -1);
        }
        for (int i = 0;i < one.length()+1;i++) {
            arr[i][0] = i;
        }
        for (int j = 0;j < two.length()+1;j++) {
            arr[0][j] = j;
        }

        for (int i = 1;i < one.length()+1;i++) {
            for (int j = 1;j < two.length()+1;j++) {
                int var1 = arr[i][j-1] + 1;
                int var2 = arr[i-1][j] + 1;
                int var3 = arr[i-1][j-1];
                if (one.charAt(i-1) != two.charAt(j-1)) var3+=1;
                arr[i][j] = Math.min(var1, Math.min(var2, var3));
            }
        }
        resultCount = arr[arr.length-1][arr[0].length-1];
//        return result;
        int i = one.length();
        int j = two.length();

        Stack<String> stack = new Stack<>();

        while (i != 0 && j != 0) {
            if (arr[i-1][j] == arr[i][j]-1) {
                stack.push(",");
                stack.push(String.valueOf(one.charAt(i-1)));
                stack.push("+");
                i-=1;
            } else if (arr[i][j-1] == arr[i][j]-1) {
                stack.push(",");
                stack.push(String.valueOf(two.charAt(j-1)));
                stack.push("-");
                j-=1;
            } else if (arr[i-1][j-1] == arr[i][j]-1) {
                stack.push(",");
                stack.push(String.valueOf(one.charAt(i-1)));
                stack.push("~");
                i-=1;
                j-=1;
            } else if (arr[i-1][j-1] == arr[i][j]) {
                stack.push(",");
                stack.push("#");
                j-=1;
                i-=1;
            }
        }

        StringBuilder result = new StringBuilder("");
        while (!stack.empty()) {
            result.append(stack.pop());
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result.toString();
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