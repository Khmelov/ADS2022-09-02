package lesson07;

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

    int levenshtainDistanceRec(String fst, String scnd) {//через массивы
        int[] Mx_scnd = new int[scnd.length() + 1];
        int[] Mx = new int[scnd.length() + 1];

        for (int j = 0; j <= scnd.length(); j++)
            Mx[j] = j;

        for (int i = 1; i <= fst.length(); i++) {
            System.arraycopy(Mx, 0, Mx_scnd, 0, Mx_scnd.length);

            Mx[0] = i; // (j == 0)
            for (int j = 1; j <= scnd.length(); j++) {
                int cost = (fst.charAt(i - 1) != scnd.charAt(j - 1)) ? 1 : 0;
                Mx[j] = minAmongThree((Mx_scnd[j] + 1), (Mx[j - 1] + 1), (Mx_scnd[j - 1] + cost));
            }
        }

        return Mx[Mx.length - 1];
    }

    private static int minAmongThree(int n1, int n2, int n3) {
        /*int res;
        if (n1 < n2){
            if (n1 < n3)
                res = n1;
            else
                res = n3;
        }
        else{
            if (n2 < n3)
                res = n2;
            else
                res = n3;
        }*/

        return Math.min(Math.min(n1,n2), n3);
    }


    int getDistanceEdinting(String one, String two) {
        return levenshtainDistanceRec(one,two);
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