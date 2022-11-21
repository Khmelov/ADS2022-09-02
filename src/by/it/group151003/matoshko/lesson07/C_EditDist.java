package by.it.group151003.matoshko.lesson07;

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
        // Строк и столбцов в матрице должно быть на 1 больше чем длина слова
        // Строки - слово А. Столбцы - слово В
        // Преобразуем слово А в слово В
        int N=one.length()+1;
        int M=two.length()+1;
        int[][] table = new int[N][M];

        // Первый столбец(по строкам) - если строка А - i символов, а В - 0, значит из А удалить нужно i символов
        for (int i = 0; i < N; i++)
            table[i][0] = i;
        // Первая строка(по столбцам) - если строка А - 0 символов, а В - i, значит к А добавить j символов
        for (int j = 0; j < M; j++)
            table[0][j] = j;

        for(int i=1;i<N;i++)
        {
            for(int j=1;j<M;j++)
            {
                // Добавление буквы - по строке
                int ins = table[i][j - 1] + 1;
                // Удаление буквы - по столбцу
                int del = table[i - 1][j] + 1;
                // Замена буквы - по диагонали
                int sub = table[i - 1][j - 1] + ((one.charAt(i-1) == two.charAt(j-1)) ? 0 : 1);
                table[i][j] = Math.min(Math.min(ins, del), sub);
            }
        }

        String result = "";
        int i=N-1;
        int j=M-1;
        while(i!=0 || j!=0)
        {
            if(j>0 && table[i][j]==table[i][j-1]+1) {
                result = result + '+' + two.charAt(j - 1)+',';
                j=j-1;
            }
            else
            if(i>0&& table[i][j]==table[i-1][j]+1) {
                result = result + '-' + one.charAt(i - 1)+',';
                i = i - 1;
            } else {
                if ((i > 0 && j > 0 && table[i][j] == table[i - 1][j - 1] + 1))  //j>0 && i>0 && table[i][j]==table[i-1][j-1]+1 не работает?!?!?!?!
                {
                    result = result + '~' + two.charAt(j - 1) + ',';
                } else
                    result = result + "#,";

                i = i - 1;
                j = j - 1;
            }

        }

    /*    String str="";
          for(int k=result.length()-1;k>=0;k--)
          str=str+result.charAt(k);

     */
        String str = "";
        int count = result.length() - 2;
        while (count >= 0) {
            int length = 0;
            String oper_plus_elem = "";
            while (count >= 0 && result.charAt(count) != ',') {
                --count;
                ++length;
            }
            oper_plus_elem += result.substring(count + 1, count + 1 + length);
            str += oper_plus_elem + ",";
            --count;
        }


        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return str;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151003/matoshko/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}