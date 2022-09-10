package by.it.group151002.krashevskiy.lesson01;

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

    private int calc(int n) {
        if (n < 2) {
            return n;
        }
        int prevPrev = 1;
        int prev = 1;
        int num = 2;
        for (int i = 3; i < n; i++) {
            prevPrev = prev;
            prev = num;
            num = prevPrev + prev;
        }

        return num;
    }

    BigInteger slowA(Integer n) {
        if (n == 0) {
            return BigInteger.ZERO;
        } else if (n == 1 || n == 2) {
            return BigInteger.ONE;
        }

        return slowA(n - 1).add(slowA(n - 2));
    }
}

