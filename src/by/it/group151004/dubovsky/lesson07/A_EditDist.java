package by.it.group151004.dubovsky.lesson07;

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


    private int cmp(char i, char j) {
        return i == j ? 0 : 1;
    }

    private int calculate(int [][] arr, String one, String two, int i, int j) {
        if(arr[i][j] == -1)
        {
            if(i == 0) {
                arr[i][j] = j;
            } else if(j == 0) {
                arr[i][j] = i;
            } else {
                int put = calculate(arr, one, two,i, j - 1) + 1;
                int pop = calculate(arr, one, two, i - 1, j) + 1;
                int tmp = calculate(arr, one, two, i - 1, j - 1) + cmp(one.charAt(i - 1), two.charAt(j - 1));

                arr[i][j] = Math.min(Math.min(put, pop), tmp);
            }

        }
        return arr[i][j];
    }

    int getDistanceEdinting(String one, String two) {
        int i = one.length() + 1;
        int j = two.length() + 1;
        int[][] arr = new int[i][j];

        for (int[] st: arr) {
            Arrays.fill(st, -1);
        }

        calculate(arr, one, two, i - 1, j - 1);

        return arr[i - 1][j - 1];
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
