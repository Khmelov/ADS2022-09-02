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

    private static class Edinting{
        private final StringBuilder builder;
        private int distance = 0;

        public Edinting(){
            builder = new StringBuilder();
        }

        public Edinting(Edinting edinting){
            builder = new StringBuilder();
            builder.append(edinting.builder.toString());
            distance = edinting.distance;
        }

        public int getDistance() {
            return distance;
        }

        public void append(String str){
            builder.append(str).append(",");
            if(str.length()>1){
                distance++;
            }
        }

        @Override
        public String toString(){
            return builder.toString();
        }
    }

    String getDistanceEdinting(String one, String two) {
        Edinting[][] subproblems = new Edinting[one.length()+1][two.length()+1];
        for(int i = 0;i<subproblems.length;i++){
            for(int j = 0;j<subproblems[i].length;j++){
                if(i==0){
                    subproblems[i][j] = createEdintingByInsertions(two.substring(two.length()-j));
                } else if(j==0){
                    subproblems[i][j] = createEdintingByInsertions(two.substring(one.length()-i));
                } else{
                    subproblems[i][j] = findBestEdinting(subproblems,i,j,one,two);
                }
            }
        }
        return subproblems[one.length()][two.length()].toString();
    }

    private Edinting createEdintingByInsertions(String str){
        Edinting edinting = new Edinting();
        for(int i = 0;i<str.length();i++){
            edinting.append(String.format("+%c",str.charAt(i)));
        }
        return edinting;
    }

    private Edinting findBestEdinting(Edinting[][] subproblems,int i,int j,String one,String two){
        boolean sameChars = one.charAt(i - 1) == two.charAt(j - 1);
        int[] distances = {subproblems[i-1][j-1].getDistance()+(sameChars ?0:1),
                subproblems[i-1][j].getDistance()+1,
                subproblems[i][j-1].getDistance()+1};
        int index = findMinElementIndex(distances);
        Edinting edinting;
        if(index == 0){
            edinting = new Edinting(subproblems[i-1][j-1]);
            if(sameChars){
                edinting.append("#");
            } else{
                edinting.append(String.format("~%c",two.charAt(j-1)));
            }
        } else if(index==1){
            edinting = new Edinting(subproblems[i-1][j]);
            edinting.append(String.format("-%c",two.charAt(j-1)));
        } else{
            edinting = new Edinting(subproblems[i][j-1]);
            edinting.append(String.format("+%c",one.charAt(i-1)));
        }
        return edinting;
    }

    private int findMinElementIndex(int[] arr){
        int min = arr[0];
        int index = 0;
        for(int i = 1;i<arr.length;i++){
            if(arr[i]<min){
                min = arr[i];
                index = i;
            }
        }
        return index;
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