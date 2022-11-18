package by.it.group151003.polovoy.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class A_EditDist {


    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!


        int result = 0;
        if (one.equals(two)) return result;
        int N = one.length() + 1;
        int M = two.length() + 1;

        int[][] Table = new int[N][M];

        for (int i = 0; i < N; i++){
            Arrays.fill(Table[i], -1);
        }

        EditDistTD(Table, one, two, N-1, M-1);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return Table[N-1][M-1];
    }

    public static int EditDistTD(int[][] Table, String one, String two, int i, int j){

        if (Table[i][j] == -1){
            if (i == 0){
                Table[i][j] = j;
            }
            else if (j == 0){
                Table[i][j] = i;
            }
            else{
                int ins = EditDistTD(Table, one, two, i, j - 1) + 1;
                int del = EditDistTD(Table, one, two, i - 1, j) + 1;
                int sub = EditDistTD(Table, one, two, i - 1, j - 1) + (one.charAt(i - 1) == two.charAt(j - 1) ? 0 : 1);
                Table[i][j] = Math.min(Math.min(ins, del), sub);
            }
        }
        return Table[i][j];
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151003/polovoy/lesson07/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }
}
