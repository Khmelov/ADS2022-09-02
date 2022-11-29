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

    private int getDistance(String a, String b) {
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

       return distanceMatrix[a.length()][b.length()];
    }

    private int min(int... nums) {
        return Arrays.stream(nums).min().orElseThrow(() -> new RuntimeException("Zero parameters given"));
    }

    private int getCost(char a, char b) {
        return a == b ? 0 : 1;
    }


    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!



        int result = getDistance(one, two);
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