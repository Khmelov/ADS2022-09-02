package by.it.group151003.klimovich.lesson01;

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
        if(n==0)
            return BigInteger.ZERO;
        else if(n==1)
            return BigInteger.ONE;

        BigInteger[] arrfib = new BigInteger[n+1];
        arrfib[0] = BigInteger.ZERO;
        arrfib[1] = BigInteger.ONE;

        for(int i=2; i<n+1; ++i)
            arrfib[i]=arrfib[i-1].add(arrfib[i-2]);
        return arrfib[n];
    }

}

