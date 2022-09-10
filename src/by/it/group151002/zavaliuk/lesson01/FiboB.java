package by.it.group151002.zavaliuk.lesson01;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/*
 * Вам необходимо выполнить способ вычисления чисел Фибоначчи с вспомогательным массивом
 * без ограничений на размер результата (BigInteger)
 */

public class FiboB {

    List<BigInteger> listFib = new ArrayList<>();

    {
        listFib.add(BigInteger.ZERO);
        listFib.add(BigInteger.ONE);
    }

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
        for (int i = listFib.size(); i <= n; i++) {
            BigInteger res = listFib.get(i - 1).add(listFib.get(i - 2));
            listFib.add(res);
        }
        return listFib.get(n);
    }

}