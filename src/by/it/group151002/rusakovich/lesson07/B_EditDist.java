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

    void arr_cp(int[]f, int[]s){
        for(int i = 0; i < f.length; ++i)
            s[i] = f[i];
    }


    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int min_size = 0, max_size = 0;
        if(one.isEmpty())
            return two.length();
        if(two.isEmpty())
            return one.length();


        min_size = one.length();
        max_size = two.length();

        int[] m_line = new int [min_size + 1];
        int[] m_pline = new int[min_size + 1];
        for(int i = 0; i <= min_size; i++)
            m_line[i] = i;

        for(int i = 1; i <= max_size; ++i){
            arr_cp(m_line, m_pline);

            m_line[0] = i;
            for(int j = 1; j <= min_size; ++j){
                int exchange_cost = one.charAt(j-1) == two.charAt(i-1) ? 0 : 1;
                m_line[j] = get_min(m_pline[j] + 1, m_pline[j-1] + exchange_cost, m_line[j-1] + 1);
            }
        }
        return m_line[min_size];
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
    }

    int get_min(int f, int s, int t){
        int first_low = Math.min(f, s);
        return Math.min(first_low, t);
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