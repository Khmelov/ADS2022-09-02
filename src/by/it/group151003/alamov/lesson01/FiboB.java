package by.it.group151003.alamov.lesson01;

import java.math.BigInteger;

public class FiboB {
    private long startTime = System.currentTimeMillis();
    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboB fibo = new FiboB();
        int n = 55555;
        System.out.printf("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());
    }

    BigInteger fastB(Integer n) {
        if (n == 0) return BigInteger.ZERO;
        if (n == 1) return BigInteger.ONE;

        BigInteger[] fast = new BigInteger[n];
        fast[0] = BigInteger.ONE;
        fast[1] = BigInteger.ONE;
        for (int i = 2; i < n; i++){
            fast[i] = fast[i - 1].add(fast[i - 2]);
        }
        return fast[n - 1];
    }

}

