package by.it.group151003.onuchina.lesson01;

import java.math.BigInteger;

/*
 * Вам необходимо выполнить способ вычисления чисел Фибоначчи с вспомогательным массивом
 * без ограничений на размер результата (BigInteger)
 */

public class FiboB {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {

        //вычисление чисел простым быстрым методом
        FiboB fibo = new FiboB();
        int n = 55555;
        System.out.printf("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());
    }

    BigInteger fastB(Integer n) {
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)
        BigInteger[] fib_num = new BigInteger[n+1];
        fib_num[0] = BigInteger.ZERO;
        fib_num[1] = BigInteger.ONE;
        int i;
        for (i = 2; i <= n; i++) fib_num[i] = fib_num[i - 2].add(fib_num[i - 1]);
        return fib_num[n];
    }

}

