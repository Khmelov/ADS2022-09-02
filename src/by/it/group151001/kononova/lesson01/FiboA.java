package by.it.group151001.kononova.lesson01;

import java.math.BigInteger;

/*
 * Вам необходимо выполнить рекурсивный способ вычисления чисел Фибоначчи
 */

public class FiboA {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboA fibo = new FiboA();
        int n = 33;
        System.out.printf("calc(%d)=%d \n\t time=%d \n\n", n, fibo.calc(n), fibo.time());

        //вычисление чисел фибоначчи медленным методом (рекурсией)
        fibo = new FiboA();
        n = 33;
        System.out.printf("slowA(%d)=%d \n\t time=%d \n\n", n, fibo.slowA(n), fibo.time());
    }


    public int calc(int n) {
        if (n< 0 || n > 46) {
            throw new IndexOutOfBoundsException(n);
        }
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int f0 = 0;
        int f1 = 1;
        int fn = 0;
        for (int i = 2; i <= n; i++) {
            fn = f0+f1;
            f0 = f1;
            f1 = fn;
        }
        return fn;
    }


    public BigInteger slowA(Integer n) {
        if (n <= 1) {
            return BigInteger.valueOf(n);
        }
        else {
            return BigInteger.valueOf(slowA(n - 1).longValue()
                    + slowA(n - 2).longValue());
        }
    }



}

