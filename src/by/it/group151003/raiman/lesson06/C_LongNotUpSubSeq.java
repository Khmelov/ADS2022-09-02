package by.it.group151003.raiman.lesson06;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class C_LongNotUpSubSeq {

    public int getNotUpSeqSize(InputStream stream) {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();

        int[] m = new int[n];
        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();
        }

        int[] ldsIndexes = longestDecreasingSubsequenceIndexes(m);

        System.out.println("Given array: " + Arrays.toString(m));
        System.out.println("Indexes: " + Arrays.toString(ldsIndexes));
        System.out.println("Elements: " + Arrays.toString(getElements(m, ldsIndexes)));

        return ldsIndexes.length;
    }

    private int[] getElements(int[] m, int[] ldsIndexes) {
        return Arrays.stream(ldsIndexes).map(i -> m[i - 1]).toArray();
    }

    private int[] longestDecreasingSubsequenceIndexes(int[] m) {
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

        int[] indexes = new int[maxLds];
        int index = maxLds - 1;

        for (int i = m.length - 1; i >= 0; i--) {
            if (lds[i] == maxLds) {
                indexes[index] = i + 1;
                index--;
                maxLds--;
            }
        }

        return indexes;
    }
}