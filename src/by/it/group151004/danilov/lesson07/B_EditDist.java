package by.it.group151004.danilov.lesson07;

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

    int[][] buildMatrix(String one, String two){
        int[][] matrix = new int[two.length() + 1][one.length() + 1];
        matrix[0][0] = 0;

        for(int i = 1; i <= one.length(); i++)
            matrix[0][i] = i;

        for(int j = 1; j <= two.length(); j++)
            matrix[j][0] = j;

        for(int i = 1; i <= one.length(); i++)
            for(int j = 1; j <= two.length(); j++) {
                int minValue = (one.charAt(i - 1) == two.charAt(j - 1)) ? 0 : 1;
                minValue += matrix[j - 1][i - 1];

                int minProbe = Math.min(matrix[j][i - 1], matrix[j - 1][i]);
                minProbe++;

                if(minValue > minProbe)
                    minValue = minProbe;

                matrix[j][i] = minValue;
            }

        return matrix;
    }

    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        if(one.equals(two))
            return 0;

        int[][] pathMatrix = buildMatrix(one, two);
        int result = pathMatrix[two.length()][one.length()];
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }



    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        B_EditDist instance = new B_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}