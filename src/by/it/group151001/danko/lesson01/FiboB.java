package by.it.group151001.danko.lesson01;

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
        int n = 55555;
        System.out.printf("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());
    }

    BigInteger fastB(Integer n) {
        BigInteger[] fibArray;
        fibArray = new BigInteger[n];
        fibArray[0] = BigInteger.ONE;
        fibArray[1] = BigInteger.ONE;
        for(int i = 2; i <= n - 1; i++) {
            fibArray[i] = fibArray[i - 1].add(fibArray[i - 2]);
        }
        return fibArray[n - 1];
    }

}

