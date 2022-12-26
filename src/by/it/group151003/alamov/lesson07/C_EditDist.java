package by.it.group151003.alamov.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class C_EditDist {

    String getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        StringBuilder result = new StringBuilder();
        int n = one.length() + 1;
        int m = two.length() + 1;
        int[][] D = new int[n][m];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j)
                D[i][j] = -1;
        editDistBU(D, one, two, n, m,n - 1, m - 1);
        int i = n - 1;
        int j = m - 1;
        while (i > 0 && j > 0) {
            result.insert(0, ",");
            boolean check = one.charAt(i - 1) == two.charAt(j - 1);
            int c = check ? 0 : 1;
            char symbol = check ? '#' : '~';
            if (D[i][j] == D[i - 1][j - 1] + c) {
                if (symbol == '~')
                    result.insert(0, "~" + two.charAt(j - 1));
                else
                    result.insert(0, symbol);
                --j;
                --i;
            }
            else
            if (D[i][j] == D[i - 1][j] + 1) {
                result.insert(0, '-');
                result.insert(1, one.charAt(i - 1));
                --i;
            }
            else if (D[i][j] == D[i][j - 1] + 1) {
                result.insert(0, '+');
                result.insert(1, two.charAt(j - 1));
                --j;
            }
        }

        if (i > 0) {
            result.insert(0,',');
            result.insert(0,'-');
            result.insert(1, one.charAt(i - 1));
        } else if (j > 0) {
            result.insert(0,',');
            result.insert(0,'+');
            result.insert(1, two.charAt(j - 1));
        };
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result.toString();
    }

    private int editDistBU(int[][] D, String one, String two, int n, int m, int i, int j) {
        for (i = 0; i < n; ++i)
            D[i][0] = i;
        for (j = 0; j < m; ++j)
            D[0][j] = j;
        for (i = 1; i < n; ++i)
            for (j = 1; j < m; ++j) {
                int c = (one.charAt(i - 1) == two.charAt(j - 1)) ? 0 : 1;
                D[i][j] = Math.min(Math.min(D[i - 1][j] + 1, D[i][j - 1] + 1), D[i - 1][j - 1] + c);
            }
        return D[n - 1][m - 1];
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
    }

}