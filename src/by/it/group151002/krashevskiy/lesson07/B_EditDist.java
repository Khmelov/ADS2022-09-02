package by.it.group151002.krashevskiy.lesson07;

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


    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int[][] matrix = new int[one.length() + 1][two.length() + 1];
        matrix[0][0] = 0;
        for (int i = 1; i <= two.length(); i++) {
            matrix[0][i] = matrix[0][i - 1] + 1;
        }
        int costDel = 0;
        int costIns = 0;
        int costRep = 0;
        for (int i = 1; i <= one.length(); i++) {
            matrix[i][0] = matrix[i - 1][0] + 1;
            for (int j = 1; j <= two.length(); j++) {
                costDel = matrix[i - 1][j] + 1;
                costIns = matrix[i][j - 1] + 1;
                costRep = matrix[i - 1][j - 1] + (Integer.compare(one.charAt(i-1), two.charAt(j-1)) == 0 ? 0 : 1);
                matrix[i][j] = Math.min(Math.min(costDel, costIns), costRep);
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return matrix[one.length()][two.length()];
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/krashevskiy/lesson07/dataABC.txt");
        B_EditDist instance = new B_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}