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
    int EditDisTTD(int i, String X, int j,  String Y, int [][] mas )
    {
        //double inf = Double.NEGATIVE_INFINITY;
        if (mas[i][j]==-1)
        {
            if(i==0)
                mas[i][j] = j;
                else
                    if (j == 0)
                        mas[i][j] = i;
                     else{
                            int Z;
                            int insert = EditDisTTD(i, X, j - 1, Y, mas) + 1;
                            int delete = EditDisTTD(i - 1, X, j, Y, mas) + 1;
                            if (X.charAt(i - 1) != Y.charAt(j - 1))
                                Z = 1;
                            else
                                Z = 0;
                            int substract = EditDisTTD(i - 1, X, j - 1, Y, mas) + Z;
                            mas[i][j] = minimal(insert, delete, substract);
                          }
        }
        return mas[i][j];
    }
    int getDistanceEdinting(String one, String two){
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int k = one.length();
        int p = two.length();
        //double inf = Double.NEGATIVE_INFINITY;
        int [][] mas= new int [k+1][p+1];
        for(int i=0; i <= k; i++){
            for(int j=0; j<=p; j++){
                mas[i][j] = -1;
            }
        }

        int result = EditDisTTD(one.length(),one,two.length(),two,mas);


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
