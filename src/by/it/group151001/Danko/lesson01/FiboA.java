package by.it.group151001.Danko.lesson01;

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

        fibo = new FiboA();
        n = 33;
        System.out.printf("slowA(%d)=%d \n\t time=%d \n\n", n, fibo.slowA(n), fibo.time());
    }


    private int calc(int n) {

        int fib1 = 0;
        int fib2 = 1;
        int fib_sum = 0;
        for(int i = 1; i < n; i++) {
            fib_sum = fib1 + fib2;
            fib1 = fib2;
            fib2 = fib_sum;
        }
        return fib_sum;
    }


    BigInteger slowA(int n) {

        if (n < 2) {
            return BigInteger.valueOf(n);
        }
        else {
            return slowA(n-1).add(slowA(n-2));
        }
    }



}

