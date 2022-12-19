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

    int getDistanceRecursive(String one,int onel,String two, int twol){
        int n=onel;
        int m = twol;
        if(n==0){
            return m;
        }
        if(m==0){
            return n;
        }
        int cost=0;
        if(one.charAt(n-1)==two.charAt(m-1)){
            cost = 0;
        }
        else {
           cost = 1;
        }
        return getMin(getDistanceRecursive(one,n-1,two,m)+1,getDistanceRecursive(one,n,two,m-1)+1,getDistanceRecursive(one,n-1,two,m-1)+cost);

    }


    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int result=0;
        int l1=one.length();
        int l2=two.length();
        result=getDistanceRecursive(one,l1,two,l2);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151001/saprankov/lesson07/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }
}
