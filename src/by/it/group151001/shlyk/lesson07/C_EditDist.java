package by.it.group151001.shlyk.lesson07;

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

    private static final String deletable = "-";
    private static final String addable = "+";
    private static final String copiable = "#";
    private static final String replaceable = "~";

    private int getChangingPrice(){
        return 1;
    }
    private int getInsertionPrice(){
        return 1;
    }
    private int getDeletingPrice(){
        return 1;
    }
    private int[][] transMatrix;
    void buildConverting(String origin, String result){
        int[][] matrix = new int[result.length() + 1][origin.length() + 1];
        for(int i = 1; i <= origin.length(); i++)
            matrix[0][i] = i;
        for(int j = 1; j <= result.length(); j++)
            matrix[j][0] = j;
        for(int x = 1; x <= origin.length(); x++){
            for(int y = 1; y <= result.length(); y++){
                int minValue = (result.charAt(y  - 1) == origin.charAt(x - 1)) ? 0 : getChangingPrice();
                minValue += matrix[y - 1][x - 1];
                int minProbe = matrix[y][x - 1] + getDeletingPrice();
                if(minProbe > matrix[y - 1][x] + getInsertionPrice())
                    minProbe = matrix[y - 1][x] + getInsertionPrice();
                if(minValue > minProbe)
                    minValue = minProbe;
                matrix[y][x] = minValue;
            }
        }
        transMatrix = matrix;
    }
    StringBuilder buildPath(String origin, String result){
        StringBuilder sbResult = new StringBuilder();
        int iX = origin.length(), iY = result.length();
        String appendable = "?";
        boolean wasAdded;
        while(iX != 0 && iY != 0){
            wasAdded = false;
            if(origin.charAt(iX - 1) == result.charAt(iY - 1)) {
                appendable = copiable;
                wasAdded = true;
                iX--;
                iY--;
            }
            if(!wasAdded && transMatrix[iY][iX - 1] + getDeletingPrice() == transMatrix[iY][iX]) {
                appendable = deletable + origin.charAt(iX - 1);
                wasAdded = true;
                iX--;
            }
            if(!wasAdded && transMatrix[iY - 1][iX] + getInsertionPrice() == transMatrix[iY][iX]) {
                appendable = addable + result.charAt(iY - 1);
                wasAdded = true;
                iY--;
            }
            if(!wasAdded && transMatrix[iY][iX] == transMatrix[iY - 1][iX - 1] + getChangingPrice()){
                appendable = replaceable + result.charAt(iY - 1);
                iX--;
                iY--;
            }
            sbResult.append(appendable).append(" ,");
        }
        for(int i = iY; i != 0; i--){
            appendable = addable + result.charAt(i - 1);
            sbResult.append(appendable).append(" ,");
        }
        sbResult.deleteCharAt(sbResult.length() - 1);
        sbResult.reverse();
        sbResult.delete(0, 1);
        return sbResult;
    }
    String getDistanceEdinting(String origin, String result) {
        StringBuilder sbResult;
        buildConverting(origin, result);
        sbResult = buildPath(origin, result);
        return sbResult.toString();
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151001/shlyk/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}