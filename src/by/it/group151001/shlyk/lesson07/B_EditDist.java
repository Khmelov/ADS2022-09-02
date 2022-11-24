package by.it.group151001.shlyk.lesson07;

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


    int[][] buildMatrix(String origin, String result){
        int[][] matrix = new int[result.length() + 1][origin.length() + 1];
        matrix[0][0] = 0;
        for(int i = 1; i <= origin.length(); i++)
            matrix[0][i] = i;
        for(int j = 1; j <= result.length(); j++)
            matrix[j][0] = j;
        for(int i = 1; i <= origin.length(); i++)
            for(int j = 1; j <= result.length(); j++) {
                int minValue = (origin.charAt(i - 1) == result.charAt(j - 1)) ? 0 : 1;
                minValue += matrix[j - 1][i - 1];
                int minProbe = Math.min(matrix[j][i - 1], matrix[j - 1][i]);
                minProbe++;
                if(minValue > minProbe)
                    minValue = minProbe;
                matrix[j][i] = minValue;
            }
        return matrix;
    }

    int getDistanceEditing(String origin, String result) {
        if(origin.equals(result))
            return 0;
        int distanceResult;
        int[][] pathMatrix = buildMatrix(origin, result);
        distanceResult = pathMatrix[result.length()][origin.length()];
        return distanceResult;
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