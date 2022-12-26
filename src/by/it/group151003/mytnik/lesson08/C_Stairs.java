package by.it.group151003.mytnik.lesson08;

import java.io.InputStream;
import java.util.Scanner;

public class C_Stairs {

    public int getMaxSum(InputStream stream) {
        Scanner scanner = new Scanner(stream);

        int n = scanner.nextInt();
        int[] stairs = new int[n];

        for (int i = 0; i < n; i++) {
            stairs[i] = scanner.nextInt();
        }

        return getMaxSum(stairs);
    }

    public int getMaxSum(int[] stairs) {
        int[] maxSum = new int[stairs.length + 1];

        maxSum[0] = 0;
        maxSum[1] = stairs[0];

        for (int i = 2; i <= stairs.length; i++) {
            maxSum[i] = Math.max(maxSum[i - 1], maxSum[i - 2]) + stairs[i - 1];
        }

        return maxSum[stairs.length];
    }
}