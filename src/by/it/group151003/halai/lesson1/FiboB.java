package by.it.group151003.halai.lesson1;

import java.math.BigInteger;

public class FiboB {
    public long startTime = System.currentTimeMillis();

    public long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboB fibo = new FiboB();
        int n =20000;
        System.out.printf  ("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());
    }

    public BigInteger fastB(Integer n) {
        BigInteger[] arr = new BigInteger[n + 1];
        arr[0] = BigInteger.ZERO;
        switch (n) {
            case 0:
                return arr[0];
            case 1:
                arr[1] = BigInteger.ONE;
                return arr[1];
        }
        arr[1] = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            arr[i] = arr[i - 1].add(arr[i - 2]);
        }
        return arr[n];
    }
}
