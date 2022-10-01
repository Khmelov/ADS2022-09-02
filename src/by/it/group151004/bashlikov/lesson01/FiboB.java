package by.it.group151004.bashlikov.lesson01;

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
        int n = 5555;
        System.out.printf("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());
    }

    BigInteger fastB(Integer n) {
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)
        BigInteger[] prevRes = new BigInteger[n + 1];
        if (n == 0) {
            prevRes = new BigInteger[2];
        }
        prevRes[0] = BigInteger.ZERO;
        prevRes[1] = BigInteger.ONE;
        if (n > 1) {
            return calc(n - 1, prevRes);
        } else {
            return calc(n, prevRes);
        }
    }

    BigInteger calc(Integer n, BigInteger[] prevRes) {
        if (prevRes[n] != null) {
            return prevRes[n];
        }

        prevRes[n] = calc(n - 1, prevRes).add(calc(n - 2, prevRes));
        return prevRes[n];
    }

}

