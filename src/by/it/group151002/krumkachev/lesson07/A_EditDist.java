package by.it.group151002.krumkachev.lesson07;

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

    int min(int a, int b, int c) {
        return (a > b) ? Math.min(b, c) : Math.min(a, c);
    }

    int getDistanceEditing(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        if (one.length() == 0)
            return two.length();
        if (two.length() == 0)
            return one.length();
        if (one.charAt(0) == two.charAt(0))
            return getDistanceEditing(one.substring(1), two.substring(1));

        return 1 + min (
                getDistanceEditing(one, two.substring(1)),
                getDistanceEditing(one.substring(1), two),
                getDistanceEditing(one.substring(1), two.substring(1))
        );

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEditing(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEditing(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEditing(scanner.nextLine(),scanner.nextLine()));
    }
}
