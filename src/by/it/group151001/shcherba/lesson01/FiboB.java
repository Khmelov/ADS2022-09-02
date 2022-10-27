package by.it.group151001.shcherba.lesson01;

import java.math.BigInteger;

/*
 * Вам необходимо выполнить способ вычисления чисел Фибоначчи с вспомогательным массивом
 * без ограничений на размер результата (BigInteger)
 */

public class FiboB {

    private long startTime = System.currentTimeMillis();

    private BigInteger[] arr;

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {

        //вычисление чисел простым быстрым методом
        FiboB fibo = new FiboB();
        int n = 55555;
        System.out.printf("fastB(%d) = %d \n\t time = %d \n\n", n, fibo.fastB(n), fibo.time());
    }

    BigInteger fastB(Integer n) {
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)
        arr = new BigInteger[n + 1];

        arr[0] = BigInteger.ZERO;
        arr[1] = BigInteger.ONE;

        for (int i = 2; i <= n; i++){
            arr[i] = arr[i - 2].add(arr[i - 1]);
        }
        return arr[n];
    }

}

