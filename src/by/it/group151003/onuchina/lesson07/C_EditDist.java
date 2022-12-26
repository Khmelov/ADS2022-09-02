package by.it.group151003.onuchina.lesson07;

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
        final int raw_quant = one.length() + 1;
        final int col_quant = two.length() + 1;
        int[][] dist_table = new int[raw_quant][col_quant];

        //Fill 1-st raw and 1-st column with string indexes
        for (int i = 0; i < raw_quant; i++)
            dist_table[i][0] = i;
        for (int j = 0; j < col_quant; j++)
            dist_table[0][j] = j;

        for (int i = 1; i < raw_quant; i++)
            for (int j = 1; j < col_quant; j++) {
                int insert = dist_table[i][j - 1] + 1; //If insert element - take left one form the table + 1
                int delete = dist_table[i - 1][j] + 1; //If delete element - take one above from the table + 1
                int change = dist_table[i - 1][j - 1] + ((one.charAt(i-1) == two.charAt(j-1)) ? 0 : 1); //If change element - take one from diagonal + 1, if it's not the same element
                dist_table[i][j] = Math.min(Math.min(insert, delete), change); //Take minimum of values above
            }

        //Make string with sequence of operations and elements in reverse order
        StringBuilder reverse_result = new StringBuilder();
        int i = raw_quant - 1;
        int j = col_quant - 1;
        while (i != 0 || j != 0){
            if (dist_table[i][j] == dist_table[i][j - 1] + 1) { //If equals to left element - it's insert operation
                reverse_result.append("+").append(two.charAt(j - 1)).append(",");
                j--;
            }
            else if (dist_table[i][j] == dist_table[i - 1][j] + 1) { //If equals to element above - it's delete operation
                reverse_result.append("-").append(one.charAt(i - 1)).append(",");
                i--;
            }
            else {
                if (dist_table[i][j] == dist_table[i - 1][j - 1]) //If equals to diagonal and didn't make sequence longer - it's copy operation
                    reverse_result.append("#,");
                else //If equals to diagonal and made sequence longer - it's replace operation
                    reverse_result.append("~").append(two.charAt(j - 1)).append(",");
                i--;
                j--;
            }
        }

        //Reverse string with operations and symbols
        String result = "";
        int count = reverse_result.length() - 2;
        while (count >= 0) {
            int length = 0;
            String one_operation = "";
            while (count >= 0 && reverse_result.charAt(count) != ',') {
                count--;
                length++;
            }
            one_operation += reverse_result.substring(count + 1, count + 1 + length);
            result += one_operation + ",";
            count--;
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