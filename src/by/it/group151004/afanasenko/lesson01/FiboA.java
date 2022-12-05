package by.it.group151004.afanasenko.lesson01;

import java.math.BigInteger;

import java.math.BigInteger;

public class FiboA {
    public long startTime = System.currentTimeMillis();

    public long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboA fibo = new FiboA();
        int n = 33;
        System.out.printf("calc(%d)=%d \n\t time=%d \n\n", n, fibo.calc(n), fibo.time());

        fibo = new FiboA();
        n = 33;
        System.out.printf("slowA(%d)=%d \n\t time=%d \n\n", n, fibo.slowA(n), fibo.time());
    }

    public int calc(int n) {
        if (n < 2) {
            return n;
        }
        return calc(n - 1) + calc(n - 2);
    }

    public BigInteger slowA(Integer n) {
        switch (n) {
            case 0:
                return BigInteger.ZERO;
            case 1:
                return BigInteger.ONE;
        }
        return slowA(n - 1).add(slowA(n - 2));
    }
}
