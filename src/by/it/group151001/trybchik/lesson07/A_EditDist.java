package by.it.group151001.trybchik.lesson07;

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

    static int INFINITY  = 1000;
    int diff(char a,char b)
    {
        if (a==b)
            return 0;
        else return 1;
    }
    int min (int a,int b,int c)
    {
        if(a<b && a<c)
            return a;
        else if (b<a && b<c)
            return b;
        else return c;
    }
    int EditDistance(int[][] D,int i ,int j,String one,String two)
    {
        if (D[i][j] == INFINITY) {
            if (i == 0)
                D[i][j] = j;
            else if (j == 0)
                D[i][j] = i;
            else {
                int ins = EditDistance(D, i, j - 1, one, two) + 1;
                int del = EditDistance(D, i - 1, j, one, two) + 1;
                int sub = EditDistance(D, i - 1, j - 1, one, two) + diff(one.charAt(i-1), two.charAt(j-1));
                D[i][j] =min(ins,del,sub);
            }
        }
        return D[i][j];
    }
    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int len1 = one.length();
        int len2 = two.length();
        int[][] D = new int[len1+1][len2+1];
        for(int i = 0;i<=len1;i++)
        {
            for(int j = 0;j<=len2;j++)
            {
                D[i][j] = INFINITY;
            }
        }

        int result = EditDistance(D,len1,len2,one,two);
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
