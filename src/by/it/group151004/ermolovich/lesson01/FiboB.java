package by.it.group151004.ermolovich.lesson01;

import java.math.BigInteger;

/*
 * Вам необходимо выполнить способ вычисления чисел Фибоначчи с вспомогательным массивом
 * без ограничений на размер результата (BigInteger)
 */

public class FiboB {

    private long startTime = System.currentTimeMillis();
    private BigInteger[] prev;
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
        prev = new BigInteger[n + 1];
        prev[0] = BigInteger.ZERO;
        prev[1] = BigInteger.ONE;
        return calcFibo(n);
    }

    private BigInteger calcFibo(Integer n) {
        if (prev[n] != null) return prev[n];
        return prev[n] = calcFibo(n - 1).add(calcFibo(n - 2));
    }
}
