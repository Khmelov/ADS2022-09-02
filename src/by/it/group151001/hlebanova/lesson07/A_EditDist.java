package by.it.group151001.hlebanova.lesson07;

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
    int Min(int a, int b, int c){
        if(b<a && b<c) return b;
        else if(a<c && a<b) return a;
        else return c;
    }
    int EditDist(int i, String A, int j, String B, int [][] D){
        if (D[i][j] == -1){
            if(i == 0) D[i][j] = j;
            else if(j == 0) D[i][j] = i;
            else{
                int ins = EditDist(i,A,j-1,B,D)+1;
                int del = EditDist(i-1,A,j,B,D)+1;
                int c = 0 ;
                if(A.charAt(i-1) !=B.charAt(j-1)) c = 1;
                else c = 0;
                int sub = EditDist(i-1,A,j-1,B,D)+c;
                D[i][j] = Min(ins,del,sub);
            }
        }
        return D[i][j];
    }
    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int n = one.length();
        int m = two.length();
        int D [][] = new int [n+1][m+1];
        for(int i=0; i<=n; i++){
            for(int j=0; j<=m; j++){
                D[i][j] = -1;
            }
        }

        int result = EditDist(one.length(),one,two.length(),two,D);
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
