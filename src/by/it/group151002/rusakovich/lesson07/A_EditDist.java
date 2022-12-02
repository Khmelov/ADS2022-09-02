package by.it.group151002.rusakovich.lesson07;

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


    void arr_cp(int[]f, int[]s){
        for(int i = 0; i < f.length; ++i)
            s[i] = f[i];
    }

    void aux(int[] prev, int[] curr, int i, String one, String two)
    {
        if(i > two.length())
            return;
        arr_cp(curr, prev);
        curr[0] = i;
        for(int j = 1; j <= one.length(); ++j) {
            int exchange_cost = one.charAt(j - 1) == two.charAt(i - 1) ? 0 : 1;
            curr[j] = get_min(prev[j] + 1, prev[j - 1] + exchange_cost, curr[j - 1] + 1);
        }
        aux(prev, curr, i + 1, one, two);
    }

    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int min_size = 0, max_size = 0;
        if(one.isEmpty())
            return two.length();
        if(two.isEmpty())
            return one.length();
        if(one.length() < two.length())
            getDistanceEdinting(two, one);

        min_size = one.length();
        max_size = two.length();

        int[] m_line = new int [min_size + 1];
        int[] m_pline = new int[min_size + 1];
        for(int i = 0; i <= min_size; i++)
            m_line[i] = i;

        aux(m_pline, m_line, 1, one, two);
        return m_line[min_size];
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
    }

    int get_min(int f, int s, int t){
        int first_low = Math.min(f, s);
        return Math.min(first_low, t);
    }



    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151002/rusakovich/lesson07/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }
}
