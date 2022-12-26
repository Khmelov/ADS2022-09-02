package by.it.group151001.kononchuk.lesson07;

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


    int editLev(int[][] lev, int i, int j, String first, String second){
        if (lev[i][j] == -1) {
            if (i == 0){
                lev[i][j] = j;
            } else if (j == 0) {
                lev[i][j] = i;
            }
            else{
                int insert = editLev(lev, i, j - 1, first, second) + 1;
                int delete = editLev(lev, i - 1, j, first, second) + 1;
                int match = editLev(lev, i - 1, j - 1, first, second) + (first.charAt(i - 1) == second.charAt(j - 1) ? 0 : 1);

                int min = insert;
                if (delete < min){
                    min = delete;
                }
                if (match < min){
                    min = match;
                }

                lev[i][j] = min;
            }
        }
        return lev[i][j];
    }

    int getDistanceEdinting(String first, String second) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int n = first.length(), m = second.length();
        int[][] lev = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(lev[i], -1);
        }

        int result = editLev(lev, n, m, first, second);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151001/kononchuk/lesson07/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }
}
