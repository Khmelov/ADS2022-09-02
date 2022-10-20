package by.it.group151003.romanko.lesson01;

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
        int fib = 0;

        for(int i = 3, count = 1, temp = 1; i <= n; i++) {
            fib = temp + count;
            temp = count;
            count = fib;
        }

        return fib;
    }

    BigInteger slowA(Integer n) {
        if (n == 0){
            return BigInteger.ZERO;
        } else if (n == 1){
            return BigInteger.ONE;
        } else{
            return slowA(n - 2).add(slowA(n - 1));
        }
    }

}

