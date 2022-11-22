package by.it.group151003.tolkanitsa.lesson01;

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
        BigInteger[] fbNums = new BigInteger[n];
        fbNums[0] = BigInteger.ONE;
        if (n > 1)
            fbNums[1] = BigInteger.ONE;
        for (int i = 2; i < n; ++i)
        {
            fbNums[i] = fbNums[i - 1].add(fbNums[i - 2]);
        }
        return fbNums[n - 1];
    }

}

