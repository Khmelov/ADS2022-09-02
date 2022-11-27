package by.it.group151003.polovoy.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class B_EditDist {


    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        if (one.equals(two)) return 0;
        int N = one.length() + 1;
        int M = two.length() + 1;

        int[][] Table = new int[N][M];

        for (int i = 0; i < N; i++){
            Table[i][0] = i;
        }

        for (int j = 0; j < M; j++){
            Table[0][j] = j;
        }

        for (int i = 1; i < N; i++){

            for (int j = 1; j < M; j++){

                int ins = Table[i][j - 1] + 1;
                int del = Table[i - 1][j] + 1;
                int sub = Table[i - 1][j - 1] + (one.charAt(i - 1) == two.charAt(j - 1) ? 0 : 1);
                Table[i][j] = Math.min(Math.min(ins, del), sub);
            }
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return Table[N - 1][M - 1];
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151003/polovoy/lesson07/dataABC.txt");
        B_EditDist instance = new B_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}