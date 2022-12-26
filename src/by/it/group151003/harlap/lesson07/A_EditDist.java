package by.it.group151003.harlap.lesson07;

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


    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int N = one.length() + 1;
        int M = two.length() + 1;
        int[][] D = new int[N][M];
        for (int i = 0; i < N; i++)
            Arrays.fill(D[i], -1);
        Dist(D, one, two, N - 1, M - 1);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return D[N - 1][M - 1];
    }

    private int Dist(int[][] D, String one, String two, int i, int j) {
        if (D[i][j] == -1) {
            if (i == 0) D[i][j] = j;
            else if (j == 0) D[i][j] = i;
            else {
                int inscost = Dist(D, one, two, i, j - 1) + 1;
                int delcost = Dist(D, one, two, i - 1, j) + 1;
                int subscost = Dist(D, one, two, i - 1, j - 1) + ((one.charAt(i - 1) == two.charAt(j - 1)) ? 0 : 1);
                D[i][j] = Math.min(Math.min(inscost, delcost), subscost);
            }
        }
        return D[i][j];
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
    }
}
