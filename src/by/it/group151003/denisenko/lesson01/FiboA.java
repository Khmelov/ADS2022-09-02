package by.it.group151003.denisenko.lesson01;

import java.math.BigInteger;

/*
 * Вам необходимо выполнить рекурсивный способ вычисления чисел Фибоначчи
 */

public class FiboA {

    private final long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public int calc(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return calc(n - 1) + calc(n - 2);
    }

    public BigInteger slowA(Integer n) {
        if (n == 0) return BigInteger.ZERO;
        if (n == 1) return BigInteger.ONE;
        return slowA(n - 1).add(slowA(n - 2));
    }
}

