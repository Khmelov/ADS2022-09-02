package by.it.group151004.gonzarevich.lesson01;

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
        BigInteger[] fibnums = new BigInteger[n+1];
        fibnums[0] = BigInteger.ZERO;
        fibnums[1] = BigInteger.ONE;
        for (int i = 2; i <= n; i++){
            fibnums[i] = fibnums[i-1].add(fibnums[i-2]);
        }
        return fibnums[n];
    }

}

