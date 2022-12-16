package by.it.group151001.saprankov.lesson07;

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
    int getMin(int first,int second,int third){
        int min=0;
        if(first<=second&&first<=third){
            min=first;
        }
        if(second<=first&&second<=third){
            min=second;
        }
        if(third<=first&&third<=second){
            min=third;
        }
        return min;
    }

    String getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int n = one.length();
        int m= two.length();
        int[][] d= new int [n+1][m+1];
        int add,rep,del;
        for(int i=0;i<=n;i++){
            for(int j=0;j<=m;j++){
                if(i==0){
                    d[i][j]=j;
                }
                else if(j==0){
                    d[i][j]=i;
                }
                else {
                    add=d[i][j-1]+1;
                    del=d[i-1][j]+1;
                    if(one.charAt(i-1)==two.charAt(j-1)){
                        rep=d[i-1][j-1];
                    }
                    else{
                        rep=d[i-1][j-1]+1;
                    }
                    d[i][j]=getMin(add,rep,del);
                }

            }
        }
        String result = "";
        int len = d[n][m];
        String oper="";
        int i=n;
        int j=m;
        while(i!=0&&j!=0){
            if(one.charAt(i-1)==two.charAt(j-1)&&(d[i][j]==d[i-1][j-1])){
                oper = "#,";
                result=oper.concat(result);
                i--;
                j--;

            }
            else if(one.charAt(i-1)!=two.charAt(j-1)&&(d[i][j]==d[i-1][j-1]+1)){
                oper = "~"+two.charAt(j-1)+",";
                result=oper.concat(result);
                i--;
                j--;
            }

            else if(d[i][j]-1==d[i][j-1]) {
                oper = "+" + two.charAt(j - 1) + ",";
                result=oper.concat(result);
                j--;
            }
            else if(d[i][j]-1==d[i-1][j]) {
                oper = "-" + one.charAt(i - 1) + ",";
                result=oper.concat(result);
                i--;
            }


        }
        if(i==0&&j>0){
            oper = "+" + two.charAt(j - 1) + ",";
            result = oper.concat(result);

        }
        if(j==0&&i>0){
            oper = "-" + one.charAt(i - 1) + ",";
            result = oper.concat(result);

        }






        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}