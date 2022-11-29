package by.it.group151003.polovoy.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class C_EditDist {

    String getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
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

        StringBuilder result = new StringBuilder();
        int i = N - 1;
        int j = M - 1;
        while(i != 0 || j != 0){
            if (Table[i][j] == Table[i][j - 1] + 1) {
                result.append("+").append(two.charAt(j - 1)).append(",");
                j--;
            }
            else if (Table[i][j] == Table[i - 1][j] + 1) {
                result.append("-").append(one.charAt(i - 1)).append(",");
                i--;
            }
            else {
                if (Table[i][j] == Table[i - 1][j - 1])
                    result.append("#,");
                else
                    result.append("~").append(two.charAt(j - 1)).append(",");
                i--;
                j--;
            }
        }

        StringBuilder result1 = new StringBuilder();
        int count = result.length() - 2;
        while (count >= 0) {
            int length = 0;
            String oper_plus_elem = "";
            while (count >= 0 && result.charAt(count) != ',') {
                --count;
                ++length;
            }
            oper_plus_elem += result.substring(count + 1, count + 1 + length);
            result1.append(oper_plus_elem).append(",");
            --count;
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result1.toString();
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151003/polovoy/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}