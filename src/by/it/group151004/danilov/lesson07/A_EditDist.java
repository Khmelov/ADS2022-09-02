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
    Рекурсивно вычислить расстояние редактирования двух данных непустых строк

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

public class A_EditDist {

    private  String origin, result;
    void setField(int[][] matrix, int x, int y) {
        if(x == 0 || y == 0)
            return;

        if(matrix[y - 1][x] == 0)
            setField(matrix, x, y - 1);

        if(matrix[y][x - 1] == 0)
            setField(matrix, x - 1, y);

        int minValue = ( result.charAt(y - 1) == origin.charAt(x - 1) ) ? 0 : 1;
        minValue += matrix[y - 1][x - 1];

        int minProbe = matrix[y - 1][x];
        if(minProbe > matrix[y][x - 1])
            minProbe = matrix[y][x -1];
        minProbe++;

        if(minValue > minProbe)
            minValue = minProbe;
        matrix[y][x] = minValue;

    }
    int[][] buildMatrix(String origin, String result){
        this.origin = origin;
        this.result = result;

        int[][] matrix = new int[result.length() + 1][origin.length() + 1];
        matrix[0][0] = 0;

        for(int i = 1; i <= origin.length(); i++)
            matrix[0][i] = i;

        for(int j = 1; j <= result.length(); j++)
            matrix[j][0] = j;

        setField(matrix, origin.length(), result.length());
        return matrix;
    }

    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        if(one.equals(two))
            return 0;
        int[][] matrix = buildMatrix(one, two);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return matrix[two.length()][one.length()];
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }
}
