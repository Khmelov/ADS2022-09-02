package by.it.group151002.redkovskiy.lesson07;

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
    public static int min(int... numbers) {
        return Arrays.stream(numbers).min().orElse(Integer.MAX_VALUE);
    }

    int getDistanceEditing(String first, String second) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        if (first.isEmpty()) {
            return second.length();
        }
        if (second.isEmpty()) {
            return first.length();
        }
        int substitution = getDistanceEditing(first.substring(1), second.substring(1)) + (first.charAt(0) == second.charAt(0) ? 0 : 1);
        int insertion = getDistanceEditing(first, second.substring(1)) + 1;
        int deletion = getDistanceEditing(first.substring(1), second) + 1;
        return min(substitution, insertion, deletion);
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEditing(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEditing(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEditing(scanner.nextLine(), scanner.nextLine()));
    }
}
