package by.it.a_khmelev.lesson01;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

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
        ArrayList<BigInteger> nums = new ArrayList<BigInteger>();
        nums.add(BigInteger.ZERO);
        nums.add(BigInteger.ONE);
        for(int i = 1; i <= n; i++) {
            nums.add(nums.get(i).add(nums.get(i-1)));
        }
        return nums.get(n);
    }

}

