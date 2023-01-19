package by.it.group151003.saydalikhujaev.lesson01;

import java.math.BigInteger;

/*
 * Вам необходимо выполнить рекурсивный способ вычисления чисел Фибоначчи
 */

public class FiboA {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboA fibo = new FiboA();
        int n = 33;
        System.out.printf("calc(%d)=%d \n\t time=%d \n\n", n, fibo.calc(n), fibo.time());

        //вычисление чисел фибоначчи медленным методом (рекурсией)
        fibo = new FiboA();
        n = 33;
        System.out.printf("slowA(%d)=%d \n\t time=%d \n\n", n, fibo.slowA(n), fibo.time());
    }

    private int calc(int n) {
        // здесь простейший вариант, в котором код совпадает с мат.определением чисел Фибоначчи
        // время O(2^n)
        int fib = 0;

        for(int i = 3, counter = 1, temp = 1; i <= n; i++) {
            fib = temp + counter;
            temp = counter;
            counter = fib;
        }

        return fib;
    }

    BigInteger slowA(Integer n) {
        // рекурсия
        // здесь нужно реализовать вариант без ограничения на размер числа,
        // в котором код совпадает с мат.определением чисел Фибоначчи
        // время O(2^n)
        return switch (n) {
            case 0 -> BigInteger.ZERO;
            case 1 -> BigInteger.ONE;
            default -> slowA(n - 2).add(slowA(n - 1));
        };
    }

}


