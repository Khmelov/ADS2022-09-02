package by.it.group151002.rusakovich.lesson07;

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

    int get_direction(int a, int b, int c){
        if(get_min(a, b, c) == a) {
            if (a == c)
                return 3;
            return 1;
        }
        if(get_min(a, b, c) == b) {
            if (b == c)
                return 3;
            return 2;
        }
        return 3;
    }

    int make_choice(int i, int j, int[][] d_matrix, int cost){
        int choice;
        if(i == 0)
            choice = 2;
        else
        if(j == 0)
            choice = 1;
        else
            choice = get_direction(d_matrix[i - 1][j] + 1, d_matrix[i][j - 1] + 1, d_matrix[i - 1][j - 1] + cost);
        return choice;
    }
    String get_directive(int[][] d_matrix, String one, String two)
    {
        int i = d_matrix.length - 1;
        int j = d_matrix[0].length - 1;
        StringBuilder result = new StringBuilder();
        while (i > 0 || j > 0){
            result.append(",");
            int cost = 1;
            if(i > 0 && j > 0)
                cost = one.charAt(i-1) == two.charAt(j-1) ? 0 : 1;

            switch (make_choice(i, j, d_matrix, cost)) {
                case 1 -> {
                    //delete
                    result.append(one.charAt(i - 1)).append("-");
                    i--;
                }
                case 2 -> {
                    //insert
                    result.append(two.charAt(j - 1)).append("+");
                    j--;
                }
                case 3 -> {
                    //exchange
                    if (cost == 1)
                        result.append(two.charAt(j - 1)).append("~");
                    else
                        result.append("#");
                    i--;
                    j--;
                }
            }
        }
        return result.reverse().toString();
    }

    String getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int min_size = two.length();
        int max_size = one.length();
        int[][] distance_matrix = new int[max_size + 1][min_size+1];
        for(int i = 0; i <= max_size; ++i)
            distance_matrix[i][0] = i;
        for(int i = 0; i <= min_size; ++i)
            distance_matrix[0][i] = i;

        for(int i = 1; i <= max_size; ++i){
            for(int j = 1; j <= min_size; ++j){
                int cost = one.charAt(i-1) == two.charAt(j-1) ? 0 : 1;
                distance_matrix[i][j] = get_min(distance_matrix[i][j-1] + 1, distance_matrix[i-1][j]+1,
                        distance_matrix[i-1][j-1] + cost);
            }
        }

        return get_directive(distance_matrix, one, two);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
    }
    int get_min(int f, int s, int t){
        int first_low = Math.min(f, s);
        return Math.min(first_low, t);
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/rusakovich/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}