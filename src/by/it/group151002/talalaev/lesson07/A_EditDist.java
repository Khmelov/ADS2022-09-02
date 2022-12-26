package by.it.group151002.talalaev.lesson07;

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

    int min(int a, int b, int c){
        return Integer.min(a, Integer.min(b, c));
    }

    int dist(String one, String two, int i, int j){
        if (i == 0)
            return j;
        if (j == 0)
            return i;
        int cost = (one.charAt(i - 1) == two.charAt(j - 1)) ? 0: 1;
        return  min(dist(one, two, i - 1, j) + 1,
                dist(one, two, i, j - 1) + 1,
                dist(one, two, i - 1, j - 1) + cost);
    }

    int getDistanceEdinting(String one, String two) {
        int[][]words = new int[one.length()][two.length()];
        for(int i = 0; i < one.length(); i++)
            Arrays.fill(words[i], Integer.MAX_VALUE);
        return dist(one, two, one.length() ,two.length());
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        A_EditDist ob = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(ob.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(ob.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(ob.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }
}
