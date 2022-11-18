package by.it.group151001.beryozkin.lesson07;

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
        int n = one.length() + 1;
        int m = two.length() + 1;
        int[][] d = new int[n][m];
        for (int i=0; i<n; i++) d[i][0] = i;
        for (int j=0; j<m; j++) d[0][j] = j;
        //filling table
        int c;
        for (int i=1; i<n; i++){
            for (int j=1; j<m; j++){
                c = compare(one.charAt(i-1), two.charAt(j-1));
                d[i][j] = min(d[i-1][j]+1, d[i][j-1]+1, d[i-1][j-1]+c);
            }
        }

     String result = "";
     int i=n-1;
     int j = m-1;
     boolean f;
     do{
         f = false;
         if (i>0 && j>0){
             c = compare(one.charAt(i-1), two.charAt(j-1));
             if (d[i][j] == d[i-1][j-1] + c){
                 f = true;
                 if (c == 0)  result+="#,";
                 else  result = result + "~" + two.charAt(j-1) + ",";
                 i--; j--;
             }
         }
         if (i>0 && !f){
             if (d[i][j] == d[i-1][j] + 1){
                 f = true;
                 result = result + "-" + one.charAt(i-1) + ",";
                 i--;
             }
         }
      if (j>0 && !f) {
          if (d[i][j] == d[i][j-1] + 1){
              result = result + "+" + two.charAt(j-1) + ",";
              j--;
          }
      }

     }while(!(i==0 && j==0));

     String res = "";
     int k = result.length()-2;
     while(k>=0){
         if ((result.charAt(k) >= 'a' && result.charAt(k) <='z') || (result.charAt(k) >= 'A' && result.charAt(k) <='Z')){
             res = res + result.charAt(k-1) + result.charAt(k) + ",";
             k--;
         }
         else if (result.charAt(k) == '#') res+="#,";
         k--;
     }
         //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return res;
    }

    int compare(char a, char b){
        if (a==b) return 0;
        return 1;
    }

    int min(int a, int b, int c){
        int res = a;
        if (b<res) res = b;
        if (c<res) res = c;
        return res;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151001/beryozkin/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}