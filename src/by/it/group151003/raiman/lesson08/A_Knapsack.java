package by.it.group151003.raiman.lesson08;

import java.io.*;
import java.util.Scanner;

public class A_Knapsack {
    public int getMaxWeight(InputStream stream) {
        try (Scanner scanner = new Scanner(stream)) {
            int we = scanner.nextInt();
            int n = scanner.nextInt();
            int gold[] = new int[n];
            for (int i = 0; i < n; i++) {
                gold[i] = scanner.nextInt();
            }
            int sol[] = new int[3000000];
            sol[0] = 0;
            for (int k = 0; k <= we; k++) {
                int l = 0;
                for (int element : gold) {
                    if (k >= element) {
                        l = Math.max(l, element + sol[k - element]);
                    }
                }
                sol[k] = l;
            }
            return sol[we];
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151003/raiman/lesson08/dataA.txt");
        A_Knapsack instance = new A_Knapsack();
        int res = instance.getMaxWeight(stream);
        System.out.println(res);
    }
}