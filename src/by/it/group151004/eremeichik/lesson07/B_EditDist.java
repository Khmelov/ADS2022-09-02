package by.it.group151004.eremeichik.lesson07;

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


    int getDistanceEdinting(String one, String two) {
        int[][] subproblems = new int[one.length()+1][two.length()+1];
        for(int i = 0;i<subproblems.length;i++){
            for(int j = 0;j<subproblems[i].length;j++){
                if(i==0){
                    subproblems[i][j] = j;
                } else if(j==0){
                    subproblems[i][j] = i;
                } else{
                    subproblems[i][j] = min(subproblems[i-1][j-1]+(one.charAt(i-1)==two.charAt(j-1)?0:1),
                            subproblems[i-1][j]+1,
                            subproblems[i][j-1]+1);
                }
            }
        }
        return subproblems[one.length()][two.length()];
    }

    private int min(int a,int b, int c){
        if(a>b){
            return Math.min(b,c);
        } else{
            return Math.min(a,c);
        }
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