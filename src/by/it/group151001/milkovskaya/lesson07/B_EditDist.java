package by.it.group151001.milkovskaya.lesson07;

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


    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int m = one.length();
        int n = two.length();

        // matrix to store differences
        int[][] matr = new int[m+1][n+1];

        for(int i = 0;i <= m; i++)
        {
            matr[i][0] = i;
        }

        for(int j = 1;j <= n; j++)
        {
            matr[0][j] = j;
        }

        for(int j=1;j<=n;j++)
        {
            for(int i=1;i<=m;i++)
            {
                if(one.charAt(i-1) == two.charAt(j-1))
                {
                    matr[i][j] = matr[i-1][j-1];
                }
                else
                {
                    matr[i][j] = Math.min(
                            matr[i-1][j]+1,
                            Math.min(
                                    matr[i][j-1]+1,
                                    matr[i-1][j-1]+1
                            )
                    );
                }
            }
        }

        return matr[m][n];
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