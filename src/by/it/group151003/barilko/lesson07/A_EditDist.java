package by.it.group151003.barilko.lesson07;

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
    Осуждаю
    
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


    int editDistRec(int[][] arr, String first, String second, int i, int j)
    {
        if(arr[i][j] == -1)
        {
            if(i == 0)
                arr[i][j] = j;
            else if(j == 0)
                arr[i][j] = i;
            else
            {
                int ins = editDistRec(arr, first, second, i, j - 1) + 1;
                int del = editDistRec(arr, first, second, i - 1, j) + 1;
                int sub = editDistRec(arr, first, second, i - 1, j - 1) + (first.charAt(i - 1) == second.charAt(j - 1) ? 0 : 1);
                arr[i][j] = Integer.min(Integer.min(ins, del), sub);
            }
        }
        return arr[i][j];
    }

    int getDistanceEdinting(String first, String second) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int[][] arr = new int[first.length() + 1][second.length() + 1];
        for(int i = 0; i < first.length() + 1; ++i)
        {
            for(int j = 0; j < second.length() + 1; ++j)
                arr[i][j] = -1;
        }

        editDistRec(arr, first, second, first.length(), second.length());

        int result = arr[first.length()][second.length()];
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
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
