package by.it.group151004.belsky.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
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


    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return getDistanceEditingWithMemo(one, two);
    }


    //Alternative slower solution. But it does not require additional memory for memo array
    int getDistanceEditingNoMemo(String one, String two) {
        return getDistanceEditingNoMemoRec(one, two, one.length(), two.length());
    }

    int getDistanceEditingNoMemoRec(String one, String two, int i, int j) {
        if (i == 0 && j == 0) {
            return 0;
        } else if (i == 0) {
            return j;
        } else if (j == 0) {
            return i;
        } else {
            int var1 = getDistanceEditingNoMemoRec(one, two, i, j-1) + 1;
            int var2 = getDistanceEditingNoMemoRec(one, two, i-1, j) + 1;
            int var3 = getDistanceEditingNoMemoRec(one, two, i-1, j-1);
            if (one.charAt(i-1) != two.charAt(j-1)) var3+=1;
            return Math.min(var1, Math.min(var2, var3));
        }
    }

    int getDistanceEditingWithMemo(String one, String two) {
        int[][] arr = new int[one.length()+1][two.length()+1];
        for (int[] subarr : arr) {
            Arrays.fill(subarr, -1);
        }

        for (int i = 0;i < one.length()+1;i++) {
            arr[i][0] = i;
        }

        for (int j = 0;j < two.length()+1;j++) {
            arr[0][j] = j;
        }

        return getDistanceEditingWithMemoRec(one, two, arr, one.length(), two.length());
    }

    int getDistanceEditingWithMemoRec(String one, String two, int[][] arr, int i, int j) { //array should have first row and column initialized
        if (arr[i][j] != -1) return arr[i][j];

        int var1 = getDistanceEditingWithMemoRec(one, two, arr, i, j-1) + 1;
        int var2 = getDistanceEditingWithMemoRec(one, two, arr,i-1, j) + 1;
        int var3 = getDistanceEditingWithMemoRec(one, two, arr,i-1, j-1);
        if (one.charAt(i-1) != two.charAt(j-1)) var3+=1;

        arr[i][j] = Math.min(var1, Math.min(var2, var3));
        return  arr[i][j];
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }
}
