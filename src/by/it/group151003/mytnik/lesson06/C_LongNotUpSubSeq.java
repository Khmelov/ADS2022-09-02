package by.it.group151003.mytnik.lesson06;

import java.io.InputStream;
import java.util.Scanner;

public class C_LongNotUpSubSeq {

    int getNotUpSeqSize(InputStream stream) {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();

        int[] m = new int[n];
        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();
        }

        // int[] testArray = {15, 27, 14, 38, 63, 55, 46, 65, 85};

        return longestDecreasingSubsequence(m);
    }

    private int longestDecreasingSubsequence(int[] m) {
        int[] lds = new int[m.length];
        int maxLds = 0;

        for (int i = 0; i < m.length; i++) {
            lds[i] = 1;

            for (int j = 0; j < i; j++) {
                if (m[i] <= m[j] && lds[i] < lds[j] + 1) {
                    lds[i] = lds[j] + 1;
                }
            }

            if (maxLds < lds[i]) {
                maxLds = lds[i];
            }
        }

        return maxLds;
    }
}