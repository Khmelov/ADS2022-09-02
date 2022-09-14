package by.it.group151001.novik.lesson01;

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
        int n = 0;

        System.out.printf("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());
    }

    BigInteger fastB(Integer n) {
        BigInteger[]FiboArray = new BigInteger [n + 1];
        FiboArray[0] = BigInteger.ZERO;
        if (n > 0) FiboArray[1] = BigInteger.ONE;
        for (int i = 2; i<=n; i++){
            FiboArray[i] = FiboArray[i - 1].add(FiboArray[i - 2]);
        }
        return FiboArray[n];
    }

}

