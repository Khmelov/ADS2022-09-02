package by.it.group151003.savitski.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Arrays;

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
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!
        final int raw_quant = one.length() + 1;
        final int col_quant = two.length() + 1;
        int[][] dist_table = new int[raw_quant][col_quant];

        //Fill dist_table with (-1)
        for (int i = 0; i < raw_quant; i++)
            Arrays.fill(dist_table[i], -1);

        edit_dist_table(dist_table, one, two, raw_quant - 1, col_quant - 1);

        return dist_table[raw_quant - 1][col_quant - 1];
    }

    private int edit_dist_table(int[][] distancesTable, String one, String two, int i, int j){
        if (distancesTable[i][j] == -1) //If cell have to be filled
            if (i == 0) // If it's 1-st raw, just fill it with string indexes[0..two.length()]
                distancesTable[i][j] = j;
            else if (j == 0) // If it's 1-st raw, just fill it with string indexes[0..one.length()]
                distancesTable[i][j] = i;
            else {
                int insert = edit_dist_table(distancesTable, one, two, i, j - 1) + 1; //If insert element - take left one form the table + 1
                int delete = edit_dist_table(distancesTable, one, two, i - 1, j) + 1; //If delete element - take one above from the table + 1
                int change = edit_dist_table(distancesTable, one, two, i - 1, j - 1) + ((one.charAt(i-1) == two.charAt(j-1)) ? 0 : 1); //If change element - take one from diagonal + 1, if it's not the same element
                distancesTable[i][j] = Math.min(Math.min(insert, delete), change); //Take minimum of values above
            }
        return distancesTable[i][j];
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
