package by.it.group151004.korol.lesson7;

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
    int min(int a, int b, int c){
        if (a<=b && a<=c) return a;
        else if (b<=a && b<=c) return b;
        else return c;
    }
    int diff(char a, char b){
        if (a==b)
            return 0;
        else
            return 1;
    }

    int EditDist(int Mat[][], int n, int m, String str1, String str2){
        int i,j;
        for (i = 0 ; i<=n;i++) Mat[i][0] = i;
        {
            for (j = 0; j <= m; j++) Mat[0][j] = j;
            {
                for (i = 1; i <= n; i++)
                {
                    for (j = 1; j <= m; j++)
                    {
                        Mat[i][j] = min(Mat[i-1][j] + 1,
                                        Mat[i][j-1] + 1,
                                        Mat[i-1][j-1] + diff(str1.charAt(i - 1),str2.charAt(j - 1)));
                    }
                }
            }
        }
        return Mat[n][m];
    }
    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int strlen1 = one.length();
        int strlen2 = two.length();
        int result = 0;
        int D[][] = new int[strlen1+1][strlen2+1];
        result = EditDist(D,strlen1,strlen2,one, two);
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