package by.it.group151002.ryabov.lesson01;

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
        FiboB fibo = new FiboB();
        int n = 100000;
        System.out.printf("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());
    }

    BigInteger fastB(Integer n) {
        BigInteger[] arrBI = new BigInteger[n + 1];
        arrBI[0] = BigInteger.ZERO;
        arrBI[1] = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            arrBI[i] = arrBI[i - 1].add(arrBI[i - 2]);
        }
        return arrBI[n];
    }

}

