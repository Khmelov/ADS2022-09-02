package by.it.group151002.krumkachev.lesson07;

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
    Итерационно вычислить расстояние редактирования двух данных непустых строк

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    0

    Sample Input 2:
    short
    ports
    Sample Output 2:
    3

    Sample Input 3:
    distance
    editing
    Sample Output 3:
    5

*/

public class B_EditDist {

    int min(int a, int b, int c) {
        return (a > b) ? Math.min(b, c) : Math.min(a, c);
    }

    int getDistanceEditing(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        if (one.length() == 0)
            return two.length();
        if (two.length() == 0)
            return one.length();
        if (one.equals(two))
            return 0;

        int[][] matrix = new int[2][two.length() + 1];
        for (int i = 0; i < matrix[0].length; i++)
            matrix[0][i] = i;
        for (int i = 0; i < one.length(); i++) {
            matrix[1][0] = matrix[0][0] + 1;
            for (int j = 1; j <= two.length(); j++) {
                int replacementCost = one.charAt(i) == two.charAt(j - 1) ? 0 : 1;
                matrix[1][j] = min(matrix[1][j - 1] + 1, matrix[0][j] + 1, matrix[0][j - 1] + replacementCost);
            }
            System.arraycopy(matrix[1], 0, matrix[0], 0, two.length() + 1);
        }
        int result = matrix[1][two.length()];
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }



    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        B_EditDist instance = new B_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEditing(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEditing(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEditing(scanner.nextLine(),scanner.nextLine()));
    }

}