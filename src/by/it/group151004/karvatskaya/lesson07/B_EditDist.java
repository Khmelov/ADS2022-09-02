package by.it.group151004.karvatskaya.lesson07;

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

    int minimal(int x, int y, int z)
    {
        if(x<y && x<z)
            return x;
        else
        if(y<x && y<z)
            return y;
        else
            return z;
    }
    int different(char uno, char dos)
    {
        if (uno == dos)
        {
            return 0;
        }
        else
            return 1;
    }
    int EditDistBU(int k, String X, int p,String Y)
    {   int Z;
        int [][] mas= new int [k+1][p+1];
        for(int i=0; i<=k; i++)
        {
            mas[i][0] = i;
        }
        for(int j = 1; j<=p; j++)
        {
            mas[0][j] = j;
        }
        for(int i = 1; i<=k; i++) {
            for (int j = 1; j <= p; j++) {

                Z = different(X.charAt(i - 1), Y.charAt(j - 1));
                mas[i][j] = minimal(mas[i - 1][j] + 1, mas[i][j - 1] + 1, mas[i - 1][j - 1] + Z);
            }
        }
        return mas[k][p];
    }
    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        int result = EditDistBU(one.length(),one,two.length(),two);
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