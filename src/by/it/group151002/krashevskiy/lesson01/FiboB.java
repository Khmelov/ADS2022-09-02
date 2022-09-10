package by.it.group151002.krashevskiy.lesson01;

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
        if (n == 0) {
            return BigInteger.ZERO;
        } else if (n == 1 || n == 2) {
            return BigInteger.ONE;
        }

        BigInteger[] array = {BigInteger.ZERO,BigInteger.ONE, BigInteger.ONE};
        for (int i = 2; i < n; i++) {
            array[0] = array[1];
            array[1] = array[2];
            array[2] = array[0].add(array[1]);
        }

        return array[2];
    }

}

