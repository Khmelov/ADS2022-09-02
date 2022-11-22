package by.it.group151003.mytnik.lesson06;

import java.io.InputStream;
import java.util.Scanner;

public class A_LIS {
    public int getSeqSize(InputStream stream) {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();

        int[] m = new int[n];
        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();
        }

        return longestIncreasingSubSequence(m);
    }

    private int longestIncreasingSubSequence(int[] m) {
        int[] lis = new int[m.length];
        int maxLis = 0;

        for (int i = 0; i < m.length; i++) {
            lis[i] = 1;

            for (int j = 0; j < i; j++) {
                if (m[i] > m[j] && lis[i] < lis[j] + 1) {
                    lis[i] = lis[j] + 1;
                }
            }

            if (maxLis < lis[i]) {
                maxLis = lis[i];
            }
        }

        return maxLis;
    }
}
