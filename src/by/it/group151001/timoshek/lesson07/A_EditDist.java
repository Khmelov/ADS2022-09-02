package by.it.group151001.timoshek.lesson07;

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

    String s1,s2;

    int RecLeva(int[][] a,int i,int j)
    {
    int k;
    if(a[i][j]!=-1)
        return(a[i][j]);

    a[i][j]=RecLeva(a,i-1,j)+1;
    k=RecLeva(a,i,j-1)+1;
    if(k<a[i][j])
        a[i][j]=k;

    k=RecLeva(a,i-1,j-1)+1;

    if(s1.charAt(i-1)==s2.charAt(j-1))
        k--;

    if(k<a[i][j])
        a[i][j]=k;

    return a[i][j];
    }

    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        int L1=one.length(),L2=two.length();
        s1=one;s2=two;
        int[][] a=new int[L1+1][L2+1];
        int i,j;

        for(i=0;i<=L1;i++)
            for(j=0;j<=L2;j++)
                a[i][j]=-1;

        for(i=0;i<=L1;i++)
            a[i][0]=i;
        for(j=0;j<=L2;j++)
            a[0][j]=j;

        int result = RecLeva(a,L1,L2);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151001/timoshek/lesson07/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }
}
