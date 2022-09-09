package by.it.group151001.shlyk.lesson01;

import java.math.BigInteger;
import java.util.ArrayList;

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
        if (n == 0)
            return BigInteger.ZERO;
        if (n <= 2)
            return BigInteger.ONE;

        ArrayList<BigInteger> buffer = new ArrayList<>(n + 1);
        buffer.add(BigInteger.ZERO);
        buffer.add(BigInteger.ONE);
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++){
            result = result.add(buffer.get(i - 2) );
            buffer.add(i, result);
        }
        return result;
    }

}

