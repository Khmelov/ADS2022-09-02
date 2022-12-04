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
    Итерационно вычислить алгоритм преобразования двух данных непустых строк
    Вывести через запятую редакционное предписание в формате:
     операция("+" вставка, "-" удаление, "~" замена, "#" копирование)
     символ замены или вставки

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    #,#,

    Sample Input 2:
    short
    ports
    Sample Output 2:
    -s,~p,#,#,#,+s,

    Sample Input 3:
    distance
    editing
    Sample Output 2:
    +e,#,#,-s,#,~i,#,-c,~g,


    P.S. В литературе обычно действия редакционных предписаний обозначаются так:
    - D (англ. delete) — удалить,
    + I (англ. insert) — вставить,
    ~ R (replace) — заменить,
    # M (match) — совпадение.
*/


public class C_EditDist {

    String getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        int L1=one.length(),L2=two.length();
        int[][] a=new int[L1+1][L2+1];
        int i,j;
        for(i=0;i<=L1;i++)
            a[i][0]=i;
        for(j=0;j<=L2;j++)
            a[0][j]=j;

        int k;
        for(i=1;i<=L1;i++)
            for(j=1;j<=L2;j++) {
                a[i][j]=a[i-1][j]+1;
                k=a[i][j-1]+1;
                if(k<a[i][j])
                    a[i][j]=k;
                k=a[i-1][j-1]+1;
                if(one.charAt(i-1)==two.charAt(j-1))
                    k--;

                if(k<a[i][j])
                    a[i][j]=k;
            }
        i=L1;j=L2;

        String result = "";

        while((i>0)&&(j>0))
        {

        if(a[i-1][j]+1==a[i][j])
        {i--;result="-"+one.charAt(i)+","+result;}
        else
        if(a[i][j-1]+1==a[i][j])
            {j--;result="+"+two.charAt(j)+","+result;}
        else
        if((a[i-1][j-1]==a[i][j])&&(one.charAt(i-1)==two.charAt(j-1)))
            {i--;j--;result="#"+","+result;}
        else
        if(a[i-1][j-1]+1==a[i][j])
        {i--;j--;result="~"+two.charAt(j)+","+result;}

        }

        while(i>0)
        {i--;result="-"+one.charAt(i)+","+result;}

        while(j>0)
        {j--;result="+"+two.charAt(j)+","+result;}


        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151001/timoshek/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}