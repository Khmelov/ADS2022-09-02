package by.it.group151004.prokopchuk.lesson01;

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
        if (n != 1 && n != 2) {
            BigInteger[] arr = {BigInteger.ONE, BigInteger.ONE, BigInteger.TWO};
            for (int i = 3; i < n; i++) {
                arr[0] = arr[1];
                arr[1] = arr[2];
                arr[2] = arr[0].add(arr[1]);
            }
            return arr[2];
        } else
            return BigInteger.ONE;
    }

}

