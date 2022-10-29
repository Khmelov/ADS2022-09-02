package by.it.group151004.matsiushin.lesson01;

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

    private BigInteger[] FibArr;

    public static void main(String[] args) {

        //вычисление чисел простым быстрым методом
        FiboB fibo = new FiboB();
        int n = 55555;
        System.out.printf("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());
    }


    BigInteger fastB(Integer n) {
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)
        FibArr = new BigInteger[n + 1];
        FibArr[0] = BigInteger.ZERO;
        FibArr[1] = BigInteger.ONE;

        return FibCalc(n);
    }


    BigInteger FibCalc (Integer n){
        if (FibArr[n] == null)
            FibArr[n] = FibCalc(n - 1).add(FibCalc(n - 2));

        return FibArr[n];

    }
}

